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
package de.jcup.code2doc.demo.inside.documentation.usecase.lastbutnotleast;

import de.jcup.code2doc.api.UseCase;
import de.jcup.code2doc.demo.inside.documentation.security.RegistratedUserRole;
import de.jcup.code2doc.demo.inside.documentation.usecase.UseCaseUtil;

public class UC_11_UNREGISTER extends UseCase{

	@Override
	protected void doSetup(UseCaseSetup useCaseSetup) {
		useCaseSetup.setHeadline("Unregister");
		useCaseSetup.setDescription("User is able tor remove his complete account - so all data is lost when unregistration is done");
		useCaseSetup.setExactURL(UseCaseUtil.createURL("unregister"));
		
		useCaseSetup.addRole(RegistratedUserRole.class);
	}

}