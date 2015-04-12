package de.jcup.code2doc.core.internal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.jcup.code2doc.core.internal.sort.ObjectByClassNameComparator;

public class ObjectByClassNameComparatorTest {


	@Test
	public void testSortingOfAnyObjectsNotBeingClassesItself(){
		/* compares string.class... which is both same...*/
		assertEquals(0, comparator.compare("albert","tregnaghi"));

		/*  de.jcup.code2doc.core... is lower than java.lang...*/
		assertTrue(comparator.compare(comparator, "string")<0 );
	}
	
	@Test
	public void testSortingOfAtLeastOneObjectIsAClassByItself(){
		/*  de.jcup.code2doc.core... is lower than java.lang...*/
		assertTrue("Classes must be compared not their class -which would be always same, but themself!", comparator.compare(ObjectByClassNameComparator.class, java.lang.String.class)<0 );
	}
	
	@Test
	public void testSortingOfNumbetrs(){
		assertTrue(comparator.compare(TestClass1.class, TestClass2.class) < 0);
		assertTrue(comparator.compare(TestClass2.class, TestClass1.class) > 0);
	}
	
	private ObjectByClassNameComparator comparator;
	
	@Before
	public void before(){
		comparator = new ObjectByClassNameComparator();
	}
	
	private class TestClass1{
		
	}
	
	private class TestClass2{
		
	}
}
