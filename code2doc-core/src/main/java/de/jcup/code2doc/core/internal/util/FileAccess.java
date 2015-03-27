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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FileAccess - gives simple access to files
 * @author de-jcup
 *
 */
public class FileAccess {

	private static final Logger LOG = LoggerFactory.getLogger(FileAccess.class);
	private static final String LINESEP = System.getProperty("line.separator");

	/**
	 * Reads a given file as text file and returns content as string. If there
	 * are any {@link IOException} occuring the exceptions will be logged and
	 * <code>null</code> returned.
	 * 
	 * @param file
	 *            - file representing a text file
	 * @return content as string or <code>null</code> when file cannot be read
	 */
	public String readTextFile(File file) {
		if (file == null) {
			if (LOG.isErrorEnabled()) {
				LOG.error("cannot read text file because file parameter is null!");
			}
			return null;
		}
		if (!file.exists()) {
			if (LOG.isErrorEnabled()) {
				LOG.error("cannot read text file because file does not exists:" + file.getAbsolutePath());
			}
			return null;
		}
		BufferedReader br = null;
		try {
			StringBuilder sb = new StringBuilder();
			FileReader reader = new FileReader(file);
			br = new BufferedReader(reader);
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				if (br.ready()) {
					sb.append(LINESEP);
				}
			}
			return sb.toString();

		} catch (IOException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("cannot read text file:" + file.getAbsolutePath(), e);
			}
			return null;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					if (LOG.isErrorEnabled()) {
						LOG.error("cannot close text file stream for file:" + file.getAbsolutePath(), e);
					}
				}
			}
		}
	}
}