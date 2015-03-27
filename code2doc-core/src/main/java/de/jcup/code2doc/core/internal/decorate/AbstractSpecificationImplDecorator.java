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
package de.jcup.code2doc.core.internal.decorate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jcup.code2doc.core.decorate.Decorator;
import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.core.internal.define.SpecificationImpl;

abstract class AbstractSpecificationImplDecorator implements Decorator<Specification>{

	private final static Logger LOG = LoggerFactory.getLogger(AbstractSpecificationImplDecorator.class);
	
	@Override
	public Specification decorate(Specification specification) {
		if (specification instanceof SpecificationImpl){
			long time1 = System.currentTimeMillis();
			if (LOG.isDebugEnabled()){
				LOG.debug("start decoration of specification. decorator="+getClass().getSimpleName());
			}
			decorateImpl((SpecificationImpl) specification);
			
			long time2 = System.currentTimeMillis();
			if (LOG.isDebugEnabled()) {
				LOG.debug("decoration done in "+(time2-time1)+ " milliseconds");
			}
			return specification;
		}else{
			throw new IllegalArgumentException("Only specification impl supported.");
		}
		
	}
	
	protected abstract void decorateImpl(SpecificationImpl specificationImpl);

}