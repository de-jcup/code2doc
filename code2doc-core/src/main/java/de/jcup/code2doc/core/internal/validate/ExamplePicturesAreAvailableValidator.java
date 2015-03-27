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

import java.io.File;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import de.jcup.code2doc.api.UseCase;
import de.jcup.code2doc.core.internal.define.DefinitionType;
import de.jcup.code2doc.core.internal.define.GroupDefinitionImpl;
import de.jcup.code2doc.core.internal.define.SpecificationImpl;
import de.jcup.code2doc.core.internal.define.UseCaseDefinitionImpl;
import de.jcup.code2doc.core.internal.util.Transformer;
import de.jcup.code2doc.core.validate.ValidationException;

public class ExamplePicturesAreAvailableValidator extends AbstractSpecificationImplValidator{

	private Transformer transformer = new Transformer();
	
	private static final String[] allowedFileEndings = new String[]{
		".jpg",
		".JPG",
		".gif",
		".GIF",
		".png",
		".PNG"
	};
	

	@Override
	protected void validateImpl(SpecificationImpl specification) throws ValidationException{
		
		Collection<GroupDefinitionImpl> groups = specification.getGroupDefinitions();
		for (GroupDefinitionImpl group: groups){
			@SuppressWarnings("unchecked")
			Collection<UseCaseDefinitionImpl> usecases = (Collection<UseCaseDefinitionImpl>) group.getDefinitions(DefinitionType.USECASE);
			for (UseCaseDefinitionImpl usecaseDef: usecases){
				UseCase useCase = usecaseDef.getElement();
				String path = useCase.getExamplePictureResourcePath();
				assertCorrectImageType(path);
				assertCorrectPath(path);
			}
		}
	}

	private void assertCorrectImageType(String path) {
		StringUtils.endsWithAny(path,allowedFileEndings);
	}

	private void assertCorrectPath(String resourcePath) throws ValidationException {
		if (resourcePath==null){
			/* OK no resource path */
			return;
		}
		File file = transformer.transformToFile(resourcePath);
		if (file==null){
			throw new ValidationException("No file found for:"+resourcePath);
		}
		
	}


}