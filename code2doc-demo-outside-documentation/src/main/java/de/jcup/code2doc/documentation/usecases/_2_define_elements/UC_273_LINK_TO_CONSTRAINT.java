package de.jcup.code2doc.documentation.usecases._2_define_elements;

import de.jcup.code2doc.api.LinkToConstraint;
import de.jcup.code2doc.api.UseCase;

public class UC_273_LINK_TO_CONSTRAINT extends UseCase{

	@Override
	protected void doSetup(UseCaseSetup setup) {
		setup.setHeadline("Annotate with @"+LinkToConstraint.class.getSimpleName());
		setup.setDescription("In code a method, a field or a class is annotated with @"+LinkToConstraint.class.getSimpleName()+".");
		
	}

}
