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

public class UC_230_CREATE_ARCHITECTURE extends UseCase {

	@Override
	protected void doSetup(UseCaseSetup useCaseSetup) {
		/*@formatter:off*/
		useCaseSetup.
			setHeadline("Create an architecture").
			setDescription("A developer creates an architecture part. This is done by creating a class which extends Architecture").
			content().addCode(CodeType.JAVA,
					"public class Synchronization_LDAP_TO_DB extends Architecture{\n"+
				    "   /* setup method with dedicated setup object */\n"+
				    "   protected void doSetup(ArchitectureSetup architectureSetup){\n"+
					"        architectureSetup.setHeadline(\"Synch from LDAP to database\");\n"+
					"        architectureSetup.setDescription(\"This documentation part describes the synchronization mechanism from LDAP to database\");\n"+
					"   }\n"+
					"}\n"
					);
		/*@formatter:on*/
	}
}