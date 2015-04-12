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

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import de.jcup.code2doc.api.Architecture;
import de.jcup.code2doc.api.Concept;
import de.jcup.code2doc.api.UseCase;
import de.jcup.code2doc.testdata.TestArchitectureConcepts;
import de.jcup.code2doc.testdata.TestConcept;
import de.jcup.code2doc.testdata.TestUseCases;
import de.jcup.code2doc.testdata.TestUseCases.UC_1__SHOW_ENTRIES;
import de.jcup.code2doc.testdata.TestUseCases.UC_2__DELETE_ENTRY;

public class SpecificationImplTest {
	
	
	@Test
	public void testFreezeWillCreateLinksBetweenConceptAndUseCases(){
		/* prepare */
		SpecificationImpl impl = new SpecificationImpl();
		impl.addUseCase(TestUseCases.UC_1__SHOW_ENTRIES.class);
		impl.addUseCase(TestUseCases.UC_2__DELETE_ENTRY.class);
		impl.addUseCase(TestUseCases.UC_3__EDIT_ENTRY.class);
		impl.addConcept(TestConcept.class);
		impl.freeze();
		
		/* test */
		assertTestConceptInSpecAndConceptHasBothUseCases(impl);
	}

	@Test
	public void testFreezeWillCreateLinksBetweenConceptAndUseCases_check_not_linked_usecase_has_nosize(){
		/* prepare */
		SpecificationImpl impl = new SpecificationImpl();
		impl.addUseCase(TestUseCases.UC_3__EDIT_ENTRY.class);
		impl.addConcept(TestConcept.class);
		impl.freeze();
		
		/* test */
		ElementDefinitionImpl<?, ?, ?> def = impl.getDefinition(TestUseCases.UC_3__EDIT_ENTRY.class);
		assertNotNull(def);
		assertEquals(DefinitionType.USECASE, def.getDefinitionType());
		UseCaseDefinitionImpl bimpl = (UseCaseDefinitionImpl) def;
		
		Set<Class<? extends Concept>> x = bimpl.getElement().getLinkedConcepts();
		assertEquals(0, x.size());
	}
	
	@Test
	public void testFreezeWillCreateLinksBetweenConceptAndUseCases_check_linked_usecase_has_size_and_content(){
		/* prepare */
		SpecificationImpl impl = new SpecificationImpl();
		impl.addUseCase(TestUseCases.UC_1__SHOW_ENTRIES.class);
		impl.addConcept(TestConcept.class);
		impl.freeze();
		
		/* test */
		ElementDefinitionImpl<?, ?, ?> def = impl.getDefinition(TestUseCases.UC_1__SHOW_ENTRIES.class);
		assertNotNull(def);
		assertEquals(DefinitionType.USECASE, def.getDefinitionType());
		UseCaseDefinitionImpl bimpl = (UseCaseDefinitionImpl) def;
		
		UseCase useCase = bimpl.getElement();
		assertNotNull(useCase);
		Set<Class<? extends Concept>> x = useCase.getLinkedConcepts();
		assertEquals(1, x.size());
		Class<? extends Concept> businessClass = x.iterator().next();
		assertNotNull(businessClass);
	}

	@Test
	public void testFreezeWillCreateLinksBetweenConceptAndUseCases_missing_concept_works(){
		SpecificationImpl impl = new SpecificationImpl();
		impl.addUseCase(TestUseCases.UC_1__SHOW_ENTRIES.class);
		impl.addUseCase(TestUseCases.UC_2__DELETE_ENTRY.class);
		impl.addUseCase(TestUseCases.UC_3__EDIT_ENTRY.class);
		
		impl.freeze();
		
		/* test - the Concept is missing but is automatically added to model, because defined in use case */
		assertTestConceptInSpecAndConceptHasBothUseCases(impl);
	}
	
	@Test
	public void test_find_usecases() {
		SpecificationImpl impl = new SpecificationImpl();
		impl.addUseCase(TestUseCases.UC_1__SHOW_ENTRIES.class);

		ElementDefinitionImpl<?, ?, ?> result = impl.getDefinition(TestUseCases.UC_1__SHOW_ENTRIES.class);
		assertNotNull(result);

		result = impl.getDefinition(TestUseCases.UC_666_NEVER_LINKED.class);
		assertNull(result);
	}

	@Test
	public void test_find_architectures_in_spec__means_rootgroup() {

		SpecificationImpl impl = new SpecificationImpl();
		impl.addArchitecture(TestArchitectureConcepts.TestPersistenceConcept.class);

		ElementDefinitionImpl<?, ?, ?> result = impl.getDefinition(TestArchitectureConcepts.TestPersistenceConcept.class);
		assertNotNull(result);

		result = impl.getDefinition(TestArchitectureConcepts.TestArchitectureConceptNeverLinked.class);
		assertNull(result);
		
		/* fetch via group definitions*/
		GroupDefinitionImpl root = impl.getGroupDefinitions().iterator().next();
		Collection<? extends ElementDefinitionImpl<?, ?, ?>> ad = root.getDefinitions(DefinitionType.ARCHITECTURE);
		ArchitectureDefinitionImpl def = (ArchitectureDefinitionImpl) ad.iterator().next();
		Architecture element = def.getElement();
		
		assertNotNull(element);
	}
	

	@Test
	public void test_find_architectures_in_group() {

		SpecificationImpl impl = new SpecificationImpl();
		impl.addArchitecture(TestArchitectureConcepts.TestPersistenceConcept.class);

		ElementDefinitionImpl<?, ?, ?> result = impl.getDefinition(TestArchitectureConcepts.TestPersistenceConcept.class);
		assertNotNull(result);
		Architecture element = (Architecture) result.getElement();
		assertNotNull(element);
		
		
		result = impl.getDefinition(TestArchitectureConcepts.TestArchitectureConceptNeverLinked.class);
		assertNull(result);
		
		/* fetch via group definitions*/
		boolean found=false;
		for (GroupDefinitionImpl groupDef : impl.getGroupDefinitions()){
			if (groupDef.getGroup().getName().equals(TestArchitectureConcepts.TestPersistenceConcept.class.getPackage().getName())){
				found=true;
				Collection<? extends ElementDefinitionImpl<?, ?, ?>> ad = groupDef.getDefinitions(DefinitionType.ARCHITECTURE);
				ArchitectureDefinitionImpl def = (ArchitectureDefinitionImpl) ad.iterator().next();
				element = def.getElement();
				
				assertNotNull(element);
			}
		}
		assertTrue(found);
	}
	
	private void assertTestConceptInSpecAndConceptHasBothUseCases(SpecificationImpl impl) {
		ElementDefinitionImpl<?, ?, ?> def = impl.getDefinition(TestConcept.class);
		assertNotNull(def);
		assertEquals(DefinitionType.CONCEPT, def.getDefinitionType());
		ConceptDefinitionImpl bimpl = (ConceptDefinitionImpl) def;
		Collection<UseCaseDefinitionImpl> x = bimpl.getLinkedUseCasesSorted();
		assertEquals(2, x.size());
		Iterator<UseCaseDefinitionImpl> it = x.iterator();
		assertEquals(UC_1__SHOW_ENTRIES.class, it.next().getElement().getClass());
		assertEquals(UC_2__DELETE_ENTRY.class, it.next().getElement().getClass());
	}
}