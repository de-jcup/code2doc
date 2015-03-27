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

import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import de.jcup.code2doc.core.internal.define.Group;

public class PackageGroupProviderTest {

	
	@Test
	public void testSubPackageResolving(){
		Collection<String> packageNames = provider.createParentPackageNames("de.jcup.code2doc.test.group1.group2.group3");
		assertEquals("Did not found correct size:"+packageNames,7,packageNames.size());
		/* also check content and in correct ordering*/
		Iterator<String> it = packageNames.iterator();
		assertEquals("de.jcup.code2doc.test.group1.group2", it.next());
		assertEquals("de.jcup.code2doc.test.group1", it.next());
		assertEquals("de.jcup.code2doc.test", it.next());
		assertEquals("de.jcup.code2doc", it.next());
		assertEquals("de.jcup", it.next());
		assertEquals("de", it.next());
		assertEquals("", it.next()); /* default package...*/
		
	}
	
	@Test
	public void testSubPackageResolving_when_empt(){
		Collection<String> packageNames = provider.createParentPackageNames("");
		assertNotNull(packageNames);
		assertTrue(packageNames.isEmpty());
	}
	
	@Test
	public void testSubPackageResolving_when_null(){
		Collection<String> packageNames = provider.createParentPackageNames(null);
		assertNotNull(packageNames);
		assertTrue(packageNames.isEmpty());
	}
	
	@Test
	public void testGetParent(){
		/* check child nows direct defined parent*/
		provider.freeze();
		assertEquals(group3,group4.getParent());
		assertEquals(group6, group3.getParent());
	}
	
	@Test
	public void testWhenNotFreezedNoGetParentIsPossible(){
		try{
			provider.getChildren(asImpl(group1));
			fail("must fail!");
		}catch(IllegalStateException e){
			/* OK */
		}
		provider.freeze();
		provider.getChildren(asImpl(group1));
		/* OK - freeze done, no exception...*/
	}
	
	@Test
	public void testWhenNotFreezedNoGetChildrenIsPossible(){
		try{
			provider.getChildren(asImpl(group1));
			fail("must fail!");
		}catch(IllegalStateException e){
			/* OK */
		}
		provider.freeze();
		provider.getChildren(asImpl(group1));
		/* OK - freeze done, no exception...*/
	}
	
	
	@Test
	public void testWhenFreezedNoUnknownGroupsMayBeFetched(){
		provider.getGroup("de.jcup.unknown.before.freeze");
		provider.freeze();
		try{
			provider.getGroup("de.jcup.unknown.before.freeze2");
			fail("No illegal state exception thrown when freezed");
		}catch(IllegalStateException e){
			/* OK*/
		}
	}
	
	
	@Test
	public void testGetChildren(){
		provider.freeze();
		assertNotNull(group4.getChildren());
		assertEquals(0,group4.getChildren().size());

		/* check child knows direct defined parent*/
		assertEquals(1,group3.getChildren().size());
		assertEquals(group4, group3.getChildren().iterator().next());
		
		/* group 6 is the base root for all other of the other 5 test groups...(expect group4, because group4 is direct child of group3)*/
		
		assertTrue(group6.getChildren().contains(group1));
		assertTrue(group6.getChildren().contains(group2));
		assertTrue(group6.getChildren().contains(group3));
		assertFalse(group6.getChildren().contains(group4));
		assertTrue(group6.getChildren().contains(group5));
		
		assertEquals(4, group6.getChildren().size());
	}
	
	
	/* -----------------------------------------------*/
	/* Helpers*/
	/* -----------------------------------------------*/

	private PackageGroupProvider provider;
	private Group group1;
	private Group group2;
	private Group group3;
	private Group group4;
	private Group group5;
	private Group group6;
	
	private PackageGroupImpl asImpl(Group group){
		return (PackageGroupImpl) group;
	}
	
	@Before
	public void before(){
		provider = new PackageGroupProvider();
		group1 = provider.getGroup("de.jcup.test.users.user");
		group2 = provider.getGroup("de.jcup.test.users.createUser");
		group3 = provider.getGroup("de.jcup.test.organization.org");
		group4 = provider.getGroup("de.jcup.test.organization.org.availibility");
		group5 = provider.getGroup("de.jcup.test.organization.createOrg");
		group6 = provider.getGroup("de.jcup");
	}
	
}