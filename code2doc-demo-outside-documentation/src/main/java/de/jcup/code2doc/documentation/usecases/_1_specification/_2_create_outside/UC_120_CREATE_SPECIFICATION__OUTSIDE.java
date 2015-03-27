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
package de.jcup.code2doc.documentation.usecases._1_specification._2_create_outside;

import de.jcup.code2doc.api.UseCase;

public class UC_120_CREATE_SPECIFICATION__OUTSIDE extends UseCase {

	@Override
	protected void doSetup(UseCaseSetup useCaseSetup) {
		/*@formatter:off*/
		useCaseSetup.
			setHeadline("Create a new specification").
			setDescription("A developer creates a specification (means a modell).").
			content().
				addTextResource("UC_120_CREATE_SPECIFICATION_en.html").
				addCodeResource(CodeType.JAVA, "UC_120_CREATE_SPECIFICATION.java.example");
		/*@formatter:on*/
	}

}