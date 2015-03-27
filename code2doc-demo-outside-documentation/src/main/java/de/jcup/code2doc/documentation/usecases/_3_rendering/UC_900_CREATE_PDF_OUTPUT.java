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
package de.jcup.code2doc.documentation.usecases._3_rendering;

import de.jcup.code2doc.api.UseCase;

public class UC_900_CREATE_PDF_OUTPUT extends UseCase {

	@Override
	protected void doSetup(UseCaseSetup useCaseSetup) {
		useCaseSetup.setHeadline("Create PDF output");
		useCaseSetup.setDescription("The user creates a PDF from a specification");
		
		/* @formatter:off */
		useCaseSetup.content().
			addText("Developers simply create class with a main method and use a PDF file generator.").
			addCodeResource(CodeType.JAVA, "UC_900_CREATE_PDF_OUTPUT.java.example");
		
		/* @formatter:on */
		
	}

}