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
import java.io.Reader;

import freemarker.cache.ClassTemplateLoader;

/**
 * Test template load - when havin problems of template resolving
 * @author Albert Tregnaghi
 *
 */
public class DebugClassTemplateLoader extends ClassTemplateLoader{

	
	/**
	 * @param resourceLoaderClass
	 * @param basePackagePath
	 */
	public DebugClassTemplateLoader(Class<?> resourceLoaderClass, String basePackagePath) {
		super(resourceLoaderClass, basePackagePath);
	}

	/**
	 * @param classLoader
	 * @param basePackagePath
	 */
	public DebugClassTemplateLoader(ClassLoader classLoader, String basePackagePath) {
		super(classLoader, basePackagePath);
	}

	@Override
	public Object findTemplateSource(String name) throws IOException {
		Object source = super.findTemplateSource(name);
		if (source!=null){
//			System.out.println(">>>findTemplateSource:"+name+"\t\t"+toString());
			System.out.println(">>>>source="+source);
		}
		return source;
	}

	@Override
	public long getLastModified(Object templateSource) {
		return super.getLastModified(templateSource);
	}

	@Override
	public Reader getReader(Object templateSource, String encoding) throws IOException {
		return super.getReader(templateSource, encoding);
	}

	@Override
	public void closeTemplateSource(Object templateSource) throws IOException {
		super.closeTemplateSource(templateSource);
	}

}