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
package de.jcup.code2doc.core.internal.group;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import de.jcup.code2doc.core.internal.define.Group;

public class PackageGroupImplTest {

	@Test
	public void testBeingComparable(){
		/* group1 is less then group2 */
		assertEquals(0, group1.compareTo(group1));
		assertEquals(0, group1.compareTo(new PackageGroupImpl(mockedProvider, group1.getName())));
		
		/* group1 is less then group2 */
		assertTrue(group1.compareTo(group2)<0);
		/* check transitive...*/
		assertTrue(group2.compareTo(group1)>0);
	}
	
	@Test
	public void testGetNameQualsPackageName(){
		assertEquals("de.jcup.test.packagename",new PackageGroupImpl(mockedProvider, "de.jcup.test.packagename").getName());
	}
	
	@Test
	public void testGetParentWorks(){
		when(mockedProvider.getChildren(group1)).thenReturn(Collections.singletonList(group3));
	}
	
	@Test
	public void tesetEquals(){
		assertTrue(new PackageGroupImpl(mockedProvider, group1.getName()).equals(group1));
		assertTrue(group1.equals(new PackageGroupImpl(mockedProvider, group1.getName())));
		assertFalse(new PackageGroupImpl(mockedProvider, group1.getName()+"x").equals(group1));
	}
	
	@Test
	public void testHashCode(){
		assertEquals(""+new PackageGroupImpl(mockedProvider, group1.getName()).hashCode(), ""+group1.hashCode());
	}
	
	/* -----------------------------------------------*/
	/* Helpers*/
	/* -----------------------------------------------*/

	private PackageGroupImpl group1;
	private PackageGroupImpl group2;
	private Group group3;
	private PackageGroupProvider mockedProvider;
	
	@Before
	public void before(){
		mockedProvider = mock(PackageGroupProvider.class);
		group1 = new PackageGroupImpl(mockedProvider, "de.jcup.test.users.user1");
		group2 = new PackageGroupImpl(mockedProvider, "de.jcup.test.users.user2");
		group3 = new PackageGroupImpl(mockedProvider, "de.jcup.test.users.user1.sub");
	}
	
}