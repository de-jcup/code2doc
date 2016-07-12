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
package de.jcup.code2doc.core.define;

import static de.jcup.code2doc.testdata.TestPermission.*;

import org.junit.Test;

import de.jcup.code2doc.core.Factory;
import de.jcup.code2doc.testdata.TestQuery;
import de.jcup.code2doc.testdata.TestUseCases.UC_1__SHOW_ENTRIES;
import de.jcup.code2doc.testdata.TestUseCases.UC_2__DELETE_ENTRY;

public class SpecificationTest {

	@Test
	public void test_specification_interfaces_still_available(){
		Specification spec = Factory.createEmptySpecification();
		/* @formatter:off */
		spec.
			addUseCaseAndDefine(UC_1__SHOW_ENTRIES.class).
				addTechInfo().
					addLinkToJava("Permission", ARCHIVE_ENTRY).
					addLinkToJava("Query", TestQuery.class).
					addLinkToURL("xhtml", "localhost:9080/blubb.xhtml").
				endTechInfo().
				
			endDefinition().
			
			addUseCaseAndDefine(UC_2__DELETE_ENTRY.class).
			endDefinition();
		/* @formatter:on */
	}
	
}