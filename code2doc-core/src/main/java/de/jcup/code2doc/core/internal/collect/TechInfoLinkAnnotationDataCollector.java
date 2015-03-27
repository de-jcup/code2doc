/*
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
* 
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.*/
package de.jcup.code2doc.core.internal.collect;

import static de.jcup.code2doc.core.internal.util.Validation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import de.jcup.code2doc.api.LinkToArchitecture;
import de.jcup.code2doc.api.LinkToConstraint;
import de.jcup.code2doc.api.LinkToRole;
import de.jcup.code2doc.api.LinkToUseCase;

/**
 * Collector for all annotations compatible with techInfo (value=classes, type,
 * techInfoGroup). Be aware when using it - because annotations have lack of
 * interfaces or polymorphism only the internal known annotation types are
 * possible :<br>
 * <ul>
 * <li>{@link LinkToArchitecture}</li>
 * <li>{@link LinkToUseCase}</li>
 * <li>{@link LinkToConstraint}</li>
 * <li>{@link LinkToRole}</li>
 * </ul>
 * 
 * @author hexadez
 *
 * @param <CONTENT_ELEMENT>
 * @param <ANNOTATION>
 */
class TechInfoLinkAnnotationDataCollector<CONTENT_ELEMENT, ANNOTATION extends Annotation> {

	@SuppressWarnings("unchecked")
	private final Class<? extends CONTENT_ELEMENT>[] NOTHING_FOUND = new Class[] {};
	private Reflections reflections;
	private Class<ANNOTATION> annotation;

	public TechInfoLinkAnnotationDataCollector(Class<ANNOTATION> annotation, Reflections reflections) {
		notNull(annotation, "annotation clazz must not be null!");
		notNull(reflections, "reflections must not be null!");
		this.reflections = reflections;
		this.annotation = annotation;
	}

	public Map<Class<? extends CONTENT_ELEMENT>, List<TechInfoLinkAnnotationData>> collectLinkingToElement() {

		Map<Class<? extends CONTENT_ELEMENT>, List<TechInfoLinkAnnotationData>> fetchedAnnotationData = new HashMap<Class<? extends CONTENT_ELEMENT>, List<TechInfoLinkAnnotationData>>();

		Set<Class<?>> types = reflections.getTypesAnnotatedWith(annotation);
		Set<Method> methods = reflections.getMethodsAnnotatedWith(annotation);
		Set<Field> fields = reflections.getFieldsAnnotatedWith(annotation);

		/* handle class annotations */
		for (Class<?> type : types) {
			Class<? extends CONTENT_ELEMENT>[] useCases = getValue(type);
			String techInfoType = getType(type);
			String techInfoGroup = getTechInfoGroup(type);
			for (Class<? extends CONTENT_ELEMENT> useCase : useCases) {
				TechInfoLinkAnnotationData data = new TechInfoLinkAnnotationData();
				data.linkedClass = type;
				data.type = techInfoType;
				data.techInfoGroup = techInfoGroup;
				ensureList(fetchedAnnotationData, useCase).add(data);
			}
		}

		/* handle method annotations */
		for (Method method : methods) {
			Class<? extends CONTENT_ELEMENT>[] useCases = getValue(method);
			String group = getType(method);
			String techInfoGroup = getTechInfoGroup(method);
			for (Class<? extends CONTENT_ELEMENT> useCase : useCases) {
				TechInfoLinkAnnotationData data = new TechInfoLinkAnnotationData();
				data.linkedClass = method.getDeclaringClass();
				data.type = group;
				data.linkedMethod = method;
				data.techInfoGroup = techInfoGroup;
				ensureList(fetchedAnnotationData, useCase).add(data);
			}
		}

		/* handle field annotations */
		for (Field field : fields) {
			Class<? extends CONTENT_ELEMENT>[] useCases = getValue(field);
			String type = getType(field);
			String techInfoGroup = getTechInfoGroup(field);
			for (Class<? extends CONTENT_ELEMENT> useCase : useCases) {
				TechInfoLinkAnnotationData data = new TechInfoLinkAnnotationData();
				data.linkedClass = field.getDeclaringClass();
				data.type = type;
				data.linkedField = field;
				data.techInfoGroup = techInfoGroup;
				ensureList(fetchedAnnotationData, useCase).add(data);
			}
		}

		return fetchedAnnotationData;

	}

	private List<TechInfoLinkAnnotationData> ensureList(Map<Class<? extends CONTENT_ELEMENT>, List<TechInfoLinkAnnotationData>> fetchedAnnotationData,
			Class<? extends CONTENT_ELEMENT> ucClass) {
		List<TechInfoLinkAnnotationData> list = fetchedAnnotationData.get(ucClass);
		if (list == null) {
			list = new ArrayList<TechInfoLinkAnnotationData>();
			fetchedAnnotationData.put(ucClass, list);
		}
		return list;
	}

	private Class<? extends CONTENT_ELEMENT>[] getValue(AnnotatedElement element) {
		ANNOTATION result = element.getAnnotation(annotation);
		if (result == null) {
			return NOTHING_FOUND;
		}
		Class<? extends CONTENT_ELEMENT>[] useCases = getValue(result);
		return useCases;
	}

	@SuppressWarnings("unchecked")
	private Class<? extends CONTENT_ELEMENT>[] getValue(ANNOTATION found) {
		if (found instanceof LinkToArchitecture) {
			LinkToArchitecture l = (LinkToArchitecture) found;
			return (Class<? extends CONTENT_ELEMENT>[]) l.value();
		}
		if (found instanceof LinkToUseCase) {
			LinkToUseCase l = (LinkToUseCase) found;
			return (Class<? extends CONTENT_ELEMENT>[]) l.value();
		}
		if (found instanceof LinkToRole) {
			LinkToRole l = (LinkToRole) found;
			return (Class<? extends CONTENT_ELEMENT>[]) l.value();
		}
		if (found instanceof LinkToConstraint) {
			LinkToConstraint l = (LinkToConstraint) found;
			return (Class<? extends CONTENT_ELEMENT>[]) l.value();
		}
		throw new IllegalArgumentException("get type is not supported for " + annotation);
	}

	private String getType(AnnotatedElement element) {
		ANNOTATION result = element.getAnnotation(annotation);
		if (result == null) {
			return "";
		}
		String type = getType(result);
		return type;
	}

	private String getType(ANNOTATION found) {
		if (found instanceof LinkToArchitecture) {
			LinkToArchitecture l = (LinkToArchitecture) found;
			return l.type();
		}
		if (found instanceof LinkToUseCase) {
			LinkToUseCase l = (LinkToUseCase) found;
			return l.type();
		}
		if (found instanceof LinkToRole) {
			LinkToRole l = (LinkToRole) found;
			return l.type();
		}
		if (found instanceof LinkToConstraint) {
			LinkToConstraint l = (LinkToConstraint) found;
			return l.type();
		}
		throw new IllegalArgumentException("get type is not supported for " + annotation);
	}

	private String getTechInfoGroup(AnnotatedElement element) {
		ANNOTATION result = element.getAnnotation(annotation);
		if (result == null) {
			return "";
		}
		String techInfoGroup = getTechInfoGroup(result);
		return techInfoGroup;
	}

	private String getTechInfoGroup(ANNOTATION found) {
		if (found instanceof LinkToArchitecture) {
			LinkToArchitecture l = (LinkToArchitecture) found;
			return l.techInfoGroup();
		}
		if (found instanceof LinkToUseCase) {
			LinkToUseCase l = (LinkToUseCase) found;
			return l.techInfoGroup();
		}
		if (found instanceof LinkToRole) {
			LinkToRole l = (LinkToRole) found;
			return l.techInfoGroup();
		}
		if (found instanceof LinkToConstraint) {
			LinkToConstraint l = (LinkToConstraint) found;
			return l.techInfoGroup();
		}
		throw new IllegalArgumentException("get techinfo is not supported for " + annotation);
	}
}