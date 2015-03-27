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

import static junit.framework.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import de.jcup.code2doc.core.Factory;
import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.generator.docbook.DocbookStringSpecificationGenerator;
import de.jcup.code2doc.renderer.docbook.testdata.DocBookTestSpecificationBuilder;

public class PDFSpecificationRendererTest {

	@Test
	public void test_even_empty_spec_is_generateable() throws IOException{
		Specification spec = Factory.createEmptySpecification();
		PDFSpecificationRenderer renderer = new PDFSpecificationRenderer(spec);
		File value = renderer.render();
		assertNotNull(value);
		assertTrue(value.exists());
	}
	
	@Test
	public void test_full_spec_is_generateable() throws IOException{
		
		Specification spec = DocBookTestSpecificationBuilder.buildFullTestSpecification();
		PDFSpecificationRenderer renderer = new PDFSpecificationRenderer(spec);
		try{
			File value  = renderer.render();
			assertNotNull(value);
			assertTrue(value.exists());
			assertTrue(value.length()>100);
			System.out.println("file:"+value.getAbsolutePath());
			System.out.println();

		}catch(IOException e){
			String xml = new DocbookStringSpecificationGenerator().generate(spec);
			System.err.println(xml);
			throw e;
		}
	}
	
}