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
package de.jcup.code2doc.documentation.usecases._2_define_elements;

import de.jcup.code2doc.api.UseCase;

public class UC_220_CREATE_USECASE extends UseCase {

	@Override
	protected void doSetup(UseCaseSetup useCaseSetup) {
		/*@formatter:off*/
		useCaseSetup.
			setHeadline("Create a usecase").
			setDescription("A developer creates a use case. This is done by creating a class which extends UseCase").
			content().addCode(CodeType.JAVA,
				"public class UC_1_SHOW_ITEM extends UseCase{\n"+
			    "   /* setup method with dedicated setup object */\n"+
			    "   protected void doSetup(UseCaseSetup useCaseSetup){\n"+
				"        useCaseSetup.setHeadline(\"Show item\");\n"+
				"        useCaseSetup.setDescription(\"The user opens an item, the item is shown.\");\n"+
				"   }\n"+
				"}\n"
				);
		/*@formatter:on*/
	}
}