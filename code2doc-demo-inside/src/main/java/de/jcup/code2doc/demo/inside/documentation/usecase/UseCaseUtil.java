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
package de.jcup.code2doc.demo.inside.documentation.usecase;

public class UseCaseUtil {

	private static final String DOCUMENTED_BASE_URL = "http://www.jcup.de/code2doc/demo/internal/";
	
	
	public static String createURL(String part){
		return DOCUMENTED_BASE_URL+part+".html";
	}
	
	public static String createURL(String part, String param1, String value1){
		return DOCUMENTED_BASE_URL+part+".html?"+param1+"="+value1;
	}
}