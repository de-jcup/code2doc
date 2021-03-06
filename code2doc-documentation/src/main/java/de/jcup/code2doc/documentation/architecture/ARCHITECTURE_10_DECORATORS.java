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

import java.util.Locale;

import de.jcup.code2doc.api.Architecture;

public class ARCHITECTURE_10_DECORATORS extends Architecture{

	@Override
	protected void doSetup(ArchitectureSetup architectureSetup) {
		defaultLocale(architectureSetup);
		germanLocale(architectureSetup);
	}

	private void defaultLocale(ArchitectureSetup architectureSetup) {
		/* @formatter:off */
		architectureSetup.setHeadline("Specification decorators");
		architectureSetup.setDescription("Specification decorators do automatically extend an existing or empty specification");
		
		/* @formatter:on */
	}
	
	@SuppressWarnings("deprecation") // incubating localization feature...
	private void germanLocale(ArchitectureSetup architectureSetup) {
		Locale locale = Locale.GERMAN;
		/* @formatter:off */
		architectureSetup.setHeadline("Spezifikation-Dekorierer", locale);
		architectureSetup.setDescription("Spezifikation-Dekorierer erweitern automatisch eine leere oder bestehende Spezifikation.",locale);
		/* @formatter:on */
	}

}