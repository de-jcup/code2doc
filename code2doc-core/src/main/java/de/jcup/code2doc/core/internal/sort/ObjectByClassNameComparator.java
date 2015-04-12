package de.jcup.code2doc.core.internal.sort;

import java.util.Comparator;

/**
 * ObjectByClassNameComparator is a simple comparator using the full class names to compare.
 * When the object to compare is a class itself no class resolving is done.
 * @author de-jcup
 *
 */
public class ObjectByClassNameComparator implements Comparator<Object> {

	@Override
	public int compare(Object object1, Object object2) {
		if (object1==object2){
			return 0;
		}
		if (object1 == null) {
			return -1;
		}
		if (object2 == null) {
			return 1;
		}
		/* resolve classes to check */
		Class<?> clazz1 = fetchClass(object1);
		Class<?> clazz2 = fetchClass(object2);
		if (clazz1==clazz2){
			return 0;
		}
		
		String name1 = clazz1.getName();
		String name2 = clazz2.getName();
		
		return name1.compareTo(name2);
	}

	private Class<?> fetchClass(Object object1) {
		Class<?> clazz1 = null;
		if (object1 instanceof Class<?>){
			clazz1=(Class<?>) object1;
		}else{
			clazz1 = object1.getClass();
		}
		return clazz1;
	}

}
