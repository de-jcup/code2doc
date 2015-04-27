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
package de.jcup.code2doc.documentation.output;

import java.io.File;

import de.jcup.code2doc.core.Factory;
import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.documentation.SpecificationBuilder;
import de.jcup.code2doc.renderer.docbook.PDFSpecificationRenderer;

/**
 * We use code2doc itself as target project for external approach. We do generate
 * the complete documentation here.
 * 
 * @author Albert Tregnaghi
 *
 */
public class Code2DocDocumentationOutput {

	public static void main(String[] args) throws Exception {
		new Code2DocDocumentationOutput().create(false);
	}

	void create(boolean keepTempFiles) throws Exception {
		if (keepTempFiles){
			System.setProperty("code2doc.renderer.docbook.keep_temporary_files", "true");
		}
		Specification spec = new SpecificationBuilder().start();
		Factory.createDefaultValidator().validate(spec);
		
		PDFSpecificationRenderer renderer = new PDFSpecificationRenderer(spec, new File("build","code2doc-documentation.pdf"));
		File file = renderer.render();
		System.out.println("generated to:" + file.getAbsolutePath());
	}

}