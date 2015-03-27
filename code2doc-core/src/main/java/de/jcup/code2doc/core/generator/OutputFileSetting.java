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
package de.jcup.code2doc.core.generator;

import java.io.File;

/**
 * Setup interface for output files
 * @author hexadez
 *
 */
public interface OutputFileSetting {

	/**
	 * Set parent folder of output file
	 * @param parentFolder
	 */
	public void setOutputFileParentFolder(File parentFolder);

	/**
	 * Set output file name (without extension)
	 * @param fileName - file name (without extension)
	 */
	public void setOutputFileName(String fileName);

	/**
	 * Set output file delete on exit
	 * @param deleteOnExit - true for temporary outputs. Are deleted automatically bý JVM
	 */
	public void setOutputFileDeleteOnExit(boolean deleteOnExit);

}