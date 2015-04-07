/*
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.*/
package de.jcup.code2doc.core.internal.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TransformerTest {

	@Test
	public void test_to_ID() {
		assertEquals("java.lang.Object", transformer.transformToId(new Object()));
	}
	
	@Test
	public void test_to_string() {
		assertEquals("null", transformer.transformToString(null));
		assertEquals(getClass().getName(), transformer.transformToString(TransformerTest.class));
		assertEquals("InternalTestEnum.BETA", transformer.transformToString(InternalTestEnum.BETA));
		assertEquals("InternalTestEnumWithToString.X", transformer.transformToString(InternalTestEnumWithToString.X));
		assertEquals("InternalTestEnumWithToString.Y", transformer.transformToString(InternalTestEnumWithToString.Y));
		assertEquals("Casper",transformer.transformToString("Casper"));
		assertEquals("internal test", transformer.transformToString(new InternalTestClass()));
	}
	
	@Test
	public void test_to_tag() {
		assertEquals("null", transformer.transformToTag(null));
		assertEquals("Class", transformer.transformToTag(TransformerTest.class));
		assertEquals("InternalTestEnum", transformer.transformToTag(InternalTestEnum.BETA));
		assertEquals("String",transformer.transformToTag("Casper"));
		assertEquals("InternalTestClass", transformer.transformToTag(new InternalTestClass()));
	}

	
	private Transformer transformer;

	@Before
	public void before() {
		transformer = new Transformer();
	}
	
	private class InternalTestClass{
		@Override
		public String toString() {
			return "internal test";
		}
	}
	
	private enum InternalTestEnum{
		ALPHA,
		BETA
	}
	
	private enum InternalTestEnumWithToString{
		X("1"),
		Y("2");
		private String data;

		private InternalTestEnumWithToString(String data){
			this.data=data;
		}
		
		@Override
		public String toString() {
			return data;
		}
	}
}