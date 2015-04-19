package de.jcup.code2doc.core.internal.util;

import de.jcup.code2doc.core.internal.util.AbstractHTMLMarkupTypeSupport.XHTMLReplace;

/**
 * A simple java doc output generator for the lazy developer... - to avoid typeing new elements...
 * @author de-jcup
 *
 */
public class AbstractHTMLMarkupSupportJavaDocOutputGenerator {

	public static void main(String[] args) {
		/* javadoc output generation */
		System.out.println("* <ol>");
		for (XHTMLReplace r : XHTMLReplace.values()) {
			String name = r.name().toLowerCase();
			System.out.println("* <li>&lt" + name + (r.attribute != null ? " " + r.attribute + "='...'" : "") + "&gtcontent&lt/" + name + "&gt</li>");
		}
		System.out.println("* </ol>");
	}
}
