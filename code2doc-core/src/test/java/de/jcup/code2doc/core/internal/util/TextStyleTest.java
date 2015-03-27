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

public class TextStyleTest {

	@Test
	public void test_elements_mixed(){
		assertEquals("<para>albert<?linebreak?>tregnaghi</para>", ts.applyTo("<p>albert<br/>tregnaghi</p>"));
		assertEquals("<para>albert<?linebreak?>uuuxxxitem1xxxxxxitem2xxxuuutregnaghi</para>", ts.applyTo("<p>albert<br/><ul><li>item1</li><li>item2</li></ul>tregnaghi</p>"));
		/* do same with line breaks*/
		assertEquals("<para>albert<?linebreak?>\nuuuxxxitem1xxxxxxitem2xxxuuutregnaghi</para>", ts.applyTo("<p>albert<br/>\n<ul><li>item1</li><li>item2</li></ul>tregnaghi</p>"));
	}
	
	@Test
	public void test_elements_no_content(){
		assertEquals("albert<?linebreak?>tregnaghi", ts.applyTo("albert<br/>tregnaghi"));
	}

	public void testCaseInsensitive(){
		assertEquals("_*message*_", ts.applyTo("<b>message</b>"));
		assertEquals("_*message*_", ts.applyTo("<B>message</B>"));
		assertEquals("_*message*_", ts.applyTo("<b>message</B>"));
	}
	
	@Test
	public void test_no_argument(){
		
		assertEquals("_*message*_", ts.applyTo("<b>message</b>"));
		assertEquals("_XmessageX_", ts.applyTo("<i>message</i>"));
		assertEquals("_#message#_", ts.applyTo("<u>message</u>"));
	}
	
	@Test
	public void test_multiple_elements_no_argument(){
		assertEquals("_*message*__*message2*_", ts.applyTo("<b>message</b><b>message2</b>"));
	}
	
	@Test
	public void test_with_argument(){
		assertEquals("_Alinktarget link=message_A_", ts.applyTo("<a href='linktarget'>message</a>"));
	}
	
	private TextStyle ts;
	
	@Before
	public void before(){
		ts = new TextStyle() {

			@Override
			public String applyToImpl(String text) {
				text = XHTMLReplace.P.replace(text,"<para>$1</para>");
				text = XHTMLReplace.B.replace(text,"_*$1*_");
				text = XHTMLReplace.I.replace(text,"_X$1X_");
				text = XHTMLReplace.U.replace(text,"_#$1#_");
				text = XHTMLReplace.A.replace(text,"_A$1 link=$2_A_");
				text = XHTMLReplace.UL.replace(text, "uuu$1uuu");
				text = XHTMLReplace.LI.replace(text, "xxx$1xxx");
				text = XHTMLReplace.BR.replace(text,"<?linebreak?>");
				return text;
			}};
	}
	
}