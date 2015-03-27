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
package de.jcup.code2doc.core.internal;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class I18n {
	
	private static final Logger LOG = LoggerFactory.getLogger(I18n.class);

	public static final I18n INSTANCE = new I18n();

	private Collection<String> bundles = new TreeSet<String>();
	private Collection<String> evilBundles = new TreeSet<String>();
	
	I18n() {
		addResourceBundle("de.jcup.code2doc.core.code2doc_core");
		addResourceBundle("code2doc_groups"); /* i18n for PackageGroups...*/
	}

	/**
	 * Returns replacement for type 
	 * @param type
	 * @return replacement or "{type}"
	 */
	public String get(String key) {
		for (String baseName: bundles){
			if (evilBundles.contains(baseName)){
				continue;
			}
			try{
				ResourceBundle bundle = ResourceBundle.getBundle(baseName, getLocale());
				if (bundle.containsKey(key)){
					return bundle.getString(key);
				}
			}catch(RuntimeException e){
				evilBundles.add(baseName);
				LOG.error("Bundle:"+baseName+" not available. So ignore in future.",e);
			}
		}
		return createNotFoundResult(key);
	}
	
	String createNotFoundResult(String property){
		return "{"+property+"}";
	}
	
	private Locale getLocale() {
		return Locale.getDefault();
	}

	void addResourceBundle(String baseName){
		bundles.add(baseName);
	}
}