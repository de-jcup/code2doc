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
package de.jcup.code2doc.generator;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.core.internal.define.SpecificationImpl;

public abstract class AbstractSpecificationGenerator<R> extends AbstractGenerator<R,Specification> {

	private final static Logger LOG = LoggerFactory.getLogger(AbstractSpecificationGenerator.class);
	
	@Override
	public final R generate(Specification spec) throws IOException {
		
		if (spec instanceof SpecificationImpl) {
			long time1 = System.currentTimeMillis();
			if (LOG.isDebugEnabled()){
				LOG.debug("["+getClass().getSimpleName()+"] start generation of specification");
			}
			SpecificationImpl specImpl = (SpecificationImpl) spec;
			/* freeze while generating*/
			boolean alreadyFreezedByOtherGenerator = specImpl.isFreezed();
			if (!alreadyFreezedByOtherGenerator){
				specImpl.freeze();
			}
			long time2 = System.currentTimeMillis();
			if (!alreadyFreezedByOtherGenerator){
				if (LOG.isDebugEnabled()) {
					LOG.debug("- freeze was done in "+(time2-time1)+ " milliseconds");
				}
			}
			R output = generateImpl(specImpl);
			long time3 = System.currentTimeMillis();
			if (LOG.isDebugEnabled()) {
				LOG.debug("- generation was done in "+(time3-time2)+ " milliseconds");
			}
			/* unfreeze - so changes are possible again*/
			if (!alreadyFreezedByOtherGenerator){
				specImpl.unfreeze();
				long time4 = System.currentTimeMillis();
				if (LOG.isDebugEnabled()) {
					if (!specImpl.isFreezed()){
						
					}
					LOG.debug("- unfreeze was done in "+(time4-time3)+ " milliseconds");
				}
			}
			if (LOG.isDebugEnabled()){
				LOG.debug("["+getClass().getSimpleName()+"] DONE");
			}
			return output;
		}
		throw new IllegalArgumentException("This kind of specification is not supported:" + spec);
	}
	
	/**
	 * Generation implementation
	 * 
	 * @param spec
	 * @return
	 * @throws IOException
	 */
	protected abstract R generateImpl(SpecificationImpl spec) throws IOException;

}