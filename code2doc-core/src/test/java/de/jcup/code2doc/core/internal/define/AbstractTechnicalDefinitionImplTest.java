package de.jcup.code2doc.core.internal.define;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractTechnicalDefinitionImplTest {
	
	@Test
	public void test_adding_javafields_normal_way(){
		def.addLinkToJava(null, TestEnumInside.Alpha);
		
		Map<String, Collection<Object>> combined = def.getLinksCombined();
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
		def.addLinkToJava(id1, AbstractTechnicalDefinitionImpl.class);
		def.addLinkToJava(id1, TestEnumInside.Alpha, TestEnumInside.Beta);
		def.addLinkToJavaMethod(id1, AbstractTechnicalDefinitionImpl.class, "getHeadline");
		def.addLinkToJavaField(id1,AbstractTechnicalDefinitionImpl.class, "className");

		String id2 = "id2";
		def.addLinkToJava(id2, TestEnumInside.Gamma);
		def.addLinkToURL(id2, "http://www.jcup.de");
		
		Map<String, Collection<Object>> map = def.getLinksCombined();
		/* check all keys contained*/
		assertTrue(map.keySet().contains(id1));
		assertTrue(map.keySet().contains(id2));
		assertEquals(2,map.keySet().size());
		
		/* check content1*/
		Collection<Object> content1 = map.get(id1);
		String fieldNameUrl = def.createJavaFieldURL(AbstractTechnicalDefinitionImpl.class, "className");
		String methodNameUrl = def.createJavaMethodURL(AbstractTechnicalDefinitionImpl.class, "getHeadline");
		
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
			def.addLinkToJava("beta-test-"+i, AbstractTechnicalDefinitionImpl.class);
			def.addLinkToJava("alpha-test-"+i, AbstractTechnicalDefinitionImpl.class);
		}
		Set<String> keys = def.getLinksCombined().keySet();
		Iterator<String> it = keys.iterator();
		for (int i=1;i<10;i++){
			assertEquals("alpha-test-"+i, it.next());
		}
		for (int i=1;i<10;i++){
			assertEquals("beta-test-"+i, it.next());
		}
	}
	
	private AbstractTechnicalDefinitionImpl<Object> def;
	
	@Before
	public void before(){
		def = new AbstractTechnicalDefinitionImpl<Object>(null, "headline");
		
	}
	
	private enum TestEnumInside{
		Alpha,
		Beta,
		Gamma,
		Delta
	}
}
