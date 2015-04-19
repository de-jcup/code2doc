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
package de.jcup.code2doc.generator.docbook;

import java.io.IOException;
import java.util.Map;

import de.jcup.code2doc.core.decorate.AbstractMarkupTextDecorator;
import de.jcup.code2doc.core.internal.define.SpecificationImpl;
import de.jcup.code2doc.generator.FreeMarkerStringSpecificationGenerator;

public class DocbookStringSpecificationGenerator extends FreeMarkerStringSpecificationGenerator {

	public DocbookStringSpecificationGenerator() {
		this("/de/jcup/code2doc/generator/docbook/template/");
	}

	public DocbookStringSpecificationGenerator(String basePackageName) {
		super(basePackageName, new DocbookMarkupTextDecorator());
		setTemplate("docbook.ftl");
	}

	private static class DocbookMarkupTextDecorator extends AbstractMarkupTextDecorator {

		@Override
		protected void setup(MarkupTextDecoratorSetup setup) {
			setup.register(new DocbookHTMLMarkupTypeSupport());
			/* at this point we can add additional mark up support*/
		}

	}

	@Override
	protected String generateImpl(SpecificationImpl spec) throws IOException {
		String data = super.generateImpl(spec);
		return data;
	}

	@Override
	protected void prepare(Map<String, Object> root) {
		super.prepare(root);
		/* use only sections and not sect1,sect2 etc. */
		root.put("onlySections", "true");
	}

}