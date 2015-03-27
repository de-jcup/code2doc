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
package de.jcup.code2doc.renderer.docbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.google.code.docbook4j.renderer.HTMLRenderer;
import com.google.code.docbook4j.renderer.PDFRenderer;
import com.google.code.docbook4j.renderer.RTFRenderer;

/***
 * Dieser Beispielgenerator generiert ein PDF aus dem Beispiel für
 * die Beispiel-Usecase-Spezifikation!
 * 
 * Dadurch kann einfach ein docbook als Beispiel "gebastelt" werden.
 * https://github.com/bigpuritz/docbook4j
 * @author de-jcup
 *
 */
public class ExamplePDFGenerator {
	
	
	public static void main(String[] args) throws IOException {
		new ExamplePDFGenerator().generateImpl();
	}

	protected void generateImpl() {
		String xml = "";
		xml = "res:docbook-example-04.xml";
//		xml = "res:docbook-example-05-article.xml";
		xml = "res:docbook-example-06-article.xml";
		xml = "res:example-specification.xml";
		PDFRenderer pdfRenderer = PDFRenderer.create(xml);
		pdfRenderer.parameter("toc.section.depth","5");
		try {
			InputStream in = pdfRenderer.render();
			File newFile = new File("C:\\temp\\output_generated.pdf");
			FileOutputStream out = new FileOutputStream(newFile);
			IOUtils.copy(in,out);
			System.out.println("DONE:"+newFile.getAbsolutePath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RTFRenderer rtfRenderer = RTFRenderer.create(xml);
		rtfRenderer.parameter("toc.section.depth","5");
		
		try {
			InputStream in = rtfRenderer.render();
			File newFile = new File("C:\\temp\\output_generated.rtf");
			FileOutputStream out = new FileOutputStream(newFile);
			IOUtils.copy(in,out);
			System.out.println("DONE:"+newFile.getAbsolutePath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HTMLRenderer htmlRenderer = HTMLRenderer.create(xml);
		htmlRenderer.parameter("toc.section.depth","5");
		
		try {
			InputStream in = htmlRenderer.render();
			File newFile = new File("C:\\temp\\output_generated.html");
			FileOutputStream out = new FileOutputStream(newFile);
			IOUtils.copy(in,out);
			System.out.println("DONE:"+newFile.getAbsolutePath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}