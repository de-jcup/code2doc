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
package de.jcup.code2doc.core;

import de.jcup.code2doc.core.decorate.Decorator;
import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.core.extend.ExampleURLExtension;
import de.jcup.code2doc.core.internal.decorate.ClasspathDecorator;
import de.jcup.code2doc.core.internal.decorate.UseCaseExampleURLDecorator;
import de.jcup.code2doc.core.internal.define.SpecificationImpl;
import de.jcup.code2doc.core.internal.validate.ExamplePicturesAreAvailableValidator;
import de.jcup.code2doc.core.validate.MultiValidator;
import de.jcup.code2doc.core.validate.Validator;

public class Factory {

	/**
	 * Creates a decorator for decoration when code2doc is used in internal way
	 * - with API inside projects - all information is decorated to
	 * specification by this decorator.
	 * 
	 * @return decorator for all information from classpath
	 */
	public static Decorator<Specification> createInsideSpecificationDecorator() {
		return new ClasspathDecorator();
	}

	/**
	 * Creates a default validator.
	 * 
	 * @return multi validator with all default validations
	 */
	public static Validator<Specification> createDefaultValidator() {
		MultiValidator<Specification> validator = new MultiValidator<Specification>();
		validator.add(new ExamplePicturesAreAvailableValidator());
		return validator;
	}

	public static Decorator<Specification> createExampleURLDecorator(ExampleURLExtension extension) {
		return new UseCaseExampleURLDecorator(extension);
	}

	/**
	 * Creates an empty specification - can be used for specifications form
	 * outside or inside approach
	 * 
	 * @return emtpy specification
	 */
	public static Specification createEmptySpecification() {
		return new SpecificationImpl();
	}

	/**
	 * Creates specification for inside approach - means fully decorated. Only
	 * for convenience - could also be done via empty specificiaton and
	 * decorators
	 * 
	 * @return specification filled with all information from class path
	 */
	public static Specification createFilledSpecification() {
		Specification spec = createEmptySpecification();
		return createInsideSpecificationDecorator().decorate(spec);
	}
}