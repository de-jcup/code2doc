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
package de.jcup.code2doc.generator.freemarker.test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class ExampleFreemarkerUsage {

	public static void main(String[] args) throws Exception {

		new ExampleFreemarkerUsage().start(true);

	}

	private void start(boolean useClassLoading) throws IOException, TemplateException {
		/* http://freemarker.org/docs/pgui_quickstart_createconfiguration.html */

		// Create your Configuration instance, and specify if up to what
		// FreeMarker
		// version (here 2.3.22) do you want to apply the fixes that are not
		// 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

		// Specify the source where the template files come from. Here I set a
		// plain directory for it, but non-file-system sources are possible too:
		// http://freemarker.org/docs/pgui_config_templateloading.html
		if (useClassLoading){
			System.out.println("using class loading mechansim");
			cfg.setClassForTemplateLoading(getClass(), "/de/jcup/code2doc/generator/freemarker/test/templates");
		}else{
			System.out.println("using file directory mechansim");
			cfg.setDirectoryForTemplateLoading(new File("./src/test/resources/de/jcup/code2doc/generator/freemarker/test/templates"));
		}

		// Set the preferred charset template files are stored in. UTF-8 is
		// a good choice in most applications:
		cfg.setDefaultEncoding("UTF-8");

		// Sets how errors will appear.
		// During web page *development*
		// TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		
		
		/* http://freemarker.org/docs/pgui_quickstart_createdatamodel.html*/
		// Create the root hash
		Map<String, Object> root = new HashMap<String, Object>();
		// Put string ``user'' into the root
		root.put("user", "Big Joe");
		// Create the hash for ``latestProduct''
		Map<String, String> latest = new HashMap<String, String>();
		// and put it into the root
		root.put("latestProduct", latest);
		// put ``url'' and ``name'' into latest
		latest.put("url", "products/greenmouse.html");
		latest.put("name", "green mouse"); 
		
		TestDataLoad load1 = new TestDataLoad("load1");
		TestDataLoad load2 = new TestDataLoad("load2");
		TestDataLoad load3 = new TestDataLoad("load3");
		TestDataLoad load4 = new TestDataLoad("load4");
		load1.getChildren().add(load2);
		load1.getChildren().add(load3);
		load3.getChildren().add(load4);
		
		root.put("load1", load1);
		
		/* http://freemarker.org/docs/pgui_quickstart_gettemplate.html*/
		Template temp = cfg.getTemplate("test.ftl");  
		System.out.println("---------------------");
		System.out.println("   OUTPUT: ");
		System.out.println("---------------------");
		Writer out = new OutputStreamWriter(System.out);
		temp.process(root, out);  
		
	}
}