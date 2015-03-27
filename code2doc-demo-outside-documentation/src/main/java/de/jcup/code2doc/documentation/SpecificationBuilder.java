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
package de.jcup.code2doc.documentation;

import de.jcup.code2doc.api.Architecture;
import de.jcup.code2doc.api.Concept;
import de.jcup.code2doc.api.UseCase;
import de.jcup.code2doc.core.Factory;
import de.jcup.code2doc.core.define.Specification;
import de.jcup.code2doc.documentation.architecture.DecoratorArchitecture;
import de.jcup.code2doc.documentation.architecture.SpecificationArchitecture;
import de.jcup.code2doc.documentation.concept.Code2docConcept;
import de.jcup.code2doc.documentation.usecases._1_specification._1_create_inside.UC_110_CREATE_SPECIFICATION__INSIDE;
import de.jcup.code2doc.documentation.usecases._1_specification._2_create_outside.UC_120_CREATE_SPECIFICATION__OUTSIDE;
import de.jcup.code2doc.documentation.usecases._2_define_elements.UC_210_CREATE_CONCEPT;
import de.jcup.code2doc.documentation.usecases._2_define_elements.UC_220_CREATE_USECASE;
import de.jcup.code2doc.documentation.usecases._2_define_elements.UC_230_CREATE_ARCHITECTURE;
import de.jcup.code2doc.documentation.usecases._3_rendering.UC_900_CREATE_PDF_OUTPUT;
/**
 * Eat your own dog food... via the uncomfortable approach (outside).
 * @author de-jcup
 *
 */
public class SpecificationBuilder {

	public Specification start() {
		/* @formatter:off */
		
		Specification spec = Factory.createEmptySpecification();
		spec.
			addConcept(Code2docConcept.class).
			addArchitecture(SpecificationArchitecture.class).
			addArchitecture(DecoratorArchitecture.class).
			
			addUseCase(UC_110_CREATE_SPECIFICATION__INSIDE.class).
			addUseCaseAndDefine(UC_120_CREATE_SPECIFICATION__OUTSIDE.class).
				addTechInfo("Classes used inside specification does extend").
					addLinkToJava("Architecture parts", Architecture.class).
					addLinkToJava("Use cases", UseCase.class). 
					addLinkToJava("Concepts", Concept.class). 
				endTechInfo().
			endDefinition().
			addUseCase(UC_210_CREATE_CONCEPT.class).
			addUseCaseAndDefine(UC_220_CREATE_USECASE.class).
			endDefinition().
			addUseCase(UC_230_CREATE_ARCHITECTURE.class).
			addUseCaseAndDefine(UC_900_CREATE_PDF_OUTPUT.class)
			
			;

		/* @formatter:on */
		
		return spec;
	}
}