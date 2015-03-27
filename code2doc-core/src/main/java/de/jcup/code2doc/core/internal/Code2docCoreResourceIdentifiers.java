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
package de.jcup.code2doc.core.internal;

public enum Code2docCoreResourceIdentifiers implements ResourceIdentifier{
	
	USECASE_TAG_HEADLINE ("code2doc.core.usecase.tag.headline"),
	USECASE_DESCRIPTON_HEADLINE("code2doc.core.usecase.description.headline"), 
	USECASE_DESCRIPTON_CONTENT_NOTDEFINED("code2doc.core.usecase.description.content.notdefined"), 
	
	USECASE_ROLE_CONSTRAINT_HEADLINE_PREFIX("code2doc.core.usecase.role.constraint.headline.prefix"),
	
	USECASE_EXAMPLE_URL("code2doc.core.usecase.example.url"),
	USECASE_EXAMPLE_PICTURE_HEADLINE_PREFIX("code2doc.core.usecase.example.picture.headline.prefix"),
	USECASE_EXACT_URL("code2doc.core.usecase.exact.url"), 
	DEFAULT_TITLE("code2doc.core.default.title"), 

	COMMON_TECHINFO_HEADLINE_PREFIX("code2doc.core.techinfo.headline.prefix"),
	
	ARCHITECTURES_TITLE("code2doc.core.article.architectures.title"),
	
	/*@formatter:off*/
	;
	private String key;

	/*@formatter:on*/
	private Code2docCoreResourceIdentifiers(String key){
		this.key=key;
	}

	@Override
	public String getKey() {
		return key;
	}
	
	
	
}