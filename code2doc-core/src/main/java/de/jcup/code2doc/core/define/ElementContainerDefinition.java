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

import de.jcup.code2doc.api.Architecture;
import de.jcup.code2doc.api.Concept;
import de.jcup.code2doc.api.Constraint;
import de.jcup.code2doc.api.Role;
import de.jcup.code2doc.api.UseCase;

/**
 * Implementations are element containers where dedicated elements can be added /defined
 * @author Albert Tregnaghi
 *
 * @param <T>
 */
public interface ElementContainerDefinition<T> extends Definition{

	/**
	 * Add a use case to this container - if already added the add will be ignored
	 * @param useCase - use case class
	 * @return container
	 */
	public T addUseCase(Class<? extends UseCase> useCase);
	
	/**
	 * Adds a use case - if already added the former found will be reused - and define additional use case details
	 * @param useCase - use case class
	 * @return use case definition
	 */
	public UseCaseDefinition addUseCaseAndDefine(Class<? extends UseCase> useCase);
	
	/**
	 * Add an architecture to this container - if already added the add will be ignored
	 * @param architecture - architecture class
	 * @return container
	 */
	public T addArchitecture(Class<? extends Architecture> architecture);
	
	/**
	 * Adds an architecture  - if already added the former found will be reused - and define additional architecture details
	 * @param architecture - architecture class
	 * @return architecture definition
	 */
	public ArchitectureDefinition addArchitectureAndDefine(Class<? extends Architecture> architecture);
	
	/**
	 * Add a concept to this container - if already added the add will be ignored
	 * @param concept - concept class
	 * @return container
	 */
	public T addConcept(Class<? extends Concept> concept);
	
	/**
	 * Adds a concept  - if already added the former found will be reused - and define additional concept details
	 * @param concept - concept class
	 * @return concept definition
	 */
	public ConceptDefinition addConceptAndDefine(Class<? extends Concept> concept);
	
	/**
	 * Add a constraint to this container - if already added the add will be ignored
	 * @param constraint - constraint class
	 * @return container
	 */
	public T addConstraint(Class<? extends Constraint> concept);
	
	/**
	 * Adds a constraint  - if already added the former found will be reused - and define additional concept details
	 * @param constraint - constraint class
	 * @return constraint definition
	 */
	public ConstraintDefinition addConstraintAndDefine(Class<? extends Constraint> concept);
	
	/**
	 * Add a role to this container - if already added the add will be ignored
	 * @param concept - concept class
	 * @return container 
	 */
	public T addRole(Class<? extends Role> concept);
	
	/**
	 * Adds a role  - if already added the former found will be reused - and define additional role details
	 * @param role - role class
	 * @return role definition
	 */
	public RoleDefinition addRoleAndDefine(Class<? extends Role> concept);
	
}