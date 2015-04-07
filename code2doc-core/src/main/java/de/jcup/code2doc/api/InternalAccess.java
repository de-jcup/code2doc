package de.jcup.code2doc.api;

/**
 * Internal class - do not use from outside!
 * @author de-jcup
 * @deprecated - only for internal usage! Could be changed in future.
 */
public class InternalAccess {

	public static void setExampleURL(UseCase useCase, String exampleURL){
		useCase.exampleURL=exampleURL;
	}
}
