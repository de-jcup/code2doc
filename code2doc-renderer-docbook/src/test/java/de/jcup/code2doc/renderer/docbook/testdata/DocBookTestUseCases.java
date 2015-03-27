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

import de.jcup.code2doc.api.UseCase;


public class DocBookTestUseCases {

	public static UseCase[] getAllUseCases(){
		return new UseCase[]{
				new UC_1__SHOW_ENTRIES(),
				new UC_2__DELETE_ENTRY()
		};
	}

	public static class UC_1__SHOW_ENTRIES extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup useCaseSetup) {
			useCaseSetup.setDescription("User can show entries in a list.");
			useCaseSetup.setHeadline("Show entries");
		}
		
	};
	
	
	public static class UC_2__DELETE_ENTRY extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup useCaseSetup) {
			useCaseSetup.setHeadline("Delete entries");
			useCaseSetup.setDescription("User can delete entries from list.");
		}
		
	};
	
	public static class UC_3__EDIT_ENTRY extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup useCaseSetup) {
			useCaseSetup.setHeadline("Edit entry");
			useCaseSetup.setDescription("User can edit entry.");
			
			useCaseSetup.addRole(DocBookTestRoles.LOCAL_ADMIN.class);
		}
		
	};
}