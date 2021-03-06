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
package de.jcup.code2doc.core.filter;

/**
 * If at least one of the sub filters inside or expression filters the object then it is filtered
 * @author hexadez
 *
 */
public class OrFilterExpression extends FilterExpression implements Filter {

	@Override
	public boolean isFiltered(Object obj) {
		for (Filter filter: filters){
			if (filter.isFiltered(obj)){
				return true;
			}
		}
		return false;
	}
	
	public OrFilterExpression or(Filter filter){
		assertFilterNotNull(filter);
		filters.add(filter);
		return this;
	}

	public OrFilterExpression(Filter filter){
		assertFilterNotNull(filter);
		filters.add(filter);
	}
	
	
}