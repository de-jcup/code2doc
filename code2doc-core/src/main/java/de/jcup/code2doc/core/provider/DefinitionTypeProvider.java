package de.jcup.code2doc.core.provider;

import de.jcup.code2doc.core.internal.define.DefinitionType;


/**
 * 
 * Implementations provide information about definition type
 * @author Albert Tregnaghi
 *
 */
public interface DefinitionTypeProvider extends ContentProvider{

	public DefinitionType getDefinitionType();
	
}
