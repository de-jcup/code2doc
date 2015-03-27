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
package de.jcup.code2doc.generator;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jcup.code2doc.core.generator.OutputFileSetting;
import de.jcup.code2doc.core.internal.define.SpecificationImpl;
import de.jcup.code2doc.core.internal.util.OutputFileSupport;
import de.jcup.code2doc.core.internal.util.Validation;

public abstract class AbstractFileSpecificationGenerator extends AbstractSpecificationGenerator<File> implements OutputFileSetting{
	private static final Logger LOG = LoggerFactory.getLogger(AbstractSpecificationGenerator.class);
	private final OutputFileSupport outputFileSupport;
	
	protected AbstractFileSpecificationGenerator(){
		outputFileSupport = createOutputFileSupport();
		Validation.notNull(outputFileSupport, "Implementation failure - file handler created may never be null!");
	}
	
	/**
	 * Creates an dedicated output file support object 
	 * @return output file support - never null
	 */
	protected abstract OutputFileSupport createOutputFileSupport();
	

	@Override
	protected final File generateImpl(SpecificationImpl spec) throws IOException {
		File outputFile = outputFileSupport.initOutputFile();
		generateImpl(outputFile, spec);
		if (LOG.isInfoEnabled()) {
			LOG.info("Generated file:" + outputFile.getAbsolutePath());
		}
		return outputFile;
	}

	protected abstract void generateImpl(File newFile, SpecificationImpl spec) throws IOException;
	
	@Override
	public void setOutputFileDeleteOnExit(boolean deleteOnExit) {
		outputFileSupport.setOutputFileDeleteOnExit(deleteOnExit);
	}
	
	@Override
	public void setOutputFileName(String fileName) {
		outputFileSupport.setOutputFileName(fileName);
	}

	@Override
	public void setOutputFileParentFolder(File parentFolder) {
		outputFileSupport.setOutputFileParentFolder(parentFolder);
	}
}

