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



public interface TechnicalDefinition<P> {
	
	/**
	 * Add link to java class
	 * @param id
	 * @param classes
	 * @return definition
	 */
	public TechnicalDefinition<P> addLinkToJava(String id, Class<?> ...classes);
	
	/**
	 * Add link to java linkedMethod
	 * @param id
	 * @param classes
	 * @return definition
	 */
	public TechnicalDefinition<P> addLinkToJavaMethod(String id, Class<?> clazz, String methodName);
	
	
	/**
	 * Add link to java fieldname
	 * @param id
	 * @param classes
	 * @return definition
	 */
	public TechnicalDefinition<P> addLinkToJavaField(String id, Class<?> clazz, String fieldName);
	
	/**
	 * Add link to java enumeration
	 * @param id
	 * @param enums
	 * @return definition
	 */
	public <T extends Enum<?>> TechnicalDefinition<P> addLinkToJava(String id, T ... enums);
	
	/**
	 * Adds a link to an external URL
	 * @param id
	 * @param url
	 * @return definition
	 */
	public TechnicalDefinition<P> addLinkToURL(String id, String ... urls);
	
	/**
	 * End
	 * @return
	 */
	public P endTechInfo();
}