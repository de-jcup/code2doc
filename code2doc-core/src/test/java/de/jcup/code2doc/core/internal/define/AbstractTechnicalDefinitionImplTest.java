package de.jcup.code2doc.core.internal.define;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class AbstractTechnicalDefinitionImplTest {
	
	@Test
	public void test_adding_javafields_normal_way(){
		technicalDefinition.addLinkToJava(null, TestEnumInside.Alpha);
		
		Map<String, Collection<Object>> combined = technicalDefinition.getLinksCombined();
		assertNotNull(combined);
		assertEquals(1, combined.keySet().size());
		Collection<Object> data = combined.get(combined.keySet().iterator().next());
		assertNotNull(data);
		assertEquals(1, data.size());
		Object value = data.iterator().next();
		assertTrue(value instanceof TestEnumInside);
		assertEquals(TestEnumInside.Alpha, value);
	}
	
	@Test
	public void test_combination_of_links(){
		String id1 = "id1";
		technicalDefinition.addLinkToJava(id1, AbstractTechnicalDefinitionImpl.class);
		technicalDefinition.addLinkToJava(id1, TestEnumInside.Alpha, TestEnumInside.Beta);
		technicalDefinition.addLinkToJavaMethod(id1, AbstractTechnicalDefinitionImpl.class, "getHeadline");
		technicalDefinition.addLinkToJavaField(id1,AbstractTechnicalDefinitionImpl.class, "className");

		String id2 = "id2";
		technicalDefinition.addLinkToJava(id2, TestEnumInside.Gamma);
		technicalDefinition.addLinkToURL(id2, "http://www.jcup.de");
		
		Map<String, Collection<Object>> map = technicalDefinition.getLinksCombined();
		/* check all keys contained*/
		assertTrue(map.keySet().contains(id1));
		assertTrue(map.keySet().contains(id2));
		assertEquals(2,map.keySet().size());
		
		/* check content1*/
		Collection<Object> content1 = map.get(id1);
		String fieldNameUrl = technicalDefinition.createJavaFieldURL(AbstractTechnicalDefinitionImpl.class, "className");
		String methodNameUrl = technicalDefinition.createJavaMethodURL(AbstractTechnicalDefinitionImpl.class, "getHeadline");
		
		assertTrue(methodNameUrl+" must be found!", content1.contains(methodNameUrl));
		assertTrue(fieldNameUrl+" must be found!", content1.contains(fieldNameUrl));
		
		assertTrue(content1.contains(TestEnumInside.Alpha));
		assertTrue(content1.contains(TestEnumInside.Beta));
		
		assertEquals(5, content1.size());
		
		/* check content2*/
		Collection<Object> content2 = map.get(id2);
		assertEquals(2, content2.size());
		assertTrue(content2.contains(TestEnumInside.Gamma));
	}
	
	@Test
	public void test_ordering_of_keys(){
		for (int i=9;i>0;i--){
			technicalDefinition.addLinkToJava("beta-test-"+i, AbstractTechnicalDefinitionImpl.class);
			technicalDefinition.addLinkToJava("alpha-test-"+i, AbstractTechnicalDefinitionImpl.class);
		}
		Set<String> keys = technicalDefinition.getLinksCombined().keySet();
		Iterator<String> it = keys.iterator();
		for (int i=1;i<10;i++){
			assertEquals("alpha-test-"+i, it.next());
		}
		for (int i=1;i<10;i++){
			assertEquals("beta-test-"+i, it.next());
		}
	}
	

	@Test
	public void test_equals_implemented() {
		/* prepare additional data*/
		TestTechnicalDefinition technicalDefinition2 = new TestTechnicalDefinition(null, technicalDefinition.getHeadline());
		TestTechnicalDefinition technicalDefinition3 = new TestTechnicalDefinition(null, technicalDefinition.getHeadline()+"other");

		/* null never equal...*/
		assertFalse(technicalDefinition.equals(null));
		
		/* equal */
		assertEquals(technicalDefinition, technicalDefinition2);
		assertEquals(technicalDefinition2, technicalDefinition);
		/* not equal:*/
		assertFalse(technicalDefinition2.equals(technicalDefinition3));
		assertFalse(technicalDefinition.equals(technicalDefinition3));
		
	}

	@Test
	public void test_hashcode_implemented() {
		/* prepare additional data*/
		TestTechnicalDefinition technicalDefinition2 = new TestTechnicalDefinition(null, technicalDefinition.getHeadline());
		TestTechnicalDefinition technicalDefinition3 = new TestTechnicalDefinition(null, technicalDefinition.getHeadline()+"other");

		/* hashCode equals when having same headline */
		assertEquals(technicalDefinition.hashCode(), technicalDefinition2.hashCode());
		/* hash code not equal when not having same hedaline:*/
		assertNotSame(technicalDefinition2.hashCode(), technicalDefinition3.hashCode());
	}

	@Before
	public void before(){
		technicalDefinition= new TestTechnicalDefinition(null, "headline");
	}

	private AbstractTechnicalDefinitionImpl<?> technicalDefinition;

	private class TestTechnicalDefinition extends AbstractTechnicalDefinitionImpl<Object>{

		TestTechnicalDefinition(Object parent, String headline) {
			super(parent, headline);
		}

		
	}
	
	private enum TestEnumInside{
		Alpha,
		Beta,
		Gamma,
		Delta
	}
}
