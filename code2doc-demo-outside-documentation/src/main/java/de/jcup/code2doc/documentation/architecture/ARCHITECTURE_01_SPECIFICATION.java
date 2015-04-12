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
package de.jcup.code2doc.documentation.architecture;

import de.jcup.code2doc.api.Architecture;

public class ARCHITECTURE_01_SPECIFICATION extends Architecture{

	@Override
	protected void doSetup(ArchitectureSetup architectureSetup) {
		defaultLocale(architectureSetup);
	}

	private void defaultLocale(ArchitectureSetup setup) {
		/* @formatter:off*/
		setup.setHeadline("Specification");
		setup.setDescription("Specification (model) concept"); 
		setup.content().addText(
				"code2doc is based on a <b>Specification</b> which is internally the model for all generated outputs."+
				"<p>All elements are added to this model. A DSL like java based fluent API enables the developer to define additional information to the model.</p>"
				);
		
		/* @formatter:on*/
	}
	
}