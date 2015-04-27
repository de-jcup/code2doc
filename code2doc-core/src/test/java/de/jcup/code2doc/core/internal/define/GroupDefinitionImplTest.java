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

import org.junit.Test;

import de.jcup.code2doc.api.ContentElement;
import de.jcup.code2doc.api.Element;
import de.jcup.code2doc.testdata.TestUseCases;
public class GroupDefinitionImplTest {

	@Test
	public void testGetChildrenSorted() {
		
		SpecificationImpl specification = new SpecificationImpl();
		GroupDefinitionImpl impl1 = specification.defineGroup("test.group.root1");
		specification.defineGroup("test.group.root1.sub1");
		specification.defineGroup("test.group.root1.sub1.sub1");
		GroupDefinitionImpl impl2 =specification.defineGroup("test.group.root2");
		specification.freeze();
		
		assertNotNull(impl1);
		assertNotNull(impl2);
		
		/* root 1 has one direct child*/
		Collection<GroupDefinitionImpl> children1 = impl1.getChildrenSorted();
		assertFalse(children1.isEmpty());
		assertEquals(1, children1.size());

		/* child has also one child (root1Sub1sub1)*/
		GroupDefinitionImpl child1 = children1.iterator().next();
		children1 = child1.getChildrenSorted();
		assertFalse(children1.isEmpty());
		assertEquals(1, children1.size());
		
		/* child of child has no more children */
		GroupDefinitionImpl child1b = children1.iterator().next();
		Collection<GroupDefinitionImpl> children1b = child1b.getChildrenSorted();
		assertTrue(children1b.isEmpty());
		
		/* check root2 has no children*/
		Collection<GroupDefinitionImpl> children2 = impl2.getChildrenSorted();
		assertTrue(children2.isEmpty());
	}
	
	@Test
	public void test_usecase_sortedDefinitions_are_ordered_by_classnames(){
		SpecificationImpl specification = new SpecificationImpl();
		GroupDefinitionImpl impl = specification.defineGroup("test.group.root1");
		
		/* prepare - add unsorted...*/
		impl.addUseCase(TestUseCases.UC_1__SHOW_ENTRIES.class);
		impl.addUseCase(TestUseCases.UC_3__EDIT_ENTRY.class);
		impl.addUseCase(TestUseCases.UC_2__DELETE_ENTRY.class);
		
		/* fetch sorted*/
		Collection<? extends AbstractElementDefinitionImpl<?, ?, ?>> sortedDefinitions = impl.getDefinitionsSorted(DefinitionType.USECASE.name());
		
		Iterator<? extends AbstractElementDefinitionImpl<?, ?, ?>> it = sortedDefinitions.iterator();
		/* test sorting */
		assertIsElement(TestUseCases.UC_1__SHOW_ENTRIES.class, it.next());
		assertIsElement(TestUseCases.UC_2__DELETE_ENTRY.class, it.next());
		assertIsElement(TestUseCases.UC_3__EDIT_ENTRY.class, it.next());
		assertEquals(3, sortedDefinitions.size());
		
	}
	
	private void assertIsElement(Class<? extends ContentElement> expectedElement, AbstractElementDefinitionImpl<?, ?, ?> definition){
		Class<? extends Element> elementClazz = definition.getElement().getClass();
		assertEquals("Wrong element found\n", expectedElement, elementClazz);
	}
	
	

}