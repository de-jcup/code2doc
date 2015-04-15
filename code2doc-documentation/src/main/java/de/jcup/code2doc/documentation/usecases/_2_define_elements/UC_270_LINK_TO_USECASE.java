package de.jcup.code2doc.documentation.usecases._2_define_elements;

import de.jcup.code2doc.api.LinkToUseCase;
import de.jcup.code2doc.api.UseCase;

public class UC_270_LINK_TO_USECASE extends UseCase{

	@Override
	protected void doSetup(UseCaseSetup setup) {
		setup.setHeadline("Annotate with @"+LinkToUseCase.class.getSimpleName());
		setup.setDescription("In code a method, a field or a class is annotated with @"+LinkToUseCase.class.getSimpleName()+".");
		
	}

}
