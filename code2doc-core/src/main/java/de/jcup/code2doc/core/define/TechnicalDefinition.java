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



/**
 * Technical definition
 * @author Albert Tregnaghi
 *
 * @param <P> - parent of technical definition
 */
public interface TechnicalDefinition<P> extends Comparable<TechnicalDefinition<P>>{
	
	/**
	 * Add link to java class
	 * @param group - group name
	 * @param clazz - class
	 * @return definition
	 */
	public TechnicalDefinition<P> addLinkToJava(String group, Class<?> ...classes);
	
	/**
	 * Add link to java linkedMethod
	 * @param group - group name
	 * @param clazz - class
	 * @return definition
	 */
	public TechnicalDefinition<P> addLinkToJavaMethod(String group, Class<?> clazz, String methodName);
	
	
	/**
	 * Add link to java field name
	 * @param group - group name
	 * @param clazz - class 
	 * @return definition
	 */
	public TechnicalDefinition<P> addLinkToJavaField(String group, Class<?> clazz, String fieldName);
	
	/**
	 * Add link to java enumeration
	 * @param group - group name
	 * @param enums - one or more enumerations to add for group
	 * @return definition
	 */
	public <T extends Enum<T>> TechnicalDefinition<P> addLinkToJava(String group, T ... enums);
	
	/**
	 * Adds a link to an external URL
	 * @param group - group name
	 * @param url - url string 
	 * @return definition
	 */
	public TechnicalDefinition<P> addLinkToURL(String group, String ... urls);
	
	/**
	 * End
	 * @return tech info parent
	 */
	public P endTechInfo();
}