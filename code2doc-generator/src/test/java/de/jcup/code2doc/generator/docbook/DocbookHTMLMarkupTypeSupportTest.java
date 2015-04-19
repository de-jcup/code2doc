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
package de.jcup.code2doc.generator.docbook;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class DocbookHTMLMarkupTypeSupportTest {
	
	@Test
	public void test_linesep_makes_paragraphs(){
		String text  ="Test1<br/>Test2";
		assertEquals("Test1<?linebreak?>Test2", htmlMarkupSupport.handleMarkup(text));
	}
	
	@Test
	public void test_bold_italic_underline_styles(){
		assertEquals("<emphasis role='bold'>text</emphasis>",htmlMarkupSupport.handleMarkup("<b>text</b>"));
		assertEquals("<emphasis role='italic'>text</emphasis>",htmlMarkupSupport.handleMarkup("<i>text</i>"));
		assertEquals("<emphasis role='underline'>text</emphasis>",htmlMarkupSupport.handleMarkup("<u>text</u>"));
	}
	
	@Test
	public void test_xml_escaping(){
		assertEquals("&lt;para&gt;somehting&lt;/para&gt;",htmlMarkupSupport.handleMarkup("<para>somehting</para>"));
	}
	
	@Test
	public void test_listitems(){
		assertEquals("<listitem>item</listitem>",htmlMarkupSupport.handleMarkup("<li>item</li>"));
	}
	
	@Test
	public void test_unsorted_list(){
		assertEquals("<itemizedlist mark='opencircle'>x</itemizedlist>",htmlMarkupSupport.handleMarkup("<ul>x</ul>"));
	}
	
	@Test
	public void test_unsorted_list_with_entries(){
		assertEquals("<itemizedlist mark='opencircle'><listitem>item1</listitem><listitem>item2</listitem></itemizedlist>",htmlMarkupSupport.handleMarkup("<ul><li>item1</li><li>item2</li></ul>"));
	}
	
	@Test
	public void test_links_standard(){
		assertEquals("<ulink url='target'>content</ulink>",htmlMarkupSupport.handleMarkup("<a href='target'>content</a>"));
	}
	
	
	@Test
	public void test_links_internal(){
		/* target must be uppercased automatically - because IDs are always uppercased by transformer!*/
		assertEquals("<link linkend='targetUpper'>content</link>",htmlMarkupSupport.handleMarkup("<a href='code2doc://targetUpper'>content</a>"));
	}
	
	private DocbookHTMLMarkupTypeSupport htmlMarkupSupport;
	
	@Before
	public void before(){
		htmlMarkupSupport = new DocbookHTMLMarkupTypeSupport();
	}
}