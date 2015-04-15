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
package de.jcup.code2doc.documentation.usecases._2_define_elements;

import de.jcup.code2doc.api.UseCase;

public class UC_290_ADD_CONTENT extends UseCase{

	@Override
	protected void doSetup(UseCaseSetup setup) {
		setup.setHeadline("Add content");
		setup.setDescription("Inside an element a developer "
				+ "add additinal content information");
		
		setup.content().
			addText("via addText() it is possible to add a styled text information").
			addText("via addTextResource() it is possbile to add text from a file").
			addText("via addPictureResource() images can be added").
			addText("via addCode() code fragements can be added").
			addText("via addCodeResource() code fragments can be added from a file").
			openHeadlineContainer("Advanced parts").
				addText("via method \"openHeadlineContainer\" it is"
						+ " possible to create sub containers. "
						+ "These have to be closed by closeContainer() method").
			closeContainer().
			addCodeResource(CodeType.JAVA, "UC_290_ADD_CONTENT.java.example");
	}

}
