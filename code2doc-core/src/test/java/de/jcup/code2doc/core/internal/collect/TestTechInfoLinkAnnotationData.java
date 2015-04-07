package de.jcup.code2doc.core.internal.collect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestTechInfoLinkAnnotationData extends TechInfoLinkAnnotationData{

	/**Extended constructor - only for testcasees
	 * @param linkedClass
	 * @param type
	 * @param linkedMethod
	 * @param linkedField
	 * @param group
	 */
	public TestTechInfoLinkAnnotationData(Class<?> linkedClass, String type, Method linkedMethod, Field linkedField, String group) {
		this.linkedClass = linkedClass;
		this.type = type;
		this.linkedMethod = linkedMethod;
		this.linkedField = linkedField;
		this.group = group;
	}
	
}
