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
package de.jcup.code2doc.documentation.architecture;

import de.jcup.code2doc.api.Architecture;
import de.jcup.code2doc.core.extend.ExampleURLExtension;
import de.jcup.code2doc.core.internal.decorate.UseCaseExampleURLDecorator;

public class ARCHITECTURE_20_EXTENSIONS extends Architecture {

	@Override
	protected void doSetup(ArchitectureSetup setup) {
		/* @formatter:off */
		setup.setHeadline("Code2Doc extensions");
		setup.setDescription("Via a code2doc extension and a corresponding decorator project developers are able to decorate their specification automatically in some "
				+ "parts. E.g. "+ExampleURLExtension.class.getSimpleName()+" can be used via "+UseCaseExampleURLDecorator.class.getSimpleName()+" to add example urls automatical to UseCases");
		
		/* @formatter:on */
	}

}