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
package de.jcup.code2doc.demo.inside.documentation.architecture;

import de.jcup.code2doc.api.Architecture;

public class PersistenceConcept extends Architecture{

	public final static String GROUP_DAO_BASE="Common DAO parts";
	public final static String GROUP_DATA="Data";
	public final static String TYPE_QUERY="query";
	public final static String TYPE_DAO="dao";
	public final static String TYPE_ENTITY="entity";
	
	@Override
	protected void doSetup(ArchitectureSetup architectureSetup) {
		architectureSetup.setHeadline("Persistence concept");
		architectureSetup.setDescription("A short introducction about the used technologies and internal architecture parts of persistence.");
		architectureSetup.setWikiURL("http://en.wikipedia.org/wiki/Java_Persistence_API");
		/* @formatter:off*/
		architectureSetup.content().
			addPictureResource("About persistence architecture","/demo/architecture_persistence_picture.png").
			openHeadlineContainer("Technology").
				addText("<a href='http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html'>JPA</a> (java persistence api) is used for ORM.").
			closeContainer().
			openHeadlineContainer("Queries").
				addText("All queries are implemented via JPQL").
			closeContainer().
			
			openHeadlineContainer("DAOs").
				addText("The data access objects (DAOs) are used from for all entity access").
			closeContainer();
	}

	
}