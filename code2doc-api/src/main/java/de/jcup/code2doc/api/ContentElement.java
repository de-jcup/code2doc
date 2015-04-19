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
package de.jcup.code2doc.api;

import java.util.Locale;

/**
 * Content element - gives access to content, has a setup possibility and provides a description
 * @author Albert Tregnaghi
 *
 */
public abstract class ContentElement extends Element{
	

	/**
	 * ContentElement has only a package private constructor to avoid members
	 * outside of API.
	 */
	ContentElement(){
	}
	
	/**
	 * Get the full description about this use case - without locale set
	 * 
	 * @return full description (when not set an empty string is returned)
	 */
	public final String getDescription() {
		return getDescription(null);
	}
	
	/**
	 * Get the full description for given locale
	 * @param locale - locale to use
	 * @return full description (when not set an empty string is returned)
	 */
	public final String getDescription(Locale locale) {
		return getBaseContent(locale).getDescription();
	}
	
	/**
	 * Returns content - without locale
	 * @return content
	 */
	public final Content getContent(){
		return getContent(null);
	}
	
	/**
	 * Returns content - for given locale
	 * @param locale content
	 * @return content for given locale
	 */
	public final Content getContent(Locale locale){
		return getBaseContent(locale);
	}
	
	
	/**
	 * Base setup class can be used by subclasses to enable a dedicated setup methods.
	 * Main purpose of setup objects is to avoid having protected fields were extending
	 * classes can directly change fields 
	 */
	protected abstract class ContentSetup<T extends ContentSetup<?>>{
		
		/**
		 * Sets full description - no locale used
		 * @param description - description text
		 * @return setup
		 */
		public T setDescription(String description) {
			return setDescription(description,null);
		}
		

		/**
		 * Sets full description - for given locale
		 * @param description - description text
		 * @param locale - locale to use
		 * @return setup
		 * @deprecated - incubating method could change in future
		 */
		@SuppressWarnings("unchecked")
		public T setDescription(String description, Locale locale) {
			baseContent(locale).description=description;
			return (T) this;
		}

		/**
		 * Set the headline - no locale
		 * 
		 * @param headline - description text
		 * @return setup
		 */
		public T setHeadline(String headline) {
			return setHeadline(headline,null);
		}
		
		/**
		 * Set headline - no locale used
		 * 
		 * @param headline - description text
		 * @param locale - locale to use
		 * @return setup
		 * @deprecated - incubating method could change in future
		 */
		@SuppressWarnings("unchecked")
		public T setHeadline(String headline, Locale locale) {
			if (headline==null){
				headline=BaseContentContainer.DEFAULT_DESCRIPTION;
			}
			baseContent(locale).headline=headline;
			return (T) this;
		}
	
		/**
		 * Set a wiki URL where additinal information can be found - no locale used
		 * 
		 * @param description - description text
		 * @return setup
		 */
		public T setWikiURL(String wikiURL) {
			return setWikiURL(wikiURL,null);
		}
		
		/**
		 * Set a wiki URL where additinal information can be found
		 * 
		 * @param description - description text
		 * @param locale - locale to use
		 * @return setup
		 */
		@SuppressWarnings("unchecked")
		public T setWikiURL(String wikiURL, Locale locale) {
			baseContent(locale).wikiURL=wikiURL;
			return (T) this;
		}
		
		/**
		 * Define content - no locale used
		 * @return setup
		 */
		public ContentContainer content(){
			return content(null);
		}
		
		/**
		 * Define content - for given locale
		 * @param locale - locale to use
		 * @return content
		 * @deprecated - incubating method could change in future
		 */
		public ContentContainer content(Locale locale){
			BaseContentContainer content = baseContent(locale);
			return content;
		}

		
	}
}