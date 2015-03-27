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
package de.jcup.code2doc.api;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import de.jcup.code2doc.api.Element.Content;
import de.jcup.code2doc.api.Element.HeadlineContainer;
import de.jcup.code2doc.api.Element.PictureContentResource;
import de.jcup.code2doc.api.Element.TextContent;

public class ElementTest {
	
	@Test
	public void test_resourcpath_building__without_slash(){
		String resourcePath = exampleElement.prepareResourcePath("test.png");
		assertEquals("/de/jcup/code2doc/api/test.png", resourcePath);
	}
	
	@Test
	public void test_resourcpath_building__with_slash(){
		String resourcePath = exampleElement.prepareResourcePath("/de/somewhere/test.png");
		assertEquals("/de/somewhere/test.png", resourcePath);
	}
	

	@Test
	public void test_when_nothing_defined_even_nullsafe(){
		/* no locale given */
		assertNotNull(exampleElement.getDescription());
		assertNotNull(exampleElement.getDescription(Locale.GERMAN));

		assertNotNull(exampleElement.getHeadline());
		assertNotNull(exampleElement.getHeadline(Locale.GERMAN));
		
		assertNotNull(exampleElement.getContent());
		assertNotNull(exampleElement.getContent(Locale.GERMAN));
		
	}
	

	@Test
	public void test_when_defined_values_are_safed(){
		/* no locale given */
		exampleElement.setup.setDescription("full1");
		exampleElement.setup.setDescription("voll1",Locale.GERMAN);
		exampleElement.setup.setDescription("full2",Locale.ENGLISH);
		
		assertEquals("full1", exampleElement.getDescription());
		assertEquals("voll1", exampleElement.getDescription(Locale.GERMAN));
		assertEquals("full2", exampleElement.getDescription(Locale.ENGLISH));
		
	}
	
	@Test
	public void test_childrelation_okay(){
		/* @formatter:off*/
		exampleElement.setup.
			content().openHeadlineContainer("headline1").
							addText("text1").
							addPictureResource("title1","/resourcePath1").
					  closeContainer().
					  addText("text2");
		/* @formatter:on*/
		List<Content> list = exampleElement.getContent().getChildren();
		assertEquals(2,  list.size());
		
		HeadlineContainer element1 = (HeadlineContainer) list.get(0);
		assertEquals("headline1",element1.getHeadline());
		List<Content> headlineChildren = element1.getChildren();
		assertEquals(2,headlineChildren.size());
		TextContent text = (TextContent) headlineChildren.get(0);
		assertEquals("text1",text.getText());
		PictureContentResource picture = (PictureContentResource) headlineChildren.get(1);
		assertEquals("/resourcePath1",picture.getResourcePath());
		
		TextContent textContent = (TextContent) list.get(1);
		assertEquals("text2",textContent.getText());
		
	}
	

	private TestExampleElement exampleElement;
	
	@Before
	public void before(){
		exampleElement = new TestExampleElement();
	}
}