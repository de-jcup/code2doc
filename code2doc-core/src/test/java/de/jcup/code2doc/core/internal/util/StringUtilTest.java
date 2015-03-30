package de.jcup.code2doc.core.internal.util;

import static de.jcup.code2doc.core.internal.util.StringUtil.*;
import static org.junit.Assert.*;

import org.junit.Test;
public class StringUtilTest {

	@Test
	public void test_isEmpty(){
		assertTrue(isEmpty(null));
		assertTrue(isEmpty(""));
		assertTrue(isEmpty("   "));
		assertTrue(isEmpty("		"));
		
		assertFalse(isEmpty("a"));
		assertFalse(isEmpty("   a"));
	}
	
	@Test
	public void test_isNotEmpty(){
		assertFalse(isNotEmpty(null));
		assertFalse(isNotEmpty(""));
		assertFalse(isNotEmpty("   "));
		assertFalse(isNotEmpty("		"));
		
		assertTrue(isNotEmpty("a"));
		assertTrue(isNotEmpty("   a"));
	}
	
}
