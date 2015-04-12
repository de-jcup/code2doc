package de.jcup.code2doc.documentation.usecases._3_rendering;

import de.jcup.code2doc.api.UseCase;
import de.jcup.code2doc.documentation.roles.Roles;

public class UC_950_READ_PDF_OUTPUT extends UseCase{

	@Override
	protected void doSetup(UseCaseSetup setup) {
		setup.setHeadline("Read pdf output");
		setup.setDescription("A person is able to read the document");
		
		setup.addRole(Roles.DEVELOPER.class);
		setup.addRole(Roles.DOCUMENT_READER.class);
	}

}
