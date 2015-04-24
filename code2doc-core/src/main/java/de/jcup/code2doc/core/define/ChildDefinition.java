package de.jcup.code2doc.core.define;

/**
 * Implementations are a child of dedicated parent
 * @author Albert Tregnaghi
 *
 * @param <P> - parent of this child
 */
public interface ChildDefinition<P> {

	/**
	 * End this definition and return to parent
	 * @return
	 */
	public P endDefinition();
}
