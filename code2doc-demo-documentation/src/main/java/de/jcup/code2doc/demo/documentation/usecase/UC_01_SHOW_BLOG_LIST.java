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
package de.jcup.code2doc.demo.documentation.usecase;

import de.jcup.code2doc.api.UseCase;

public class UC_01_SHOW_BLOG_LIST extends UseCase{

	@Override
	protected void doSetup(UseCaseSetup useCaseSetup) {
		useCaseSetup.setHeadline("Show blog list");
		/* @formatter:off*/
		useCaseSetup.setDescription(
				"A list with all currently created blogs inside this system is shown "
				+"to user. No matter if the user is logged in or not."
				+"<p> </p>" /* example for an empty paragraph! Recommend the space between the elements - necessary to work!*/
				+"<p>(<i>Its pretty normal that blogs are read by anonymous users...</i>)</p>"
				/* next follows an example for an internal link */
				+"<p>There is another use case (<a href='code2doc://de.jcup.code2doc.demo.inside.documentation.usecase.UC_13_SELECT_BLOG_LISTENTRY'>UC_13_SELECT_BLOG_LISTENTRY</a>) which depends on this one.</p>"
				);
		/* @formatter:on*/

		useCaseSetup.setExactURL(UseCaseUtil.createURL("showAllBlogs"));
		useCaseSetup.addAllRoles();
		useCaseSetup.setExamplePicture("/demo/uc_01_example_picture.png");
	}

}