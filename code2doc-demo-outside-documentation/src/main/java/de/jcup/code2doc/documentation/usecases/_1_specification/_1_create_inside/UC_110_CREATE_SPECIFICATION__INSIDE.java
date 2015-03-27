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
package de.jcup.code2doc.documentation.usecases._1_specification._1_create_inside;

import static de.jcup.code2doc.api.Element.CodeType.*;
import de.jcup.code2doc.api.UseCase;

public class UC_110_CREATE_SPECIFICATION__INSIDE extends UseCase {

	@Override
	protected void doSetup(UseCaseSetup useCaseSetup) {
		addDefaultLocale(useCaseSetup);
	}

	private void addDefaultLocale(UseCaseSetup useCaseSetup) {
		/*@formatter:off*/
		useCaseSetup.
			setHeadline("Create a new INSIDE specification").
			setDescription("A developer creates a specification for inside approach.").
			content().
				addTextResource("UC_110_CREATE_SPECIFICATION_en.html").
				addCodeResource(JAVA, "UC_110_CREATE_SPECIFICATION.java.example");
		/*@formatter:on*/
	}

}