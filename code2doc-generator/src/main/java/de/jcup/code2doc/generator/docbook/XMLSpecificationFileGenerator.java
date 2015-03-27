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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import de.jcup.code2doc.core.internal.define.SpecificationImpl;
import de.jcup.code2doc.core.internal.util.OutputFileSupport;
import de.jcup.code2doc.generator.AbstractFileSpecificationGenerator;

public class XMLSpecificationFileGenerator extends AbstractFileSpecificationGenerator{


	@Override
	protected void generateImpl(File newFile, SpecificationImpl spec) throws IOException {
		DocbookStringSpecificationGenerator stringGeno = new DocbookStringSpecificationGenerator();
		stringGeno.setFilter(getFilter());
		String output = stringGeno.generate(spec);
		IOUtils.write(output, new FileOutputStream(newFile),ENCODING);
	}

	
	@Override
	protected OutputFileSupport createOutputFileSupport() {
		return new OutputFileSupport() {
			
			@Override
			protected void handleExistingFile(File outputFile) throws IOException {
				FileUtils.forceDelete(outputFile);
			}
			
			@Override
			protected String getFileExtension() {
				return "xml";
			}
		};
	}

}