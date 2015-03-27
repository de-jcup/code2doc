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
package de.jcup.code2doc.core.filter;

import static junit.framework.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class OrFilterTest {

	private Filter ALWAYS_FILTERED_FALSE;
	private Filter ALWAYS_FILTERED_TRUE;

	@Test
	public void test_orfilter_no_filter_returns_true() {
		Filter filter = new OrFilterExpression(ALWAYS_FILTERED_FALSE).or(ALWAYS_FILTERED_FALSE).or(ALWAYS_FILTERED_FALSE);
		assertFalse(filter.isFiltered(null));
	}
	
	@Test
	public void test_orfilter_one_filter_returns_true() {
		Filter filter = new OrFilterExpression(ALWAYS_FILTERED_FALSE).or(ALWAYS_FILTERED_TRUE).or(ALWAYS_FILTERED_FALSE);
		assertTrue(filter.isFiltered(null));
	}

	@Before
	public void before() {
		ALWAYS_FILTERED_FALSE = mock(Filter.class);
		when(ALWAYS_FILTERED_FALSE.isFiltered(any())).thenReturn(false);
		ALWAYS_FILTERED_TRUE = mock(Filter.class);
		when(ALWAYS_FILTERED_TRUE.isFiltered(any())).thenReturn(true);

	}
}