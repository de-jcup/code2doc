package de.jcup.code2doc.core.internal.validate;

import de.jcup.code2doc.api.Element;
import de.jcup.code2doc.core.validate.ValidationException;

public class NotAnElementValidationException extends ValidationException{

	private static final long serialVersionUID = -1099678379546177471L;

	public NotAnElementValidationException(Class<?> clazz) {
		super(clazz+" is not a code2doc element but is expected to be one. See "+Element.class.getName());
	}

}
