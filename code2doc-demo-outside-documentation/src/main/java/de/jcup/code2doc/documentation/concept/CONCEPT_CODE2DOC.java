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
package de.jcup.code2doc.documentation.concept;

import java.util.Locale;

import de.jcup.code2doc.api.Concept;

public class CONCEPT_CODE2DOC extends Concept{

	@Override
	protected void doSetup(ConceptSetup setup) {
		gemanLocale(setup);
		defaultLocale(setup);
	}

	private void defaultLocale(ConceptSetup setup) {
		/* @formatter:off */
		setup.setHeadline("code2doc concept");
		setup.setDescription("What is code2doc and what are the averages when using?");
		setup.content().addTextResource("code2docConcept_en.html");

		/* @formatter:on */
	}

	private void gemanLocale(ConceptSetup setup) {
		Locale locale = Locale.GERMAN;
		/* @formatter:off */
		setup.setHeadline("code2doc Konzept", locale);
		setup.setDescription("Was ist code2doc und welche Vorteile bietet die Verwendung?",locale);
		setup.content(locale).addText("code2docConcept_de.html");
		/* @formatter:on */
	}

}