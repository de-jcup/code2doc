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
package de.jcup.code2doc.demo.inside.documentation.usecase.entries;

import de.jcup.code2doc.api.UseCase;
import de.jcup.code2doc.demo.inside.documentation.security.AuthorRole;
import de.jcup.code2doc.demo.inside.documentation.security.BlogIsOwnedByUserConstraint;

public class UC_05_EDIT_ENTRY extends UseCase{

	@Override
	protected void doSetup(UseCaseSetup useCaseSetup) {
		useCaseSetup.setHeadline("Edit an entry");
		useCaseSetup.setDescription("User edits an existing entry. Changes are automatically logged into audit log");
		
		useCaseSetup.addRole(AuthorRole.class, BlogIsOwnedByUserConstraint.class);
	}

}