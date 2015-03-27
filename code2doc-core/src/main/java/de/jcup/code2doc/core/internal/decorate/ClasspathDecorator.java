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
package de.jcup.code2doc.core.internal.decorate;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jcup.code2doc.api.Architecture;
import de.jcup.code2doc.api.Concept;
import de.jcup.code2doc.api.Constraint;
import de.jcup.code2doc.api.Element;
import de.jcup.code2doc.api.Role;
import de.jcup.code2doc.api.UseCase;
import de.jcup.code2doc.core.define.TechnicalDefinition;
import de.jcup.code2doc.core.internal.collect.ClasspathDataCollector;
import de.jcup.code2doc.core.internal.collect.TechInfoLinkAnnotationData;
import de.jcup.code2doc.core.internal.define.ElementDefinitionImpl;
import de.jcup.code2doc.core.internal.define.SpecificationImpl;

public class ClasspathDecorator extends AbstractSpecificationImplDecorator {

	private final static Logger LOG = LoggerFactory.getLogger(ClasspathDecorator.class);

	@Override
	protected void decorateImpl(SpecificationImpl specificationImpl) {
		if (LOG.isDebugEnabled()){
			LOG.debug("collect data");
		}
		ClasspathDataCollector dataCollector = new ClasspathDataCollector();
		if (LOG.isDebugEnabled()){
			LOG.debug("- collect all elements");
		}
		/* define concepts and usecases in specification by info from class path:*/
		for (Class<? extends Concept> clazz: dataCollector.collectAllConcepts()){
			specificationImpl.addConcept(clazz);
		}
		
		for (Class<? extends UseCase> clazz: dataCollector.collectAllUseCases()){
			specificationImpl.addUseCase(clazz);
		}
		
		for (Class<? extends Architecture> clazz: dataCollector.collectAllArchitectures()){
			specificationImpl.addArchitecture(clazz);
		}
		
		for (Class<? extends Role> clazz: dataCollector.collectAllRoles()){
			specificationImpl.addRole(clazz);
		}
		
		for (Class<? extends Constraint> clazz: dataCollector.collectAllConstraints()){
			specificationImpl.addConstraint(clazz);
		}
		
		
		
		if (LOG.isDebugEnabled()){
			LOG.debug("- collect all annotations");
		}
		appendTechInfoLinkAnnotationData(specificationImpl, dataCollector.collectLinkingToUseCases());
		appendTechInfoLinkAnnotationData(specificationImpl, dataCollector.collectLinkingToArchitectures());
		appendTechInfoLinkAnnotationData(specificationImpl, dataCollector.collectLinkingToConstraints());
		appendTechInfoLinkAnnotationData(specificationImpl, dataCollector.collectLinkingToRoles());
		
		/* constraint annotations*/
		
		
		if (LOG.isDebugEnabled()){
			LOG.debug("- DONE");
		}
	}
	

	/* currently having problems with generics in IDE so made map raw  ...*/
	@SuppressWarnings("rawtypes") 
	private void appendTechInfoLinkAnnotationData(SpecificationImpl specificationImpl, Map/*<Class<? extends Element>, List<TechInfoLinkAnnotationData>>*/ mapOfLinkToElement) {
		@SuppressWarnings("unchecked")
		Map<Class<? extends Element>, List<TechInfoLinkAnnotationData>> map = mapOfLinkToElement; 
		for (Class<? extends Element> elementClazzKey : map.keySet()) {
			ElementDefinitionImpl<?, ?, ?> useCaseDefinition = specificationImpl.getDefinition(elementClazzKey);
			if (useCaseDefinition == null) {
				continue;
			}
			List<TechInfoLinkAnnotationData> linkDataList = map.get(elementClazzKey);
			appendData(linkDataList, useCaseDefinition);

		}
	}

	private void appendData(List<TechInfoLinkAnnotationData> linkDataList, ElementDefinitionImpl<?, ?, ?> element) {
		if (linkDataList == null || linkDataList.isEmpty()) {
			return;
		}
		/* append data to definition */
		for (TechInfoLinkAnnotationData linkData : linkDataList) {
			Class<?> clazz = linkData.getLinkedClass();
			String group = linkData.getType();
			TechnicalDefinition<?> techInfo = element.addTechInfo(linkData.getTechInfoGroup());
			if (linkData.isFieldLink()) {
				techInfo.addLinkToJavaField(group, clazz, linkData.getLinkedField().getName());
			} else if (linkData.isMethodLink()) {
				techInfo.addLinkToJavaMethod(group, clazz, linkData.getLinkedMethod().getName());
			}else {
				techInfo.addLinkToJava(group, clazz);
			}

		}
	}

}