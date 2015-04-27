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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
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
import de.jcup.code2doc.core.define.ArchitectureDefinition;
import de.jcup.code2doc.core.define.ConceptDefinition;
import de.jcup.code2doc.core.define.ConstraintDefinition;
import de.jcup.code2doc.core.define.GroupDefinition;
import de.jcup.code2doc.core.define.RoleDefinition;
import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.core.define.UseCaseDefinition;
import de.jcup.code2doc.core.internal.sort.ElementDefinitionImplByClassNameComparator;
import de.jcup.code2doc.core.internal.util.Validation;


public class GroupDefinitionImpl implements GroupDefinition, Comparable<GroupDefinitionImpl> {
	private static final ElementDefinitionImplByClassNameComparator SHARED_COMPARATOR = new ElementDefinitionImplByClassNameComparator();
	private static final Logger LOG = LoggerFactory.getLogger(GroupDefinitionImpl.class); 
	private static final GroupDefinitionImpl typeInstanceDummyParentGroup = new GroupDefinitionImpl(null, null);
	
	SpecificationImpl specification;
	@SuppressWarnings("rawtypes")
	/**
	 * Used only to have a mapping for already created element definitions
	 */
	private  Map<Class<Element>, AbstractElementDefinitionImpl> elementToelementDefininitionMap = new HashMap<Class<Element>, AbstractElementDefinitionImpl>();
	
	List<UseCaseDefinitionImpl> useCaseDefinitions = new ArrayList<UseCaseDefinitionImpl>();
	List<ArchitectureDefinitionImpl> architectureDefinitions = new ArrayList<ArchitectureDefinitionImpl>();
	List<ConceptDefinitionImpl> conceptDefinitions = new ArrayList<ConceptDefinitionImpl>();
	List<RoleDefinitionImpl> roleDefinitions = new ArrayList<RoleDefinitionImpl>();
	List<ConstraintDefinitionImpl> constraintDefinitions = new ArrayList<ConstraintDefinitionImpl>();
	
	private Group group;
	private static ConceptDefinitionCreator CONCEPT_DEF_CREATOR = new ConceptDefinitionCreator();
	private static UseCaseDefinitionCreator USECASE_DEF_CREATOR = new UseCaseDefinitionCreator();
	private static RoleDefinitionCreator ROLE_DEF_CREATOR = new RoleDefinitionCreator();
	private static ConstraintDefinitionCreator CONSTRAINT_DEF_CREATOR = new ConstraintDefinitionCreator();
	private static ArchitectureDefinitionCreator ARCHITECTURE_DEF_CREATOR = new ArchitectureDefinitionCreator();
	/**
	 * Do not call this constructor from outside - only
	 * specification.addGroup(..) is valid - because the type is registered to
	 * the spec!
	 * 
	 * @param specification
	 * @param type
	 */
	GroupDefinitionImpl(SpecificationImpl specification, Group group) {
		this.specification = specification;
		this.group = group;
	}

	public GroupDefinition addUseCase(Class<? extends UseCase> useCase) {
		addUseCaseAndDefine(useCase);
		return this;
	}

	public UseCaseDefinition addUseCaseAndDefine(Class<? extends UseCase> useCase) {
		return (UseCaseDefinition) internalAddElement(useCase, USECASE_DEF_CREATOR,this);
	}

	@Override
	public GroupDefinition addArchitecture(Class<? extends Architecture> architecture) {
		addArchitectureAndDefine(architecture);
		return this;
	}

	@Override
	public ArchitectureDefinition addArchitectureAndDefine(Class<? extends Architecture> architecture) {
		return (ArchitectureDefinition) internalAddElement(architecture, ARCHITECTURE_DEF_CREATOR,this);
	}
	
	@Override
	public GroupDefinition addConcept(Class<? extends Concept> concept) {
		addConceptAndDefine(concept);
		return this;
	}
	
	@Override
	public ConceptDefinition addConceptAndDefine(Class<? extends Concept> concept) {
		return (ConceptDefinition) internalAddElement(concept, CONCEPT_DEF_CREATOR,this);
	}
	
	
	@Override
	public GroupDefinition addConstraint(Class<? extends Constraint> concept) {
		addConstraintAndDefine(concept);
		return this;
	}

	@Override
	public ConstraintDefinition addConstraintAndDefine(Class<? extends Constraint> concept) {
		return (ConstraintDefinition) internalAddElement(concept, CONSTRAINT_DEF_CREATOR,this);
	}

	@Override
	public GroupDefinition addRole(Class<? extends Role> concept) {
		addRoleAndDefine(concept);
		return this;
	}

	@Override
	public RoleDefinition addRoleAndDefine(Class<? extends Role> concept) {
		return (RoleDefinition) internalAddElement(concept, ROLE_DEF_CREATOR,this);
	}

	@Override
	public Specification endDefinition() {
		return specification;
	}

	/**
	 * Get all element definitions sorted
	 * @param definitionType
	 * @return collection with all element definitions
	 * @throws IllegalArgumentException when definition type not supported on not in enum 
	 * @throws IllegalArgumentException when definition type is null
	 */
	public Collection<? extends AbstractElementDefinitionImpl<?,?,?>> getDefinitionsSorted(String definitionType) {
		Validation.notNull(definitionType, "Definition may not be null");
		DefinitionType resolvedDefinitionType = DefinitionType.valueOf(definitionType);
		List<? extends AbstractElementDefinitionImpl<?, ?, ?>> list = internalFindCorrectList(resolvedDefinitionType);
		return createSortedList(list);
	}
	
	/**
	 * Get all element definitions 
	 * @param definitionType - must be one of the names defined in {@link DefinitionType}
	 * @return collection with all element definitions 
	 * @throws IllegalArgumentException when definition type not supported on not in enum
	 * @throws IllegalArgumentException when definition type is null
	 */
	public Collection<? extends AbstractElementDefinitionImpl<?,?,?>> getDefinitions(String definitionType) {
		Validation.notNull(definitionType, "Definition may not be null");
		DefinitionType type = DefinitionType.valueOf(definitionType);
		return getDefinitions(type);
	}
	
	/**
	 * Get all element definitions - for every definition type
	 * @return unmodifiable list of all element definitions
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<? extends AbstractElementDefinitionImpl<?, ?, ?>> getDefinitions(){
		/* simply add every definition type to fetch all */
		List<? extends AbstractElementDefinitionImpl<?, ?, ?>> result = new ArrayList<AbstractElementDefinitionImpl<?,?,?>>();
		for (DefinitionType type: DefinitionType.values()){
			List<? extends AbstractElementDefinitionImpl<?, ?, ?>> allOfType = internalFindCorrectList(type);
			result.addAll((Collection)(allOfType)); /* without cast to raw type not working ... */
		}
		return Collections.unmodifiableList(result);
	}
	
	/**
	 * Get all definitions for given definition type - not sorted
	 * @param definitionType - may not be null
	 * @return unmodifiable list for given definition type - never null
	 * @throws IllegalArgumentException when unsupported definition type
	 */
	public List<? extends AbstractElementDefinitionImpl<?,?,?>> getDefinitions(DefinitionType definitionType) {
		Validation.notNull(definitionType,  "Defintion type must be set - or use getDefinitions() instead!");
		List<? extends AbstractElementDefinitionImpl<?, ?, ?>> list = internalFindCorrectList(definitionType);
		return Collections.unmodifiableList(list);
	}

	public Group getGroup() {
		return group;
	}

	@Override
	public int compareTo(GroupDefinitionImpl other) {
		if (other == null) {
			return 1;
		}
		if (other == this) {
			return 0;
		}
		return group.compareTo(other.group);
	}

	@Override
	public String toString() {
		return "GroupDefinitionImpl:"+ getGroup().toString();
	}

	/**
	 * Returns all children of current type
	 * @return
	 */
	public Collection<GroupDefinitionImpl> getChildrenSorted() {

		Collection<GroupDefinitionImpl> allGroupDefinitions = specification.getGroupDefinitions();
		List<GroupDefinitionImpl> children = new ArrayList<GroupDefinitionImpl>();
		Group pgp = getGroup();
		for (GroupDefinitionImpl impl : allGroupDefinitions) {
			if (impl == this) {
				continue;
			}

			Group igp = impl.getGroup().getParent();
			if (pgp == igp) {
				children.add(impl);
			}
		}
		/*
		 * return children in same sort order as defined in Groups by Factory
		 * calls
		 */
		Collections.sort(children);
		return children;

	}

	
	/* @formatter:on*/
	
	List<? extends AbstractElementDefinitionImpl<?,?,?>> createSortedList(List<? extends AbstractElementDefinitionImpl<?,?,?>>list ){
		List<? extends AbstractElementDefinitionImpl<?,?,?>> sortedList = new ArrayList<AbstractElementDefinitionImpl<?,?,?>>(list);
		Collections.sort(sortedList,SHARED_COMPARATOR);
		return sortedList;
	}

	/*
	 * Internal method to fetch correct list - will return the real list - public caller methods should always return a unmodifiable version...
	 * 
	 */
	private List<? extends AbstractElementDefinitionImpl<?, ?, ?>> internalFindCorrectList(DefinitionType definitionType) {
		List<? extends AbstractElementDefinitionImpl<?,?,?>> list = null;
		if (definitionType==DefinitionType.USECASE){
			list= useCaseDefinitions;
		}else if (definitionType==DefinitionType.ARCHITECTURE){
			list= architectureDefinitions;
		}else if (definitionType==DefinitionType.CONCEPT){
			list= conceptDefinitions;
		}else if (definitionType==DefinitionType.ROLE){
			list= roleDefinitions;
		}else if (definitionType==DefinitionType.CONSTRAINT){
			list= constraintDefinitions;
		}
		assertListAvailable(definitionType, list);
		return list;
	}

	private void assertListAvailable(DefinitionType definitionType, List<? extends AbstractElementDefinitionImpl<?, ?, ?>> list) {
		if (list==null){
			throw new IllegalArgumentException("definition type unsupported - no list found for::"+definitionType);
		}
	}

	/* hmm... currently no generics are used here - but we know what we do here. so normally not necessary. effort with generic approach too great */
	@SuppressWarnings({"unchecked","rawtypes"})
	/* @formatter:off*/
	private AbstractElementDefinitionImpl internalAddElement(
				Class<? extends Element> elementClazz,
				ElementDefinitionCreator definitionCreator, 
				GroupDefinitionImpl parentContainer) {
		DefinitionType defType = definitionCreator.getType();
		List list = (List) internalFindCorrectList(defType);
		if (elementToelementDefininitionMap.containsKey(elementClazz))	{
			LOG.debug("key already exists. So reuse it.");
			return elementToelementDefininitionMap.get(elementClazz);
		}
		/* create instance*/
		Element elementInstance = specification.transformer.transformToInstance(elementClazz);
		AbstractElementDefinitionImpl  elementDef = definitionCreator.createDefinitionFor(elementInstance, parentContainer);
		list.add(elementDef);
		elementToelementDefininitionMap.put((Class)elementClazz, elementDef);
		if (LOG.isDebugEnabled()){
			String listName = null;
			if (list ==architectureDefinitions){
				listName="architecture";
			}else if (list ==conceptDefinitions){
				listName="concept";
			}else if (list ==useCaseDefinitions){
				listName="usecase";
			}else if (list ==roleDefinitions){
				listName="role";
			}else if (list ==constraintDefinitions){
				listName="constraint";
			}else{
				listName="unknown-list";
			}
			LOG.debug("Group '"+parentContainer.getGroup().getName()+"'+="+elementDef.getDefinitionType()+"("+elementClazz.getSimpleName()+").   map("+elementToelementDefininitionMap.size()+"), list("+listName+","+list.size()+")");
		}
		return elementDef;
	}


	private interface ElementDefinitionCreator{
		public AbstractElementDefinitionImpl<?, ?, ?> createDefinitionFor(Element element, GroupDefinitionImpl parentContainerOfDefinition);
		
		public DefinitionType getType();
	}

	private static abstract class AbstractElementDefinitionCreator implements ElementDefinitionCreator{
		
		/*
		 * An internal instance only for fetching type...
		 */
		private AbstractElementDefinitionImpl<?, ?, ?> typeInstance;
		
		@Override
		public DefinitionType getType() {
			if (typeInstance==null){
				/* typeInstanceDummyParentGroup - avoids NPEs...*/
				typeInstance=createDefinitionFor(null,typeInstanceDummyParentGroup);
			}
			return typeInstance.getDefinitionType();
		}
	}

	private static class UseCaseDefinitionCreator extends AbstractElementDefinitionCreator{

		@Override
		public UseCaseDefinitionImpl createDefinitionFor(Element element, GroupDefinitionImpl parentContainerOfDefinition) {
			return new UseCaseDefinitionImpl(parentContainerOfDefinition, (UseCase)element); 
		}

	}
	
	private static class ConceptDefinitionCreator extends AbstractElementDefinitionCreator{

		@Override
		public ConceptDefinitionImpl createDefinitionFor(Element element, GroupDefinitionImpl parentContainerOfDefinition) {
			return new ConceptDefinitionImpl(parentContainerOfDefinition, (Concept)element); 
		}

	}
	
	private static class ArchitectureDefinitionCreator extends AbstractElementDefinitionCreator{

		@Override
		public ArchitectureDefinitionImpl createDefinitionFor(Element element, GroupDefinitionImpl parentContainerOfDefinition) {
			return new ArchitectureDefinitionImpl(parentContainerOfDefinition, (Architecture)element); 
		}

	}
	
	private static class RoleDefinitionCreator extends AbstractElementDefinitionCreator{

		@Override
		public RoleDefinitionImpl createDefinitionFor(Element element, GroupDefinitionImpl parentContainerOfDefinition) {
			return new RoleDefinitionImpl(parentContainerOfDefinition, (Role)element);
		}

	}
	
	private static class ConstraintDefinitionCreator extends AbstractElementDefinitionCreator{

		@Override
		public ConstraintDefinitionImpl createDefinitionFor(Element element, GroupDefinitionImpl parentContainerOfDefinition) {
			return new ConstraintDefinitionImpl(parentContainerOfDefinition, (Constraint)element);
		}

	}
	
}