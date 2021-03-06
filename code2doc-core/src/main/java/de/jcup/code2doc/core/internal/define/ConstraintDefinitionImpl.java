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

import de.jcup.code2doc.api.Constraint;
import de.jcup.code2doc.core.define.ConstraintDefinition;
import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.core.define.TechInfoDefinition;

public class ConstraintDefinitionImpl extends AbstractElementDefinitionImpl<ConstraintDefinition,Constraint,Specification> implements ConstraintDefinition, Comparable<ConstraintDefinitionImpl> {
	

	ConstraintDefinitionImpl(GroupDefinitionImpl groupDefinition, Constraint role) {
		super(groupDefinition.specification, role);
	}

	@Override
	protected TechInfoDefinition<ConstraintDefinition> createNewTechnicalDefinition(String text) {
		ConstraintTechnicalDefinitionImpl impl = new ConstraintTechnicalDefinitionImpl(this,text);
		return impl;
	}
	

	@Override
	public DefinitionType getDefinitionType() {
		return DefinitionType.CONSTRAINT;
	}

	@Override
	public int compareTo(ConstraintDefinitionImpl o) {
		String d1 = o.getElement().getDescription();
		String d0 = getElement().getDescription();
		
		if (d1==null){
			return 1;
		}
		if (d0==null){
			return -1;
		}
		return d1.compareTo(d0);
	}

}