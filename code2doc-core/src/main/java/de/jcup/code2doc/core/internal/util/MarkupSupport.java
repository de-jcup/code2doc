package de.jcup.code2doc.core.internal.util;

import de.jcup.code2doc.api.Element.MarkupType;

/**
 * Interface for all kind of support for {@link MarkupType}.
 * @author de-jcup
 *
 */
public interface MarkupSupport {

	/**
	 * Returns the supported mark up type
	 * @return
	 */
	public MarkupType getMarkupType();
	
	/**
	 * Handle mark up for given text
	 * @param text
	 * @return handled text
	 */
	public String handleMarkup(String text);
}
