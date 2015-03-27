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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.LoggerFactory;

import de.jcup.code2doc.core.decorate.Decorator;
import de.jcup.code2doc.core.internal.I18n;
import de.jcup.code2doc.core.internal.define.SpecificationImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreeMarkerStringSpecificationGenerator extends AbstractSpecificationGenerator<String>{
	
	private static final String ID_FREEMARKER_TEMPLATE = "code2doc.freemarker.template";
	protected Configuration cfg;
	private Decorator<String> textDecorator;

	public FreeMarkerStringSpecificationGenerator(String basePackageName, Decorator<String> textDecorator){
		if (basePackageName==null){
			throw new IllegalArgumentException("basePackageName must not be null!");
		}
		if (textDecorator==null){
			throw new IllegalArgumentException("textDecorator must not be null!");
		}
		this.textDecorator=textDecorator;
		/* http://freemarker.org/docs/pgui_quickstart_createconfiguration.html */
		
		// Create your Configuration instance, and specify if up to what FreeMarker
		// version (here 2.3.22) do you want to apply the fixes that are not 100%
		// backward-compatible. See the Configuration JavaDoc for details.
		cfg = new Configuration(Configuration.VERSION_2_3_22);

		// Specify the source where the template files come from. Here I set a
		// plain directory for it, but non-file-system sources are possible too:
		cfg.setClassForTemplateLoading(getClass(), basePackageName);

		// Set the preferred charset template files are stored in. UTF-8 is
		// a good choice in most applications:
		cfg.setDefaultEncoding(ENCODING);

		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);  
	}
	
	public void setTemplate(String template){
		setParameter(ID_FREEMARKER_TEMPLATE, template);
	}

	@Override
	protected String generateImpl(SpecificationImpl spec) throws IOException {
		/* filters are not supported currently*/
		Template temp = cfg.getTemplate(getMandatoryParameter(ID_FREEMARKER_TEMPLATE));  
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Writer writer = new OutputStreamWriter(stream);
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("spec", spec);
		
		prepare(root);
		
		try {
			temp.process(root, writer);
		} catch (TemplateException e) {
			 throw new IOException("Not able to generate specification", e);
		}  
		String result = stream.toString(ENCODING);
		return result;
	}
	
	protected void prepare(Map<String, Object> root){
		root.put("i18n", I18n.INSTANCE);
		root.put("textDecorator", textDecorator);
		root.put("transformer", transformer);
		String language = params.get("language");
		root.put("language", language);
		/* we put the logger into the template - so template can log too...*/
		root.put("log", LoggerFactory.getLogger(getClass()));
		Locale locale = null;
		if (language!=null){
			 locale = new Locale(language);
		}
		root.put("locale", locale);
	}
	

}