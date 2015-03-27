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
package de.jcup.code2doc.core.internal.validate;

import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.core.internal.define.SpecificationImpl;
import de.jcup.code2doc.core.validate.ValidationException;
import de.jcup.code2doc.core.validate.Validator;

abstract class AbstractSpecificationImplValidator implements Validator<Specification>{

	@Override
	public final Specification validate(Specification specification) throws ValidationException {
		if (specification instanceof SpecificationImpl){
			validateImpl((SpecificationImpl)specification);
			return specification;
		}else{
			throw new IllegalArgumentException("Only specification impl is supported!");
		}
	}

	abstract void validateImpl(SpecificationImpl specification) throws ValidationException;

}