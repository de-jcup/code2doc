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
package de.jcup.code2doc.core.validate;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.jcup.code2doc.api.UseCase;
import de.jcup.code2doc.core.internal.define.SpecificationImpl;
import de.jcup.code2doc.core.internal.validate.ExamplePicturesAreAvailableValidator;
import de.jcup.code2doc.core.validate.ValidationException;

public class ExamplePictureValidatorTest {

	private ExamplePicturesAreAvailableValidator validator;
	private SpecificationImpl specification;

	@Test
	public void test_when_found_PNG() throws ValidationException {
		specification.addUseCase(InternalPictureFoundUseCaseForTest_PNG.class);
		validator.validate(specification);
	}
	
	@Test
	public void test_when_found_JPG() throws ValidationException {
		specification.addUseCase(InternalPictureFoundUseCaseForTest_JPG.class);
		validator.validate(specification);
	}
	
	@Test
	public void test_when_found_GIF() throws ValidationException {
		specification.addUseCase(InternalPictureFoundUseCaseForTest_GIF.class);
		validator.validate(specification);
	}
	
	@Test
	public void test_when_found_but_unsupported_ending() throws ValidationException {
		specification.addUseCase(InternalPictureFoundForUseCase_UNSUPPORTED_FORMAT.class);
		try{
			validator.validate(specification);
			fail("no validation exception!");
		}catch(ValidationException e){
			/* OK */
		}
	}
	
	@Test
	public void test_when_NOT_found() throws ValidationException {
		specification.addUseCase(InternalPictureNotFoundUseCaseForTest.class);
		try{
			validator.validate(specification);
			fail("no validation exception!");
		}catch(ValidationException e){
			/* OK */
		}
	}
	
	/* -----------------------------------------------------------*/
	/* Internal helpers */
	/* -----------------------------------------------------------*/
	
	
	
	
	@Before
	public void before(){
		specification = new SpecificationImpl();
		validator = new ExamplePicturesAreAvailableValidator();
	}

	public static class InternalPictureNotFoundUseCaseForTest extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup useCaseSetup) {
			useCaseSetup.setExamplePicture("unknown");
		}
		
	}
	
	public static class InternalPictureFoundUseCaseForTest_PNG extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup useCaseSetup) {
			useCaseSetup.setExamplePicture("/de/jcup/code2doc/core/validation/testpicture.png");
		}
		
	}
	
	public static class InternalPictureFoundUseCaseForTest_JPG extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup useCaseSetup) {
			useCaseSetup.setExamplePicture("/de/jcup/code2doc/core/validation/testpicture.jpg");
		}
		
	}
	
	public static class InternalPictureFoundUseCaseForTest_GIF extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup useCaseSetup) {
			useCaseSetup.setExamplePicture("/de/jcup/code2doc/core/validation/testpicture.gif");
		}
		
	}
	
	public static class InternalPictureFoundForUseCase_UNSUPPORTED_FORMAT extends UseCase{

		@Override
		protected void doSetup(UseCaseSetup useCaseSetup) {
			useCaseSetup.setExamplePicture("/de/jcup/code2doc/core/validation/testpicture.txt");
		}
		
	}
}