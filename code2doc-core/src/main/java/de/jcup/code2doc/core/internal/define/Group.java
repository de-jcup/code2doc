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
package de.jcup.code2doc.core.internal.define;

import java.util.Collection;

/**
 * A Group inside specification model
 * @author Albert Tregnaghi
 *
 */
public interface Group extends Comparable<Group>{

	/**
	 * Get parent group
	 * @return parent group or <code>null</code> when no parent
	 */
	public Group getParent();
	
	/**
	 * Return name
	 * @return name of group
	 */
	public String getName();
	
	
	
	/**
	 * Return unmodifiable collection of children
	 * @return children
	 */
	public Collection<Group> getChildren();
}