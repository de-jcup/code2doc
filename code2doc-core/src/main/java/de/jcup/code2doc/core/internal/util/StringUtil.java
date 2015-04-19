package de.jcup.code2doc.core.internal.util;
/**
 * To reduce dependencies to an absolute minimum we create our own string util here - with only necessary parts.
 * apache commons lang would be nice instead but many project have different dependencies to different library versions
 * - we want to avoid class path problems here...
 * @author Albert Tregnaghi
 *
 */
public class StringUtil {
	
	public static final String EMPTY="";

	/**
	 * Returns true when given string is null or has only white spaces
	 * @param string - to validate
	 * @return true when given string is null or has only white spaces
	 */
	public static boolean isEmpty(String string){
		if (string==null){
			return true;
		}
		if (string.trim().length()==0){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true when given string is not null and has not only white spaces
	 * @param string - to validate
	 * @return true when given string is not null and has not only white spaces
	 */
	public static boolean isNotEmpty(String string){
		return !isEmpty(string);
	}

	
}
