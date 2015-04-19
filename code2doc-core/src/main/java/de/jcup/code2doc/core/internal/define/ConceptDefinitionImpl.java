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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import de.jcup.code2doc.api.Concept;
import de.jcup.code2doc.core.define.ConceptDefinition;
import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.core.define.TechnicalDefinition;

public class ConceptDefinitionImpl extends AbstractElementDefinitionImpl<ConceptDefinition,Concept, Specification> implements ConceptDefinition  {

	private GroupDefinitionImpl groupDefinition;
	Set<UseCaseDefinitionImpl> linkedUseCases = new HashSet<UseCaseDefinitionImpl>();

	ConceptDefinitionImpl(GroupDefinitionImpl groupDefinition, Concept architectureConcept) {
		super(groupDefinition.specification, architectureConcept);
		this.groupDefinition=groupDefinition;
	}

	@Override
	protected TechnicalDefinition<ConceptDefinition> createNewTechnicalDefinition(String text) {
		return new DummyTechnicalInfo<ConceptDefinition>(this);
	}
	
	public GroupDefinitionImpl getGroupDefinition() {
		return groupDefinition;
	}
	
	public Collection<UseCaseDefinitionImpl> getLinkedUseCasesSorted(){
		ArrayList<UseCaseDefinitionImpl> list = new  ArrayList<UseCaseDefinitionImpl>(linkedUseCases);
		Collections.sort(list);
		return list;
	}

	@Override
	public DefinitionType getDefinitionType() {
		return DefinitionType.CONCEPT;
	}
}