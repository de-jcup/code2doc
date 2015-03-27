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

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TechInfoLinkAnnotationData {

	Class<?> linkedClass;
	String type;
	Method linkedMethod;
	Field linkedField;
	String techInfoGroup;
	
	public String getTechInfoGroup() {
		return techInfoGroup;
	}
	
	public String getType() {
		return type;
	}
	
	public Class<?> getLinkedClass() {
		return linkedClass;
	}
	
	public boolean isFieldLink(){
		return linkedField!=null;
	}
	
	public boolean isMethodLink(){
		return linkedMethod!=null;
	}
	
	public Method getLinkedMethod() {
		return linkedMethod;
	}
	
	public Field getLinkedField() {
		return linkedField;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((linkedClass == null) ? 0 : linkedClass.hashCode());
		result = prime * result + ((linkedField == null) ? 0 : linkedField.hashCode());
		result = prime * result + ((linkedMethod == null) ? 0 : linkedMethod.hashCode());
		result = prime * result + ((techInfoGroup == null) ? 0 : techInfoGroup.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TechInfoLinkAnnotationData other = (TechInfoLinkAnnotationData) obj;
		if (linkedClass == null) {
			if (other.linkedClass != null)
				return false;
		} else if (!linkedClass.equals(other.linkedClass))
			return false;
		if (linkedField == null) {
			if (other.linkedField != null)
				return false;
		} else if (!linkedField.equals(other.linkedField))
			return false;
		if (linkedMethod == null) {
			if (other.linkedMethod != null)
				return false;
		} else if (!linkedMethod.equals(other.linkedMethod))
			return false;
		if (techInfoGroup == null) {
			if (other.techInfoGroup != null)
				return false;
		} else if (!techInfoGroup.equals(other.techInfoGroup))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TechInfoLinkAnnotationData [linkedClass=" + linkedClass + ", type=" + type + ", linkedMethod=" + linkedMethod + ", linkedField=" + linkedField
				+ ", techInfoGroup=" + techInfoGroup + "]";
	}


}