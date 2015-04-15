package de.jcup.code2doc.documentation.usecases._2_define_elements;

import de.jcup.code2doc.api.LinkToArchitecture;
import de.jcup.code2doc.api.UseCase;

public class UC_271_LINK_TO_ARCHITECTURE extends UseCase{

	@Override
	protected void doSetup(UseCaseSetup setup) {
		setup.setHeadline("Annotate with @"+LinkToArchitecture.class.getSimpleName());
		setup.setDescription("In code a class is annotated with @"+LinkToArchitecture.class.getSimpleName()+".");
		
	}

}
