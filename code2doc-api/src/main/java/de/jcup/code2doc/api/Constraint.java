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
 * 
 * Restricts an {@link UseCase}.
 * 
 * @author Albert Tregnaghi
 *
 */
public abstract class Constraint extends ContentElement{
	
	public Constraint(){
		ConstraintSetup setup = new ConstraintSetup();
		doSetup(setup);
	}

	/**
	 * Setup the use case. Via given setup object all definitions can be done
	 * 
	 * @param setup
	 *            - used to do the setup
	 */
	protected abstract void doSetup(ConstraintSetup setup);
	
	
	protected class ConstraintSetup extends ContentSetup<ConstraintSetup>{
		private ConstraintSetup(){
			
		}
	}
}