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
import de.jcup.code2doc.demo.inside.documentation.usecase.UseCaseUtil;

public class UC_02_SHOW_ENTRY_LIST extends UseCase{

	@Override
	protected void doSetup(UseCaseSetup useCaseSetup) {
		/*@formatter:off*/
		useCaseSetup.setHeadline("Show entries in list");
		
		useCaseSetup.setExampleURL(UseCaseUtil.createURL("showAllEntries","blog","alberts-test-blog"));
		
		useCaseSetup.addAllRoles();
		
		useCaseSetup.content().
				openHeadlineContainer("Why entries are visible for everybody").
					addText("A blog must shall be read - and many user do not want to register only for having access to information. They want to keep anonymous!").
					addText("Otherwise the blog viewer will not be used very often...").
					closeContainer();
	
		/*@formatter:on*/
	}

}