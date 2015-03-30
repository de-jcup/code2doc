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

import java.io.ByteArrayOutputStream;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import de.jcup.code2doc.core.generator.Generator;
import de.jcup.code2doc.core.internal.util.TextStyle;

public class DocbookTextStyle extends TextStyle{
	/* we use newInstance() because newFactory is only available at a specific JDK6 update and we want to
	 * support every JRE 6 installation - so instead using newInstance()
	 */
	private XMLOutputFactory factory = XMLOutputFactory.newInstance();
	
	public String applyToImpl(String internalFormatText) {
		String escaped = escapeXml(internalFormatText);
		String styledContent = convertToDocbook(escaped);
		return styledContent;
	}

	private String escapeXml(String internalFormatText) {
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		try {
			XMLStreamWriter x = factory.createXMLStreamWriter(bas, Generator.ENCODING);
			x.writeCharacters(internalFormatText);
			x.close();
			String result = new String(bas.toByteArray(),Generator.ENCODING);
			return result;
		} catch (Exception e) {
			throw new RuntimeException("cannot escape xml", e);
		}
		
	}

	private String convertToDocbook(String text) {
		text = XHTMLReplace.B.replace(text, "<emphasis role='bold'>$1</emphasis>");
		/* normally no underline really available (but it works already on dookbook4j!?! with
		 * role attribute!
		 * potential:http://www.sagehill.nets/docbookxsl/CustomInlines.html
		 */
		text = XHTMLReplace.U.replace(text, "<emphasis role='underline'>$1</emphasis>");
		text = XHTMLReplace.I.replace(text, "<emphasis role='italic'>$1</emphasis>");
		text = XHTMLReplace.LI.replace(text, "<listitem>$1</listitem>");
		text = XHTMLReplace.UL.replace(text, "<itemizedlist mark='opencircle'>$1</itemizedlist>");
		text = XHTMLReplace.P.replace(text, "<para>$1</para>");
		text = XHTMLReplace.A.replace(text, "<ulink url='$11'>$2</ulink>");
		
		text = XHTMLReplace.BR.replace(text, "<?linebreak?>");
		return text;
	}
	
	
}