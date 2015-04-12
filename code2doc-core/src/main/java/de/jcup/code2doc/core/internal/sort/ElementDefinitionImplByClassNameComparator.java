package de.jcup.code2doc.core.internal.sort;

import java.util.Comparator;

import de.jcup.code2doc.core.internal.define.ElementDefinitionImpl;

/**
 * Compares element definitin impl by the class names of their elements
 * @author de-jcup
 *
 */
public class ElementDefinitionImplByClassNameComparator implements Comparator<ElementDefinitionImpl<?, ?, ?>>{

	private ObjectByClassNameComparator classNameComparator = new ObjectByClassNameComparator();
	
	@Override
	public int compare(ElementDefinitionImpl<?, ?, ?> o1, ElementDefinitionImpl<?, ?, ?> o2) {
		if (o1==o2){
			return 0;
		}
		if (o1==null){
			return -1;
		}
		if (o2==null){
			return 1;
		}
		return classNameComparator.compare(o1.getElement(), o2.getElement());
	}

}
