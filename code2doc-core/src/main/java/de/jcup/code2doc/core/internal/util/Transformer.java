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
import java.net.URL;
import java.util.Locale;

import static de.jcup.code2doc.core.internal.util.StringUtil.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jcup.code2doc.api.Element;

/**
 * Transformer class handles all kind of transformations done in code2doc
 * @author de-jcupIGNA
 *
 */
public class Transformer {

	private static final Logger LOG = LoggerFactory.getLogger(Transformer.class);
	private Locale locale;
	private FileAccess fileAccess = new FileAccess();

	public Transformer(){
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	/**
	 * Transforms given object to a string
	 * <ol>
	 * <li>null object -  to "null"</li>
	 * <li>string - keeps the string</li>
	 * <li>enumeration - returns enum.simpleClassName()+"."+name() (when toString() is same as name()(default) - otherwise a string of enum.simpleClassName()+"."+name() +":"+toString() will be returned )</li>
	 * <li>class - the complete class name</li>
	 * <li>{@linkplain Element} - the i18n headline</li>
	 * <li>all others return their .toString() result</li>
	 * </ol>
	 * @param object
	 * @return string of object
	 */
	public String transformToString(Object object){
		if (object == null){
			return "null";
		}
		if (object instanceof String){
			return (String)object;
		}
		if (object instanceof Enum<?>){
			Enum<?> en = (Enum<?>)object;
			String simpleClassName = en.getClass().getSimpleName();
			String name = en.name();
			String toString = en.toString();
			if (name.equals(toString)){
				 return simpleClassName+"."+name;
			}
			return simpleClassName+"."+name+":"+toString;
			
		}
		if (object instanceof Class<?>){
			return ((Class<?>)object).getName();
		}
		if (object instanceof Element){
			Element e = (Element) object;
			return e.getHeadline(locale);
		}
		return object.toString();
	}
	
	/**
	 * Transforms a file resource to its content (String)
	 * @param resourcePath
	 * @return string - when content is not found "null" is returned
	 */
	public String transformToStringContent(String resourcePath){
		File file = transformToFile(resourcePath);
		String text = fileAccess.readTextFile(file);
		return transformToString(text);
	}
	
	/**
	 * Transforms a given clazz to an instance. The clazz must have a default constructur and must be public accessible!
	 * @param clazz
	 * @return instance
	 * @throws IllegalArgumentException - when given clazz cannot be created 
	 */
	public<T> T transformToInstance(Class<T> clazz){
		if (clazz==null){
			throw new IllegalArgumentException("Clazz parameter is null - so cannt create instance...");
		}
		/* @formatter:off */
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Cannot transform clazz to instance:" + clazz+"\n"
				  + "Class must have a default constructor, not abstract, must be public and, if this is a inner class, it must be static!", e);
		}
		/* @formatter:on */
	}
	
	/**
	 * Transforms given object to a tag - tags are used e.g. to identify elements
	 * <ol>
	 * <li>null object to "null"</li>
	 * <li>all others return their simple class name as result</li> 
	 * </ol>
	 * @param object
	 * @return tag of object
	 */
	public String transformToTag(Object object){
		if (object == null){
			return "null";
		}
		return object.getClass().getSimpleName();
	}

	
	/**
	 * Transforms given object to an ID 
	 * <ol>
	 * <li>all others return their full class name as result</li> 
	 * </ol>
	 * @param object
	 * @return tag of object
	 */
	public String transformToId(Object object){
		if (object == null){
			throw new IllegalArgumentException("Object is null! To create an identifier the object must always be available!");
		}
		return object.getClass().getName();
	}

	
	/**
	 * Tries to transform given resource path to a file
	 * @param resourcePath - path must be suitable for class loading - e.g. <pre>"/de/jcup/test/pictures/picture01.png"</pre>
	 * @return file or <code>null</code> if not transformable/not found
	 */
	public File transformToFile(String resourcePath){
		/*
		 * Remark: convenience resource handling is only done inside Element.prepareResourcePath() - not here
		 */
		if (resourcePath==null){
			if (LOG.isErrorEnabled()){
				LOG.error("resource path was null!");
			}
			return null;
		}
		/* try to resolve via class loader */
		URL resource = null;
		try{
			resource = getClass().getResource(resourcePath);
		}catch(RuntimeException e){
			if (LOG.isErrorEnabled()){
				LOG.error("resource not found for resource path:"+resourcePath,e);
			}
		}
		if (resource!=null){
			String filePath = resource.getFile();
			if (isEmpty(filePath)){
				if (LOG.isErrorEnabled()){
					LOG.error("resource not available as file for resource path:"+resourcePath);
				}
				return null;
			}
			File urlFile = new File(filePath);
			return urlFile;
		}
		if (LOG.isErrorEnabled()){
			LOG.error("resource not found for resource path:"+resourcePath);
		}
		return null;
	}
}