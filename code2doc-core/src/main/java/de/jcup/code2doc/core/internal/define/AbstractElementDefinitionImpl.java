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

import java.util.Collection;
import java.util.TreeSet;

import de.jcup.code2doc.api.Element;
import de.jcup.code2doc.core.define.TechnicalDefinition;

/**
 * ElementDefinitionImpl
 * @author de-jcup
 *
 * @param <TECH_PARENT> - type of technology info parent
 * @param <ELEMENT> - element type
 * @param <PARENT> - parent type
 */
public abstract class AbstractElementDefinitionImpl<TECH_PARENT,ELEMENT extends Element,PARENT> {

	ELEMENT element;
	PARENT parent;
	private Collection<TechnicalDefinition<TECH_PARENT>> technicalDefinitions = new TreeSet<TechnicalDefinition<TECH_PARENT>>();

	AbstractElementDefinitionImpl(PARENT parent, ELEMENT element) {
		this.element = element;
		this.parent = parent;
	}

	/**
	 * get element
	 * @return element
	 */
	public ELEMENT getElement() {
		return element;
	}


	/**
	 * End this definition
	 *  @return parent
	 */
	public PARENT endDefinition() {
		return parent;
	}


	public TechnicalDefinition<TECH_PARENT> addTechInfo() {
		return addTechInfo("");
	}
	
	public abstract DefinitionType getDefinitionType();
	
	/**
	 * Adds a tech info
	 * @param group
	 * @return technical definition
	 */
	public TechnicalDefinition<TECH_PARENT> addTechInfo(String group) {
		String text = element.getHeadline();
		if (isNotEmpty(group)){
			text = text + " ("+group+")";
		}
		/* try to resolve technical definition for given group*/
		for (TechnicalDefinition<TECH_PARENT> techDef: getTechnicalDefinitions()){
			if (!(techDef instanceof AbstractTechnicalDefinitionImpl<?>)){
				throw new IllegalArgumentException("this kind of tech def is not supported:"+techDef);
			}
			AbstractTechnicalDefinitionImpl<TECH_PARENT> impl = (AbstractTechnicalDefinitionImpl<TECH_PARENT>) techDef;
			if (impl.getHeadline().equals(text)){
				return techDef;
			}
		}
		/* not found - add new one*/
		TechnicalDefinition<TECH_PARENT> impl = createNewTechnicalDefinition(text);
		technicalDefinitions.add(impl);
		return impl;
	}

	/**
	 * Implementation create a dedicated new technical definition with given text
	 * @param text
	 * @return technical definition
	 */
	protected abstract TechnicalDefinition<TECH_PARENT> createNewTechnicalDefinition(String text);
	
	/**
	 * Returns all technical definitions
	 * @return technical definitions
	 */
	public Collection<TechnicalDefinition<TECH_PARENT>> getTechnicalDefinitions() {
		return technicalDefinitions;
	}

}