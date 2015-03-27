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

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;


public class FileAccessTest {


	@Test
	public void testReadTextFile_null_param(){
		String result = fileAccess.readTextFile(null);
		assertNull(result);
	}
	
	@Test
	public void testReadTextFile_not_existing_file(){
		String result = fileAccess.readTextFile(new File("xxxasfasfsdfasdfasdfasdfasfasdfasdfasdfasfasfasfasfasfsfasfa.txtses"));
		assertNull(result);
	}
	
	@Test
	public void testReadTextFile_existing_file() throws IOException{
		/* check preconditions*/
		URL resource = getClass().getResource("/de/jcup/code2doc/core/util/test-textfile1.txt");
		assertNotNull("Testcase wrong - resource must be found!",resource);
		String filePath = resource.getFile();
		assertNotNull(filePath);
		File file = new File(filePath);
		
		/* execute */
		String result = fileAccess.readTextFile(file);
		
		/* test */
		assertNotNull(result);
		String l =System.getProperty("line.separator");
		String expected = "Line1"+l+"Line2"+l+"Line3";

		assertEquals(expected,result);
	}
	
	private FileAccess fileAccess;
	
	@Before
	public void before(){
		fileAccess = new FileAccess();
	}
}