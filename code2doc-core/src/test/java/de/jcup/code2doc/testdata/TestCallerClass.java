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

import de.jcup.code2doc.api.LinkToConstraint;
import de.jcup.code2doc.api.LinkToRole;
import de.jcup.code2doc.api.LinkToUseCase;
import de.jcup.code2doc.testdata.TestUseCases.UC_1__SHOW_ENTRIES;
public class TestCallerClass {

	@LinkToUseCase(UC_1__SHOW_ENTRIES.class) /* test case for linkedMethod handling!*/
	public void showList(){
		
	}

	@LinkToRole(TestRoles.LOCAL_ADMIN.class)
	public void doSomethingOnlyLocalAdminIsAllowed(){
		/* simple an example to link to local admin role */
	}
	
	public enum InternalConstrainingparts{
		@LinkToConstraint(TestConstraints.ONLY_FOR_HOME_ORG.class)
		MYCONSTRAINT1,
		@LinkToConstraint(TestConstraints.ONLY_WHEN_LEGAL.class)
		MYCONSTRAINT2,
	}

}