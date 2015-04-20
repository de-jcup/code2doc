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
package de.jcup.code2doc.documentation.usecases._1_specification;

import static de.jcup.code2doc.api.Element.CodeType.*;
import static de.jcup.code2doc.api.Element.MarkupType.*;
import de.jcup.code2doc.api.UseCase;
import de.jcup.code2doc.documentation.concept.CONCEPT_CODE2DOC;
import de.jcup.code2doc.documentation.roles.Roles;

public class UC_110_CREATE_SPECIFICATION__INSIDE extends UseCase {

	@Override
	protected void doSetup(UseCaseSetup setup) {
		/*@formatter:off*/
		setup.
			setHeadline("Create a INSIDE specification").
			setDescription("A developer creates a specification for inside approach.").
			
			addRole(Roles.DEVELOPER.class).
			
			
			content().
				addTextResource(HTML, "UC_110_CREATE_SPECIFICATION_en.html").
				addCodeResource(JAVA, "UC_110_CREATE_SPECIFICATION.java.example");
			
		setup.addLinkToConcept(CONCEPT_CODE2DOC.class);
		/*@formatter:on*/	}


}