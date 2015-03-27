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
package de.jcup.code2doc.demo.inside.documentation.usecase;

import de.jcup.code2doc.api.UseCase;

public class UC_13_SELECT_BLOG_LISTENTRY extends UseCase{

	@Override
	protected void doSetup(UseCaseSetup useCaseSetup) {
		useCaseSetup.setHeadline("Select blog from list");
		
		useCaseSetup.setDescription("The user does select a dedicated blog from list on UI. After the selection the blog is shown.");
		
		useCaseSetup.addAllRoles();
	}

}