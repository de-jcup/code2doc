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
package de.jcup.code2doc.api;

import static org.junit.Assert.*;

import org.junit.Test;
public class UseCaseTest {

	@Test
	public void test_get_constraint_works(){
		TestUseCase1 uc1 = new TestUseCase1();
		assertTrue(uc1.getRoles().contains(Role1.class));
		assertTrue(uc1.getConstraint(Role1.class).equals(Constraint1.class));
	}
	
	/**
	 * Test for bugfix of https://github.com/de-jcup/code2doc/issues/2
	 */
	@Test
	public void test_setExampleUrl__without_slash__uses_package_of_container_element(){
		
		TestUseCase1 u1 = new TestUseCase1();
		String resPath = u1.getExamplePictureResourcePath();
		assertEquals("/de/jcup/code2doc/api/resourcePath1/path2/path3", resPath);
		
	}
	
	@Test
	public void test_setExampleUrl__with_slash__uses_given_path(){
		
		TestUseCase2 u2 = new TestUseCase2();
		String resPath = u2.getExamplePictureResourcePath();
		assertEquals("/resourcePath4/path5/path6", resPath);
		
	}
	
	
	public static class TestUseCase1 extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup setup) {
			setup.addRole(Role1.class,Constraint1.class);
			setup.setExamplePicture("resourcePath1/path2/path3");
		}
		
	}
	
	public static class TestUseCase2 extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup setup) {
			setup.setExamplePicture("/resourcePath4/path5/path6");
		}
		
	}
	
	public static class Role1 extends Role{

		@Override
		protected void doSetup(RoleSetup setup) {
			
		}
		
	}
	
	public static class Constraint1 extends Constraint{

		@Override
		protected void doSetup(ConstraintSetup setup) {
			
		}
		
	}
}