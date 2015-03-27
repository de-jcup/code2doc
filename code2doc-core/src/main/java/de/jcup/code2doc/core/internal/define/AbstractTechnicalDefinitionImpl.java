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

import static org.apache.commons.lang.StringUtils.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import de.jcup.code2doc.core.define.TechnicalDefinition;

public class AbstractTechnicalDefinitionImpl<TECH_PARENT> implements TechnicalDefinition<TECH_PARENT>{

	private TECH_PARENT parent;
	
	private Map<String, Collection<Class<?>>> linkToJavaClasses = new TreeMap<String, Collection<Class<?>>>();
	private Map<String, Collection<Enum<?>>> linkToJavaEnums = new TreeMap<String, Collection<Enum<?>>>();
	private Map<String, Collection<String>> linkToURLs= new TreeMap<String, Collection<String>>();

	private String headline;

	private String className;

	AbstractTechnicalDefinitionImpl(TECH_PARENT parent, String headline){
		this.parent=parent;
		this.headline=headline;
		this.className=getClass().getName();
	}
	
	public String getHeadline() {
		return headline;
	}
	
	public boolean isContainingLinks(){
		int count =0;
		count += getLinkToJavaClasses().size();
		count += getLinkToJavaEnums().size();
		count += getLinkToURLs().size();
		
		return count>0;
	}
	

	@Override
	public TechnicalDefinition<TECH_PARENT> addLinkToJava(String id, Class<?> ... classes) {
		if (isEmpty(id)){
			id="classes";
		}
		Collection<Class<?>> x = ensuredCollection(linkToJavaClasses, id);
		for (Class<?> clazz: classes ){
			x.add(clazz);
		}
		return this;
	}
	
	@Override
	public TechnicalDefinition<TECH_PARENT> addLinkToJavaField(String id, Class<?> clazz, String fieldName) {
		if (isEmpty(id)){
			id="fields";
		}
		addLinkToURL(id, clazz.getSimpleName()+"."+fieldName);
		return this;
	}

	
	@Override
	public TechnicalDefinition<TECH_PARENT> addLinkToJavaMethod(String id, Class<?> clazz, String methodName) {
		if (isEmpty(id)){
			id="methods";
		}
		addLinkToURL(id, clazz.getSimpleName()+"#"+methodName+"(...)");
		return this;
	}
	
	@Override
	public <T extends Enum<?>> TechnicalDefinition<TECH_PARENT> addLinkToJava(String id, T... enums) {
		Collection<Enum<?>> x = ensuredCollection(linkToJavaEnums, id);
		for (Enum<?> enum_: enums ){
			x.add(enum_);
		}
		return this;
	}
	

	@Override
	public TechnicalDefinition<TECH_PARENT> addLinkToURL(String id, String ...urls) {
		Collection<String> x = ensuredCollection(linkToURLs, id);
		for (String url: urls){
			x.add(url);
		}
		return this;
	}

	@Override
	public TECH_PARENT endTechInfo() {
		return parent;
	}


	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((headline == null) ? 0 : headline.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		AbstractTechnicalDefinitionImpl other = (AbstractTechnicalDefinitionImpl) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (headline == null) {
			if (other.headline != null)
				return false;
		} else if (!headline.equals(other.headline))
			return false;
		return true;
	}

	public Map<String, Collection<Class<?>>> getLinkToJavaClasses() {
		return linkToJavaClasses;
	}
	
	public Map<String, Collection<Enum<?>>> getLinkToJavaEnums() {
		return linkToJavaEnums;
	}
	
	public Map<String, Collection<String>> getLinkToURLs() {
		return linkToURLs;
	}
	
	
	private <T> Collection<T> ensuredCollection(Map<String, Collection<T>> map, String key){
		Collection<T> data = map.get(key);
		if (data==null){
			data = new ArrayList<T>();
			map.put(key, data);
		}
		return data;
	}

}