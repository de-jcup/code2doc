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

public class Validation {

	/**
	 * Assert given object is not null
	 * @param object - object which must not be null
	 * @param message - message of exception when object is null
	 * @return object
	 * @throws IllegalArgumentException when object is null
	 */
	public static <T> T notNull(T object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
		return object;
	}
}