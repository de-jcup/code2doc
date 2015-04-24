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
package de.jcup.code2doc.core.internal.define;

import de.jcup.code2doc.api.Architecture;
import de.jcup.code2doc.core.define.ArchitectureDefinition;
import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.core.define.TechInfoDefinition;

public class ArchitectureDefinitionImpl extends AbstractElementDefinitionImpl<ArchitectureDefinition,Architecture, Specification> implements ArchitectureDefinition  {
	

	ArchitectureDefinitionImpl(GroupDefinitionImpl groupDefinition, Architecture architecture) {
		super(groupDefinition.specification, architecture);
	}

	@Override
	protected TechInfoDefinition<ArchitectureDefinition> createNewTechnicalDefinition(String text) {
		ArchitectureTechnicalDefinitionImpl impl = new ArchitectureTechnicalDefinitionImpl(this,text);
		return impl;
	}

	@Override
	public DefinitionType getDefinitionType() {
		return DefinitionType.ARCHITECTURE;
	}
}