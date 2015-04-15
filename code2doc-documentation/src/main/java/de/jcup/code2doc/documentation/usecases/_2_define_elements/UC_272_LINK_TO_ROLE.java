package de.jcup.code2doc.documentation.usecases._2_define_elements;

import de.jcup.code2doc.api.LinkToRole;
import de.jcup.code2doc.api.UseCase;

public class UC_272_LINK_TO_ROLE extends UseCase{

	@Override
	protected void doSetup(UseCaseSetup setup) {
		setup.setHeadline("Annotate with @"+LinkToRole.class.getSimpleName());
		setup.setDescription("In code a method, a field or a class is annotated with @"+LinkToRole.class.getSimpleName()+".");
		
	}

}
