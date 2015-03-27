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


public interface UseCaseDefinition {

	/**
	 * Adds technological info with element headline as default headline - if already added the existing definition is returned
	 * @return technological info with element headline as default headline - if already added the existing definition is returned
	 */
	public TechnicalDefinition<? extends  UseCaseDefinition> addTechInfo();
	
	/**
	 * Adds technological info with custom headline instead of default name - if already added the existing definition is returned
	 * @param headline
	 * @return technological info with custom headline instead of default name - if already added the existing definition is returned
	 */
	public TechnicalDefinition<? extends UseCaseDefinition> addTechInfo(String headline);
	
	/**
	 * End definition and return to group
	 * @return group def
	 */
	public Specification endDefinition();
	
}