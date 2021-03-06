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
package de.jcup.code2doc.core.extend;

/**
 * ExampleURLExtension
 * @author Albert Tregnaghi
 *
 */
public interface ExampleURLExtension extends Code2DocExtension{

	/**
	 * Create example URL for given object
	 * @param object
	 * @return <code>null</code> if not supported for given object. If an example URL can be built for given object a URL string is returned
	 */
	public String createExampleURL(Object object);
}
