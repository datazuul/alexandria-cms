package de.alexandria.cms.backend.impl.file.xml.model;

import de.alexandria.cms.model.api.content.Article;
import de.alexandria.cms.model.api.content.Author;
import de.alexandria.cms.model.api.content.Category;
import de.alexandria.cms.model.impl.content.AuthorImpl;
import de.alexandria.cms.model.impl.content.CategoryImpl;
import org.jdom.Document;
import org.jdom.Element;

/**
 * <pre>
 * &lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot; ?&gt;
 *
 * &lt;article category=&quot;1&quot;&gt;
 *   &lt;artheader&gt;
 *     &lt;author id=&quot;1&quot; /&gt;
 *     &lt;title&gt;Glauben, Religionen, Götter&lt;/title&gt;
 *     &lt;revhistory&gt;
 *       &lt;revision&gt;
 *         &lt;revnumber&gt;1.0&lt;/revnumber&gt;
 *         &lt;date&gt;13.07.2001&lt;/date&gt;
 *         &lt;revremark&gt;erstellt&lt;/revremark&gt;
 *       &lt;/revision&gt;
 *     &lt;/revhistory&gt;
 *
 *   &lt;/artheader&gt;
 * &lt;/article&gt;
 * </pre>
 *
 * @author ralf
 */
public class XMLArticle extends XMLObject {

  Element article = null;

  public XMLArticle(final Document pMetaDoc) {
    document = pMetaDoc;
    article = document.getRootElement();
  }

  public XMLArticle(final Article pArticle) {
    // <article> (root element)
    article = new Element("article");
    // create document
    document = new Document(article);

    if (pArticle.getCategory() != null) {
      article.setAttribute("category", String.valueOf(pArticle.getCategory().getId()));
    }

    // <artheader>
    final Element artheader = new Element("artheader");
    article.addContent(artheader);

    // <author>
    if (pArticle.getAuthor() != null) {
      final Element e = new Element("author");
      e.setAttribute("id", String.valueOf(pArticle.getAuthor().getId()));
      artheader.addContent(e);
    }

    // <title>
    if (pArticle.getTitle() != null) {
      final Element e = new Element("title");
      e.setText(pArticle.getTitle());
      artheader.addContent(e);
    }

  }

  public Author getAuthor() {
    Author result = null;
    final Element artheader = article.getChild("artheader");
    final Element author = artheader.getChild("author");
    if (author != null) {
      final long id = Long.parseLong(author.getAttributeValue("id"));
      result = new AuthorImpl(id);
    }
    return result;
  }

  public Category getCategory() {
    Category result = null;
    final String id = article.getAttributeValue("category");
    if (id != null) {
      result = new CategoryImpl(Long.parseLong(id));
    }
    return result;
  }

  public String getTitle() {
    return article.getChild("artheader").getChildText("title");
  }
}
