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

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ TYPE,METHOD })
/**
 * Link this to given architecture
 * @author de-jcup
 *
 */
public @interface LinkToArchitecture {
	/**
	 * Uses cases
	 * @return classes of uses cases
	 */
	Class<? extends Architecture>[] value() default {};
	
	/**
	 * Link type - used for identification inside TECHINFO
	 * @return type -default is empty
	 */
	String type() default "";
	

	/**
	 * Link techInfoGroup
	 * @return name of TECHINFO group - default is empty
	 */
	String techInfoGroup() default "";
}