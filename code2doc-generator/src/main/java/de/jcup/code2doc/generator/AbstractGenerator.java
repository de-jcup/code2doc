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
package de.jcup.code2doc.generator;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import static de.jcup.code2doc.core.internal.util.StringUtil.*;
import de.jcup.code2doc.core.filter.Filter;
import de.jcup.code2doc.core.generator.Generator;
import de.jcup.code2doc.core.internal.util.Transformer;


public abstract class AbstractGenerator<RESULT,MODEL> implements Generator<RESULT,MODEL>{
	
	protected Map<String,String> params = new TreeMap<String,String>();
	protected Transformer transformer = new Transformer();
	private Filter filter = Filter.NONE;

	public AbstractGenerator() {
		super();
	}

	@Override
	public void setFilter(Filter filter) {
		if (filter == null) {
			this.filter = Filter.NONE;
		}else{
			this.filter=filter;
		}
		
	}
	
	protected Filter getFilter() {
		return filter;
	}

	@Override
	public final void setLanguage(Locale locale) {
		setParameter("language", locale.getLanguage());
		transformer.setLocale(locale);
	}

	/**
	 * Sets a parameter by type value
	 * @param name
	 * @param value - if null former value will be removed
	 */
	public void setParameter(String name, String value) {
		if (name==null){
			return;
		}
		if (value==null){
			params.remove(name);
			return;
		}
		params.put(name, value);
	}

	/**
	 * Get mandatory parameter by name
	 * @param name - name of parameter
	 * @return
	 * @throws IllegalArgumentException if parameter is not set
	 */
	protected String getMandatoryParameter(String name) {
		String value = params.get(name);
		if (isEmpty(value)){
			throw new IllegalArgumentException("Mandatory parameter "+name+" is NOT set!");
		}
		return value;
	}

	/**
	 * Method can be overriden by generators if they want a special transformator behaviour - e.g. a html generator
	 * may collect transformation data because images etc. must be copied into a resource folder at end...
	 * @return
	 */
	protected Transformer getTransformer() {
		return transformer;
	}

}