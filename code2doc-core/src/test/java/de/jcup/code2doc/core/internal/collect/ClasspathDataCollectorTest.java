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

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import de.jcup.code2doc.api.UseCase;
import de.jcup.code2doc.testdata.TestArchitectureConcepts.TestArchitectureConceptNeverLinked;
import de.jcup.code2doc.testdata.TestConcept;
import de.jcup.code2doc.testdata.TestConstraints;
import de.jcup.code2doc.testdata.TestQuery;
import de.jcup.code2doc.testdata.TestRoles;
import de.jcup.code2doc.testdata.TestUseCases;

public class ClasspathDataCollectorTest {
	@Test
	public void test_usecase_found() {
		assertTrue(collector.collectAllUseCases().contains(TestUseCases.UC_666_NEVER_LINKED.class));
		assertFalse(collector.collectAllUseCases().contains(UseCase.class));
	}

	@Test
	public void test_architecture_found() {
		assertTrue(collector.collectAllArchitectures().contains(TestArchitectureConceptNeverLinked.class));
	}

	@Test
	public void test_concept_found() {
		assertTrue(collector.collectAllConcepts().contains(TestConcept.class));
	}

	@Test
	public void test_role_found() {
		assertTrue(collector.collectAllRoles().contains(TestRoles.LOCAL_ADMIN.class));
	}

	@Test
	public void test_constraint_found() {
		assertTrue(collector.collectAllConstraints().contains(TestConstraints.ONLY_FOR_HOME_ORG.class));
	}

	@Test
	public void test_link_to_usecase__found() {
		Map<Class<? extends UseCase>, List<TechInfoLinkAnnotationData>> data = collector.collectLinkingToUseCases();

		/*
		 * check Testquery is found -which is annotated with link to... UC_1 and
		 * with type='query'
		 */
		assertFound(data, TestUseCases.UC_1__SHOW_ENTRIES.class, "query", TestQuery.class);
	}

	@Test
	public void test_link_to_usecase__UC_666_NEVER_LINKED() {
		Map<Class<? extends UseCase>, List<TechInfoLinkAnnotationData>> data = collector.collectLinkingToUseCases();
		assertNOTFound(data, TestUseCases.UC_666_NEVER_LINKED.class, null, TestQuery.class);
	}

	@Test
	public void test_link_to_roles_found() {
		/* simply check we got some results - so collecting works */
		assertFalse(collector.collectLinkingToRoles().isEmpty());
	}

	@Test
	public void test_link_to_constraint_found() {
		/* simply check we got some results - so collecting works */
		assertFalse(collector.collectLinkingToConstraints().isEmpty());
	}

	@Test
	public void test_link_to_usecase_found() {
		/* simply check we got some results - so collecting works */
		assertFalse(collector.collectLinkingToUseCases().isEmpty());
	}

	/* -------------------- */
	/* --Helpers ---------- */
	/* -------------------- */
	private ClasspathDataCollector collector;

	private void assertFound(Map<Class<? extends UseCase>, List<TechInfoLinkAnnotationData>> map, Class<? extends UseCase> useCase, String expectedGroup,
			Class<TestQuery> expectedClazz) {
		_assertFound(map, useCase, expectedGroup, expectedClazz, true);
	}

	private void assertNOTFound(Map<Class<? extends UseCase>, List<TechInfoLinkAnnotationData>> map, Class<? extends UseCase> useCase, String expectedGroup,
			Class<TestQuery> expectedClazz) {
		_assertFound(map, useCase, expectedGroup, expectedClazz, false);
	}

	private void _assertFound(Map<Class<? extends UseCase>, List<TechInfoLinkAnnotationData>> map, Class<? extends UseCase> useCase, String expectedGroup,
			Class<TestQuery> expectedClazz, boolean expectedFound) {
		boolean found = false;
		List<TechInfoLinkAnnotationData> list = map.get(useCase);
		if (list != null) {
			for (TechInfoLinkAnnotationData annotationData : list) {
				Class<?> clazz = annotationData.linkedClass;
				String group = annotationData.getType();

				if (expectedClazz.equals(clazz)) {
					if (expectedGroup == null) {
						if (group == null) {
							found = true;
							break;
						}
					} else if (expectedGroup.equals(group)) {
						found = true;
						break;
					}
				}
			}
		}
		if (expectedFound) {
			assertTrue(expectedClazz.getName() + " must be found as class, within type:" + expectedGroup + " but isnt", found);
		} else {
			assertFalse(expectedClazz.getName() + " must NOT be found as class, within type:" + expectedGroup + " but is", found);
		}
	}

	@Before
	public void before() {
		collector = new ClasspathDataCollector();
	}
}