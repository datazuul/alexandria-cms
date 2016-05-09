package de.alexandria.cms.backend.impl.repository.lucene;

/**
 * Thrown when a disaster happens in the underlying Lucene layer.
 */
public class LuceneException extends RuntimeException {

    public LuceneException(final String message) {
	super(message);
    }

    public LuceneException(final Throwable cause) {
	super(cause);
    }

    public LuceneException(final String msg, final Throwable cause) {
	super(msg, cause);
    }
}
