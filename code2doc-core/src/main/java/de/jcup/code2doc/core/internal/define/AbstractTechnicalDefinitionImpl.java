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

import static de.jcup.code2doc.core.internal.util.StringUtil.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import de.jcup.code2doc.core.define.TechInfoDefinition;

public abstract class AbstractTechnicalDefinitionImpl<TECH_PARENT> implements TechInfoDefinition<TECH_PARENT>{

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
		count += linkToJavaClasses.size();
		count += linkToJavaEnums.size();
		count += linkToURLs.size();
		return count>0;
	}
	

	@Override
	public TechInfoDefinition<TECH_PARENT> addLinkToJava(String id, Class<?> ... classes) {
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
	public TechInfoDefinition<TECH_PARENT> addLinkToJavaField(String id, Class<?> clazz, String fieldName) {
		if (isEmpty(id)){
			id="fields";
		}
		addLinkToURL(id, createJavaFieldURL(clazz, fieldName));
		return this;
	}

	@Override
	public TechInfoDefinition<TECH_PARENT> addLinkToJavaMethod(String id, Class<?> clazz, String methodName) {
		if (isEmpty(id)){
			id="methods";
		}
		addLinkToURL(id, createJavaMethodURL(clazz, methodName));
		return this;
	}

	@Override
	public <T extends Enum<T>> TechInfoDefinition<TECH_PARENT> addLinkToJava(String id, T... enums) {
		if (isEmpty(id)){
			id="enums";
		}
		Collection<Enum<?>> x = ensuredCollection(linkToJavaEnums, id);
		for (Enum<?> enum_: enums ){
			x.add(enum_);
		}
		return this;
	}
	

	@Override
	public TechInfoDefinition<TECH_PARENT> addLinkToURL(String id, String ...urls) {
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
	public int compareTo(TechInfoDefinition<TECH_PARENT> other) {
		if (other==null){
			return 1;
		}
		if (! (other instanceof AbstractTechnicalDefinitionImpl)){
			/* not comparable handle as same level*/
			return 0;
		}
		AbstractTechnicalDefinitionImpl<?> otherImpl=(AbstractTechnicalDefinitionImpl<?>) other;
		String otherHeadline = otherImpl.getHeadline();
		if (otherHeadline==null){
			if (headline==null){
				return 0;
			}
			return 1;
		}
		if (headline==null){
			return -1;
		}
		return headline.compareTo(otherHeadline);
	}
	
	

	@Override
	public String toString() {
		return "TechInfo [headline=" + headline + ", className=" + className + "]";
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

	public Map<String, Collection<Object>> getLinksCombined(){
		Map<String, Collection<Object>> map = new TreeMap<String, Collection<Object>>();
		for (String key: linkToJavaClasses.keySet()){
			ensuredCollection(map, key).addAll(linkToJavaClasses.get(key));
		}
		for (String key: linkToJavaEnums.keySet()){
			ensuredCollection(map, key).addAll(linkToJavaEnums.get(key));
		}
		for (String key: linkToURLs.keySet()){
			ensuredCollection(map, key).addAll(linkToURLs.get(key));
		}
		return map;
	}
	
	
	String createJavaFieldURL(Class<?> clazz, String fieldName) {
		return clazz.getSimpleName()+"."+fieldName;
	}

	String createJavaMethodURL(Class<?> clazz, String methodName) {
		return clazz.getSimpleName()+"#"+methodName+"(...)";
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