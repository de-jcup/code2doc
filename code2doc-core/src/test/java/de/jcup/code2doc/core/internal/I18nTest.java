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
package de.jcup.code2doc.core.internal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
public class I18nTest {

	private I18n i18n;
	
	
	@Test
	public void testCreateNotFoundResultWorks(){
		String result = i18n.createNotFoundResult("property");
		assertNotNull(result);
		/* replacement is always same - so do it again*/
		String result2 = i18n.createNotFoundResult("property");
		assertEquals(result, result2);
	}
	
	@Test
	public void testAllResourceIdentifiersAreAvailable(){
		for (ResourceIdentifier ri: Code2docCoreResourceIdentifiers.values()){
			String key = ri.getKey();
			assertKeyInResourceBundleFound(key);
		}
	}

	
	
	
	
	/* ---------------------------------- */
	/* ---------------------------------- */
	/* Helpers*/
	/* ---------------------------------- */
	/* ---------------------------------- */
	
	
	private void assertKeyInResourceBundleFound(String key) {
		String notFound = i18n.createNotFoundResult(key);
		String result = i18n.get(key);
		assertNotNull(result);
		if (result.equals(notFound)){
			System.out.println("missing key: \n"+key+"\n");
			fail("Key not in resource bundles:"+key+"\nSee also System.out to copy key");
		}
	}
	
	@Before
	public void before(){
		i18n = new I18n();
	}
}