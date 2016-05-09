package de.alexandria.cms.backend.impl.repository.lucene;

import de.alexandria.cms.backend.api.repository.SearchRepository;
import de.alexandria.cms.model.api.content.DomainName;
import de.alexandria.cms.model.api.search.SearchResult;
import de.alexandria.cms.model.impl.search.SearchResultImpl;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.de.GermanAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.misc.ChainedFilter;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryFilter;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.springframework.stereotype.Repository;

@Repository
public class SearchRepositoryImpl implements SearchRepository {

  private LuceneIndexNetCMS indexCMS;

  private LuceneDocumentFactory luceneDocumentFactory;

  public void setIndex(final LuceneIndexNetCMS indexCMS) {
    this.indexCMS = indexCMS;
  }

  public void setLuceneDocumentFactory(final LuceneDocumentFactory luceneDocumentFactory) {
    this.luceneDocumentFactory = luceneDocumentFactory;
  }

  @Override
  public synchronized void index(final DomainName dn, final Object obj) {
    try {
      unIndex(dn, obj);
    } catch (final RuntimeException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    try {
      final Analyzer analyzer = createAnalyzer();
      final IndexWriter writer = indexCMS.createWriter(analyzer);

      try {
        final Document doc = luceneDocumentFactory.createDocument(obj);
        writer.addDocument(doc);
        writer.optimize();
      } finally {
        writer.close();
      }
    } catch (final IOException e) {
      throw new LuceneException("Cannot update index", e);
    }
  }

  private Analyzer createAnalyzer() {
    // return new StandardAnalyzer();
    return new GermanAnalyzer();
  }

  @Override
  public synchronized void unIndex(final DomainName dn, final Object obj) {
    final String handleAttributeName = luceneDocumentFactory.getHandleAttributeName(obj);
    final String handleFieldName = luceneDocumentFactory.getHandleFieldName(obj);
    String handleAttributeValue = null;

    try {
      handleAttributeValue = BeanUtils.getProperty(obj, handleAttributeName);
      handleAttributeValue = obj.getClass().getSimpleName() + "." + handleAttributeValue;
    } catch (final Exception e) {
      throw new LuceneException("Cannot identify object", e);
    }

    try {
      final IndexReader reader = indexCMS.createReader();

      try {
        final Term t = new Term(handleFieldName, handleAttributeValue);
        reader.deleteDocuments(t);
      } finally {
        reader.close();
      }
    } catch (final IOException e) {
      throw new LuceneException("Cannot delete from index", e);
    }
  }

  @Override
  public boolean isIndexEmpty(final DomainName dn) {
    try {
      return this.indexCMS.getNumDocs() == 0;
    } catch (final IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public void deleteIndex(final DomainName dn) throws Exception {
    this.indexCMS.delete();

  }

  @Override
  public List search(final DomainName dn, final String query, final Object searchableObject) {
    MultiFieldQueryParser qp;
    Query myQuery = null;
    Analyzer analyzer = null;

    // create search filter for specific search object
    final Filter[] filters = new Filter[1];
    final Query typeQuery = new TermQuery(new Term("type", searchableObject.getClass().getName()));
    filters[0] = new QueryFilter(typeQuery);
    final Filter filter = new ChainedFilter(filters, ChainedFilter.OR);

    // get fields to do full text search on
    final ArrayList fields = new ArrayList();
    final Document objDoc = luceneDocumentFactory.createDocument(searchableObject);
    final List objFields = objDoc.getFields();
    for (final Iterator iter = objFields.iterator(); iter.hasNext();) {
      final Field objField = (Field) iter.next();
      if (objField.isIndexed()) {
        fields.add(objField.name());
      }
    }
    final String[] searchFields = (String[]) fields.toArray(new String[fields.size()]);

    // parse query string
    try {
      analyzer = luceneDocumentFactory.createAnalyzer();
      qp = new MultiFieldQueryParser(searchFields, analyzer);
      qp.setDefaultOperator(QueryParser.Operator.OR);
      myQuery = qp.parse(query.toLowerCase()); // lucene seems to index
      // all in lower case...
    } catch (final Exception e) {
      throw new LuceneException("Couldn't parse the query: " + e.getMessage());
    }

    // do search
    IndexSearcher searcher = null;
    try {
      searcher = indexCMS.createSearcher();

      // do search
      final Hits hits = searcher.search(myQuery, filter);

      // highlight text fragments in hits
      final Highlighter highlighter = new Highlighter(new QueryScorer(myQuery));
      highlighter.setTextFragmenter(new SimpleFragmenter(100));

      final List result = new ArrayList(hits.length());
      for (int i = 0; i < hits.length(); i++) {
        final Document doc = hits.doc(i);

        // highlight
        final StringBuffer highlightedTextSB = new StringBuffer();
        for (String fieldName : searchFields) {
          final String text = doc.get(fieldName);
          if (text != null) {
            final TokenStream tokenStream = analyzer.tokenStream(fieldName, new StringReader(text));
            // get 3 best fragments and seperate them with "..."
            final String textFragment = highlighter.getBestFragments(tokenStream, text, 3, "...");
            if (textFragment != null && textFragment.length() > 0) {
              if (highlightedTextSB.length() > 0) {
                highlightedTextSB.append("<br>");
              }
              highlightedTextSB.append(textFragment);
            }
          }
        }
        final String highlightedText = highlightedTextSB.toString();

        // add SearchResult object to result-list
        final SearchResult hitObject = new SearchResultImpl();

        String id = doc.get("handle");
        // cut object class type from id:
        id = id.substring(id.lastIndexOf(".") + 1);

        hitObject.setHandle(Long.valueOf(id));
        hitObject.setHighlightedText(highlightedText);
        result.add(hitObject);
        // result.add(Long.valueOf(doc.get("handle")));
      }
      return result;
    } catch (final Exception e) {
      throw new LuceneException("Couldn't complete search", e);
    } finally {
      try {
        if (searcher != null) {
          searcher.close();
        }
      } catch (final IOException e) {
        throw new LuceneException("Couldn't complete search", e);
      }
    }
  }
}
