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
package de.jcup.code2doc.testdata;

import de.jcup.code2doc.api.UseCase;


public class TestUseCases {

	public static class UC_1__SHOW_ENTRIES extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup s) {
			s.setDescription("User can show entries in a list.");
			s.setHeadline("Show entries");
			
			s.addLinkToConcept(TestConcept.class);
		}
		
	};
	
	
	public static class UC_2__DELETE_ENTRY extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup s) {
			s.setHeadline("Delete entries");
			s.setDescription("User can delete entries from list.");
			s.addLinkToConcept(TestConcept.class);
		}
		
	};
	
	public static class UC_3__EDIT_ENTRY extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup s) {
			s.setHeadline("Edit entry");
			s.setDescription("User can edit entry.");
			
			s.addRole(TestRoles.LOCAL_ADMIN.class, TestConstraints.ONLY_FOR_HOME_ORG.class);
		}
		
	};
	
	/**
	 * Special test use case which is NEVER linked - so link collecting negative test possible
	 * @author de-jcupIGNA
	 *
	 */
	public static class UC_666_NEVER_LINKED extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup s) {
			s.setHeadline("Never linked");
			
		}
		
	};
}