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
package de.jcup.code2doc.demo.documentation.output;

import java.io.File;

import de.jcup.code2doc.core.Factory;
import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.renderer.docbook.PDFSpecificationRenderer;
/**
 * Document builder
 * @author Albert Tregnaghi
 *
 */
public class DemoDocumentationOutput {

	public static void main(String[] args) throws Exception {
		new DemoDocumentationOutput().create();
	}

	void create() throws Exception {
		/* @formatter:off */
		
		Specification spec = Factory.createFilledSpecification();
		spec.
			setAuthor("Albert", "Tregnaghi").
			setPreface("This a internal-demo for <b>code2doc</b><p>code2doc stands for: COde to DOCumentation</p>"
					+ "<p>The demo project is a BLog application with different use cases.This document represents a system specification.</p>");
			
			
		/* @formatter:on */
		Factory.createDefaultValidator().validate(spec);
		
		PDFSpecificationRenderer renderer = new PDFSpecificationRenderer(spec,new File("build","code2doc-demo-inside-documentation.pdf"));
		File file = renderer.render();
		System.out.println("PDF output done into:"+file.getAbsolutePath());
		
	}
}