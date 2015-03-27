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

import de.jcup.code2doc.core.define.TechnicalDefinition;

public class DummyTechnicalInfo<P> implements TechnicalDefinition<P>{

	private P parent;
	
	DummyTechnicalInfo(P parent){
		this.parent=parent;
	}

	@Override
	public TechnicalDefinition<P> addLinkToJava(String id, Class<?>... classes) {
		return this;
	}

	@Override
	public TechnicalDefinition<P> addLinkToJavaMethod(String id, Class<?> clazz, String methodName) {
		return this;
	}

	@Override
	public TechnicalDefinition<P> addLinkToJavaField(String id, Class<?> clazz, String fieldName) {
		return this;
	}


	@Override
	public TechnicalDefinition<P> addLinkToURL(String id, String... urls) {
		return this;
	}

	@Override
	public P endTechInfo() {
		return parent;
	}

	@Override
	public <T extends Enum<?>> TechnicalDefinition<P> addLinkToJava(String id, T... enums) {
		return this;
	}


}