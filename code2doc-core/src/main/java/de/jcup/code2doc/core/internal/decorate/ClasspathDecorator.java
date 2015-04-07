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

import java.lang.reflect.Field;
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
import de.jcup.code2doc.core.internal.util.Validation;

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
	void appendTechInfoLinkAnnotationData(SpecificationImpl specificationImpl, Map/*<Class<? extends Element>, List<TechInfoLinkAnnotationData>>*/ mapOfLinkToElement) {
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

	void appendData(List<TechInfoLinkAnnotationData> linkDataList, ElementDefinitionImpl<?, ?, ?> element) {
		if (linkDataList == null || linkDataList.isEmpty()) {
			return;
		}
		/* append data to definition */
		for (TechInfoLinkAnnotationData linkData : linkDataList) {
			TechnicalDefinition<?> techInfo = element.addTechInfo(linkData.getTechInfoGroup());
			append(linkData, techInfo);
		}
	}

	@SuppressWarnings(value={"unchecked", "rawtypes"})
	void  append(TechInfoLinkAnnotationData linkData,  TechnicalDefinition<?> techInfo) {
		String group = linkData.getType();
		Class<?> clazz = linkData.getLinkedClass();
		Validation.notNull(clazz, "clazz may not be null");
		if (linkData.isFieldLink()) {
			/* when the field is an enum - we must check if its an enum value - if so we add it with dedicated method */
			Field field = linkData.getLinkedField();
			if (clazz.isEnum()){
				if (field.isEnumConstant()){
					String name = field.getName();
					Validation.notNull(name, "field name must be set!");
					/* because of http://bugs.java.com/bugdatabase/view_bug.do?bug_id=6369605 we must use a reduced part because a generic approach fails for JAVA 6!*/
					Class workaroundForJavaBug = clazz;
					Enum enumValue = Enum.valueOf(workaroundForJavaBug, name);
					techInfo.addLinkToJava(group, enumValue);
				}
			}else{
				techInfo.addLinkToJavaField(group, clazz, field.getName());
			}
		} else if (linkData.isMethodLink()) {
			techInfo.addLinkToJavaMethod(group, clazz, linkData.getLinkedMethod().getName());
		}else {
			techInfo.addLinkToJava(group, clazz);
		}
	}

}