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

import static de.jcup.code2doc.core.internal.util.Validation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import de.jcup.code2doc.core.define.RoleDefinition;
import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.core.define.UseCaseDefinition;
import de.jcup.code2doc.core.internal.Code2docCoreResourceIdentifiers;
import de.jcup.code2doc.core.internal.I18n;
import de.jcup.code2doc.core.internal.group.PackageGroupProvider;
import de.jcup.code2doc.core.internal.util.Transformer;

public class SpecificationImpl implements Specification {
	private static final Logger LOG = LoggerFactory.getLogger(SpecificationImpl.class);
	
	final Transformer transformer = new Transformer();
	final PackageGroupProvider provider = new PackageGroupProvider();
	private String legalNotice = "";
	private String authorFirstName = "";
	private String authorLastName = "";
	private String title = I18n.INSTANCE.get(Code2docCoreResourceIdentifiers.DEFAULT_TITLE.getKey());
	private String preface = "";
	private Map<Group, GroupDefinitionImpl> groupDefinitions = new HashMap<Group, GroupDefinitionImpl>();
	private boolean freezed;
	
	public SpecificationImpl() {
	}

	public Specification setLegalNotice(String legalNotice) {
		this.legalNotice = legalNotice;
		return this;
	}

	public List<GroupDefinitionImpl> getRootGroupDefinitions() {
		Collection<GroupDefinitionImpl> all = getGroupDefinitions();
		/*
		 * return children in same sort order as defined in Groups by Factory
		 * calls
		 */
		List<GroupDefinitionImpl> sortedGroups = new ArrayList<GroupDefinitionImpl>(all);
		Collections.sort(sortedGroups);

		Collection<GroupDefinitionImpl> unsorted = new ArrayList<GroupDefinitionImpl>(all);
		for (GroupDefinitionImpl i : unsorted) {
			/* when not at root position - remove */
			if (i.getGroup().getParent() != null) {
				sortedGroups.remove(i);
			}
		}
		return sortedGroups;
	}

	/**
	 * Return a element definition for given element clazz
	 * 
	 * @param clazz - the exact class of the element definition.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends Element> ElementDefinitionImpl<?, T, ?> getDefinition(Class<T> clazz) {
		if (clazz == null){
			throw new IllegalArgumentException("cant get a definition for NULL element!");
		}
		DefinitionType definitionType = null;
		if (UseCase.class.isAssignableFrom(clazz)) {
			definitionType = DefinitionType.USECASE;
		} else if (Architecture.class.isAssignableFrom(clazz)) {
			definitionType = DefinitionType.ARCHITECTURE;
		} else if (Concept.class.isAssignableFrom(clazz)) {
			definitionType = DefinitionType.CONCEPT;
		} else if (Role.class.isAssignableFrom(clazz)) {
			definitionType = DefinitionType.ROLE;
		} else if (Constraint.class.isAssignableFrom(clazz)) {
			definitionType = DefinitionType.CONSTRAINT;
		} else {
			throw new IllegalArgumentException("def type not reasolveable for " + clazz);
		}

		for (GroupDefinitionImpl groupdef : getGroupDefinitions()) {
			Collection<? extends de.jcup.code2doc.core.internal.define.ElementDefinitionImpl<?, ?, ?>> definitions = groupdef.getDefinitions(definitionType);
			Iterator<? extends de.jcup.code2doc.core.internal.define.ElementDefinitionImpl<?, ?, ?>> it = definitions.iterator();
			while (it.hasNext()) {
				ElementDefinitionImpl<?, ?, ?> elementDef = it.next();
				Object obj = elementDef.getElement();
				if (clazz.equals(obj.getClass())) {
					return (ElementDefinitionImpl<?, T, ?>) elementDef;
				}
			}
		}
		/* not found*/
		return null;
	}

	public Specification setAuthor(String firstName, String lastName) {
		this.authorFirstName = firstName;
		this.authorLastName = lastName;
		return this;
	}

	public Specification setTitle(String title) {
		this.title = title;
		return this;
	}

	public Specification setPreface(String preface) {
		this.preface = preface;
		return this;
	}

	public String getLegalNotice() {
		return legalNotice;
	}

	public String getAuthorFirstName() {
		return authorFirstName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public String getTitle() {
		return title;
	}

	public String getPreface() {
		return preface;
	}

	/**
	 * Get ALL group definitions
	 * 
	 * @return all group definitions
	 */
	public Collection<GroupDefinitionImpl> getGroupDefinitions() {
		return groupDefinitions.values();
	}

	@Override
	public Specification addConcept(Class<? extends Concept> concept) {
		addConceptAndDefine(concept);
		return this;
	}

	@Override
	public ConceptDefinition addConceptAndDefine(Class<? extends Concept> concept) {
		return defineGroup(createGroupName(concept)).addConceptAndDefine(concept);
	}

	@Override
	public Specification addUseCase(Class<? extends UseCase> useCase) {
		addUseCaseAndDefine(useCase);
		return this;
	}

	@Override
	public UseCaseDefinition addUseCaseAndDefine(Class<? extends UseCase> useCase) {
		return defineGroup(createGroupName(useCase)).addUseCaseAndDefine(useCase);
	}

	@Override
	public Specification addArchitecture(Class<? extends Architecture> architecture) {
		addArchitectureAndDefine(architecture);
		return this;
	}

	@Override
	public ArchitectureDefinition addArchitectureAndDefine(Class<? extends Architecture> architecture) {
		return defineGroup(createGroupName(architecture)).addArchitectureAndDefine(architecture);
	}

	@Override
	public Specification addConstraint(Class<? extends Constraint> concept) {
		addConstraintAndDefine(concept);
		return this;
	}

	@Override
	public ConstraintDefinition addConstraintAndDefine(Class<? extends Constraint> constraint) {
		return defineGroup(createGroupName(constraint)).addConstraintAndDefine(constraint);
	}

	@Override
	public Specification addRole(Class<? extends Role> concept) {
		addRoleAndDefine(concept);
		return this;
	}

	@Override
	public RoleDefinition addRoleAndDefine(Class<? extends Role> role) {
		return defineGroup(createGroupName(role)).addRoleAndDefine(role);
	}


	public void freeze() {
		if (!isFreezed()){
			/* FIXME de-jcup, 26.03.2015 : the freeze must also avoid addings etc. here! an assertMethod would be nice */
			if (LOG.isDebugEnabled()){
				LOG.debug("start freezing specification");
			}
			freezed=true;
			provider.freeze();
			/* ensure parent child for group definitions */
			ensureParentChildGroupDefinitions();
			
			ensureConceptKnowsAllUseCases();
			if (LOG.isDebugEnabled()){
				LOG.debug("done freezing specification");
			}
		}
	}
	
	private void ensureConceptKnowsAllUseCases() {
		Map<Class<? extends Concept>, ConceptDefinitionImpl> conceptDefinitions= new HashMap<Class<? extends Concept>, ConceptDefinitionImpl>();
		Set<UseCaseDefinitionImpl> usecaseDefinitions = new HashSet<UseCaseDefinitionImpl>();
		for (GroupDefinitionImpl groupDef: getGroupDefinitions()){
			for (ConceptDefinitionImpl bcdi : groupDef.conceptDefinitions){
				conceptDefinitions.put(bcdi.getElement().getClass(), bcdi);
			}
			usecaseDefinitions.addAll(groupDef.useCaseDefinitions);
		}
		
		for (UseCaseDefinitionImpl useCaseDef: usecaseDefinitions){
			UseCase useCase = useCaseDef.getElement();
			Set<Class<? extends Concept>> concepts = useCase.getLinkedConcepts();
			for (Class<? extends Concept> concept: concepts){
				ConceptDefinitionImpl conceptDefinition = conceptDefinitions.get(concept);
				if (conceptDefinition==null){
					/* definition not added inside model - so do automatically add ...*/
					conceptDefinition = (ConceptDefinitionImpl) addConceptAndDefine(concept);
					conceptDefinitions.put(concept, conceptDefinition);
				}
				conceptDefinition.linkedUseCases.add(useCaseDef);
			}
		}
	}

	public void unfreeze(){
		if (isFreezed()){
			if (LOG.isDebugEnabled()){
				LOG.debug("start unfreezing specification");
			}
			provider.unfreeze();
			freezed=false;
			if (LOG.isDebugEnabled()){
				LOG.debug("done unfreezing specification");
			}
		}
	}
	
	public boolean isFreezed(){
		return freezed;
	}


	/**
	 * Internal method for group definition
	 * 
	 * @param name
	 * @return
	 */
	GroupDefinitionImpl defineGroup(String name) {
		Group group = createGroup(name);
		return ensureGroupDefinition(group);
	}

	private GroupDefinitionImpl ensureGroupDefinition(Group group) {
		GroupDefinitionImpl groupDefImpl = groupDefinitions.get(group);
		if (groupDefImpl == null) {
			groupDefImpl = new GroupDefinitionImpl(this, group);
			groupDefinitions.put(group, groupDefImpl);
		}
		return groupDefImpl;
	}

	private String createGroupName(Class<? extends Element> elementClazz) {
		Package package1 = notNull(elementClazz, "element class may not be null").getPackage();
		return package1.getName();
	}

	private Group createGroup(String name) {
		return provider.getGroup(name);
	}

	private void ensureParentChildGroupDefinitions() {
		boolean working;
		do {
			working = false;
			Set<Group> keys = new HashSet<Group>(groupDefinitions.keySet());
			for (Group group : keys) {
				Group parent = group.getParent();
				if (parent != null) {
					GroupDefinitionImpl groupDefImpl = groupDefinitions.get(parent);
					if (groupDefImpl == null) {
						ensureGroupDefinition(parent);
						working = true;
					}
				}
			}

		} while (working);

	}

}