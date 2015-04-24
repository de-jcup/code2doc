package de.jcup.code2doc.core.define;

/**
 * Implementations representing parent for a technical information.
 * @author Albert Tregnaghi
 *
 * @param <P> - parent of tech info
 */
public interface TechInfoParent <P>{

	/**
	 * Adds technological info with element headline as default headline - if already added the existing definition is returned
	 * @return technological info with element headline as default headline - if already added the existing definition is returned
	 */
	public TechInfoDefinition<P> addTechInfo();
	
	/**
	 * Adds technological info with custom headline instead of default name - if already added the existing definition is returned
	 * @param headline
	 * @return technological info with custom headline instead of default name - if already added the existing definition is returned
	 */
	public TechInfoDefinition<P> addTechInfo(String headline);
}
