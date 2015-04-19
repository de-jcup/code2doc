package de.jcup.code2doc.core.decorate;

import de.jcup.code2doc.api.Element.MarkupType;

public interface MarkupTextDecorator extends Decorator<String>{

	
	/**
	 * Decorates the given text for markup {@link MarkupType#getDefault()}
	 */
	public String decorate(String text);

	/**
	 * Decorates given text.
	 * @param markup - markup type used in text
	 * @param text - the text
	 * @return decorated text
	 */
	public String decorate(MarkupType markup, String text);
}
