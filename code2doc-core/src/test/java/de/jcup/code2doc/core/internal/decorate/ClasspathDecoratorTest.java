package de.jcup.code2doc.core.internal.decorate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import de.jcup.code2doc.core.define.TechnicalDefinition;
import de.jcup.code2doc.core.internal.collect.TechInfoLinkAnnotationData;
import de.jcup.code2doc.core.internal.collect.TestTechInfoLinkAnnotationData;

public class ClasspathDecoratorTest {

	
	
	@Test
	public void test_annotation_data_of_enumetion_is_handled_correctly() {
		TechInfoLinkAnnotationData techInfoData = new TestTechInfoLinkAnnotationData(InternalTestEnum.class, "group", null, getField(InternalTestEnum.class,
				InternalTestEnum.Test1.name()), "group");
		classpathDecorator.append(techInfoData, technicalDefinition);

		/* there must be the ENUM added */
		verify(technicalDefinition).addLinkToJava("group", InternalTestEnum.Test1);
	}

	/* -------------------------------- */
	/* Helper area */
	/* -------------------------------- */

	private ClasspathDecorator classpathDecorator;
	private TechnicalDefinition<Object> technicalDefinition;

	@SuppressWarnings("unchecked")
	@Before
	public void before() {
		classpathDecorator = new ClasspathDecorator();
		technicalDefinition = mock(TechnicalDefinition.class);
		when(technicalDefinition.addLinkToJava(any(String.class), any(InternalTestEnum.class))).thenReturn(technicalDefinition);
	}

	private Field getField(Class<?> clazz, String methodName) {
		Field f = null;
		try {
			f = clazz.getField(methodName);
		} catch (Exception e) {
			fail(e.getMessage());

		}
		return f;
	}

	private enum InternalTestEnum {
		Test1, Test2, Test3
	}

}
