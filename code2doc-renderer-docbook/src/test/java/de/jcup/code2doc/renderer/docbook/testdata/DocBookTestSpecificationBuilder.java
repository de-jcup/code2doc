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
package de.jcup.code2doc.renderer.docbook.testdata;

import java.io.IOException;

import de.jcup.code2doc.core.Factory;
import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.renderer.docbook.testdata.DocBookTestUseCases.UC_1__SHOW_ENTRIES;
import de.jcup.code2doc.renderer.docbook.testdata.DocBookTestUseCases.UC_2__DELETE_ENTRY;

public class DocBookTestSpecificationBuilder {

	public static Specification buildFullTestSpecification() throws IOException{
		/* @formatter:off*/
		Specification spec = Factory.createEmptySpecification();
		spec.
		setTitle("Test title").
		setPreface("Preface text").
		setAuthor("Firstname", "Lastname").
		setLegalNotice("a legal notice").
			addUseCaseAndDefine(UC_1__SHOW_ENTRIES.class).
				addTechInfo().
					addLinkToJava("example classes", DocBookExampleClass1.class,DocBookTestConstraints.class).
				endTechInfo().
				addTechInfo("special techinfo").
					addLinkToURL("URL", "http://www.google.de").
				endTechInfo().
			endDefinition().
			
			addUseCaseAndDefine(UC_2__DELETE_ENTRY.class).
			endDefinition();
		
		/* @formatter:on*/
		return spec;
	}
}