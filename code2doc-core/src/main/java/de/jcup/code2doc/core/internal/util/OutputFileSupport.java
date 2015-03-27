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
package de.jcup.code2doc.core.internal.util;

import java.io.File;
import java.io.IOException;

import de.jcup.code2doc.core.generator.OutputFileSetting;

/**
 * Output file support - handles automatical delete, creation etc.
 * @author hexadez
 *
 */
public abstract class OutputFileSupport implements OutputFileSetting  {

	private String outputFileName;
	private File outputFolder;
	protected boolean deleteOnExit;

	public OutputFileSupport() {
		super();
	}

	@Override
	public void setOutputFileParentFolder(File parentFolder) {
		this.outputFolder = parentFolder;
	}

	@Override
	public void setOutputFileName(String fileName) {
		this.outputFileName = fileName;
	}

	@Override
	public void setOutputFileDeleteOnExit(boolean deleteOnExit) {
		this.deleteOnExit=deleteOnExit;
	}

	/**
	 * Implementations must implement behavior for existing files by them self
	 * @param outputFile
	 * @throws IOException
	 */
	protected abstract void handleExistingFile(File outputFile) throws IOException;

	/**
	 * Implementation return the file extension (e.g. "pdf")
	 * @return
	 */
	protected abstract String getFileExtension();

	/**
	 * Creates the file
	 * @return
	 * @throws IOException
	 */
	protected File createFile() throws IOException {
		if (outputFileName==null){
			return File.createTempFile("generated", "."+getFileExtension());
		}
		if (outputFolder==null){
			String folder = System.getProperty("java.io.tmpdir");
			outputFolder = new File(folder);
		}else{
			if (! outputFolder.exists()){
				if (!outputFolder.mkdirs()){
					throw new IOException("Output folder did not exist - but was not able to create the output folder hierarchy:"+outputFolder);
				}
			}
		}
		return new File(outputFolder,outputFileName+"."+getFileExtension());
	}

	/**
	 * Initialize output file
	 * @return
	 * @throws IOException
	 */
	public File initOutputFile() throws IOException {
		File outputFile = createFile();
		if (outputFile.exists()) {
			handleExistingFile(outputFile);
		}
		if (deleteOnExit) {
			outputFile.deleteOnExit();
		}
		return outputFile;
	}

}