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
package de.jcup.code2doc.core.internal.collect;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import de.jcup.code2doc.api.Architecture;
import de.jcup.code2doc.api.Concept;
import de.jcup.code2doc.api.Constraint;
import de.jcup.code2doc.api.LinkToArchitecture;
import de.jcup.code2doc.api.LinkToConstraint;
import de.jcup.code2doc.api.LinkToRole;
import de.jcup.code2doc.api.LinkToUseCase;
import de.jcup.code2doc.api.Role;
import de.jcup.code2doc.api.UseCase;

/**
 * Collector for information about code2doc annotations used in target projects.
 * 
 * @author Albert Tregnaghi
 *
 */
public class ClasspathDataCollector {

	private Reflections reflections;
	private TechInfoLinkAnnotationDataCollector<Architecture, LinkToArchitecture> architectureDataCollector;
	private TechInfoLinkAnnotationDataCollector<Role, LinkToRole> roleDataCollector;
	private TechInfoLinkAnnotationDataCollector<Constraint, LinkToConstraint> constrainteDataCollector;
	private TechInfoLinkAnnotationDataCollector<UseCase, LinkToUseCase> useCaseDataCollector;
	
	public ClasspathDataCollector(){
		/* @formatter:off*/
		reflections = 
		new Reflections(
					new ConfigurationBuilder().
						filterInputsBy(new FilterBuilder().
									includePackage("").
									/* exclude JAVA itself*/
									excludePackage("java").
									excludePackage("javax").
									/* exclude code2doc itself*/
									excludePackage("de.jcup.code2doc.api").
									excludePackage("de.jcup.code2doc.core").
									excludePackage("de.jcup.code2doc.generator")
									).
						setUrls(ClasspathHelper.forClassLoader()).
						setScanners(
									new SubTypesScanner(), 
									new TypeAnnotationsScanner(), 
									new MethodAnnotationsScanner(), 
									new FieldAnnotationsScanner()));
		/* @formatter:on*/
		architectureDataCollector = new TechInfoLinkAnnotationDataCollector<Architecture, LinkToArchitecture>(LinkToArchitecture.class, reflections);
		useCaseDataCollector = new TechInfoLinkAnnotationDataCollector<UseCase, LinkToUseCase>(LinkToUseCase.class, reflections);
		roleDataCollector = new TechInfoLinkAnnotationDataCollector<Role, LinkToRole>(LinkToRole.class, reflections);
		constrainteDataCollector = new TechInfoLinkAnnotationDataCollector<Constraint, LinkToConstraint>(LinkToConstraint.class, reflections);
	
	}
	/* ----------------------*/
	/* - Annotation linking -*/
	/* ----------------------*/
	
	public Map<Class<? extends UseCase>, List<TechInfoLinkAnnotationData>> collectLinkingToUseCases() {
		return useCaseDataCollector.collectLinkingToElement();
	}
	
	public Map<Class<? extends Architecture>, List<TechInfoLinkAnnotationData>> collectLinkingToArchitectures() {
		return architectureDataCollector.collectLinkingToElement();
	}
	
	public Map<Class<? extends Role>, List<TechInfoLinkAnnotationData>> collectLinkingToRoles() {
		return roleDataCollector.collectLinkingToElement();
	}
	
	public Map<Class<? extends Constraint>, List<TechInfoLinkAnnotationData>> collectLinkingToConstraints() {
		return constrainteDataCollector.collectLinkingToElement();
	}
	
	
	/* ----------------------*/
	/* - Fetch all  elements */
	/* ----------------------*/
	
	
	public Set<Class<? extends UseCase>> collectAllUseCases(){
		return reflections.getSubTypesOf(UseCase.class);
	}
	
	public Set<Class<? extends Architecture>> collectAllArchitectures(){
		return reflections.getSubTypesOf(Architecture.class);
	}
	
	public Set<Class<? extends Concept>> collectAllConcepts(){
		return reflections.getSubTypesOf(Concept.class);
	}
	
	public Set<Class<? extends Role>> collectAllRoles(){
		return reflections.getSubTypesOf(Role.class);
	}
	
	public Set<Class<? extends Constraint>> collectAllConstraints(){
		return reflections.getSubTypesOf(Constraint.class);
	}
	
}