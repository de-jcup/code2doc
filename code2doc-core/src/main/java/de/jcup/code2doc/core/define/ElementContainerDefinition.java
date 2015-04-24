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
	 * Add a use case to this container
	 * @param useCase - use case class
	 * @return container
	 */
	public T addUseCase(Class<? extends UseCase> useCase);
	
	/**
	 * Adds a use case and starts defining use case in detail
	 * @param useCase
	 * @return
	 */
	public UseCaseDefinition addUseCaseAndDefine(Class<? extends UseCase> useCase);
	
	
	public T addArchitecture(Class<? extends Architecture> architecture);
	public ArchitectureDefinition addArchitectureAndDefine(Class<? extends Architecture> architecture);
	
	public T addConcept(Class<? extends Concept> concept);
	public ConceptDefinition addConceptAndDefine(Class<? extends Concept> concept);
	

	public T addConstraint(Class<? extends Constraint> concept);
	public ConstraintDefinition addConstraintAndDefine(Class<? extends Constraint> concept);
	
	public T addRole(Class<? extends Role> concept);
	public RoleDefinition addRoleAndDefine(Class<? extends Role> concept);
	
}