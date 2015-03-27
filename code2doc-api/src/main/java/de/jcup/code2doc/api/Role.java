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


/**
 * A role represents a group of options a user has inside the specification. <br><br>
 * Examples:
 * <ul>
 *   <li>Consumer</li>
 *   <li>Author</li>
 *   <li>Administrator</li>
 * </ul>
 * @author de-jcup
 *
 */
public abstract class Role extends ContentElement{
	
	public Role(){
		RoleSetup setup = new RoleSetup();
		doSetup(setup);
	}
	
	/**
	 * Setup the role. Via given setup object all definitions can be done
	 * 
	 * @param setup
	 *            - used to do the setup
	 */
	protected abstract void doSetup(RoleSetup setup);
	
	protected class RoleSetup extends ContentSetup<RoleSetup>{
		
	}
}