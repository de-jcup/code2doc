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
package de.jcup.code2doc.core.internal.group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import de.jcup.code2doc.core.internal.define.Group;

/**
 * A package group provider knows all of its groups - so also the child parent
 * relationships.
 * 
 * @author de-jcup
 *
 */
public class PackageGroupProvider {

	private final Pattern packagePattern = Pattern.compile("^[a-zA-Z_\\$][\\w\\$]*(?:\\.[a-zA-Z_\\$][\\w\\$]*)*$");

	private Map<String, PackageGroupImpl> map = new TreeMap<String, PackageGroupImpl>();

	/*
	 * Freeze state . To handle parent child relations on a later state we use
	 * the freeze state to determine when no more groups are added to the
	 * provider.
	 */
	private boolean freezed;

	
	/**
	 * Get the group for a given package string
	 * 
	 * @param packageName
	 *            - name of the package (e.g. "de.jcup.code2doc.test")
	 * @return group
	 */
	public Group getGroup(String packageName) {
		if (packageName == null) {
			packageName = "";
		}
		/* validate package correct */
		if (!packagePattern.matcher(packageName).matches()) {
			throw new IllegalArgumentException("Not a correct package name:" + packageName);
		}

		/* lazily create group impl when not already created: */
		PackageGroupImpl group = map.get(packageName);
		if (group == null) {
			assertNotFreezed();
			group = new PackageGroupImpl(this, packageName);
			map.put(packageName, group);
		}
		return group;
	}

	/**
	 * Freeze package group provider. Build parent child relations and do no more support new unknown groups
	 * @return the provider itself
	 */
	public PackageGroupProvider freeze() {
		freezed = true;
		buildParentChildRelationShip();
		return this;
	}
	
	/**
	 * Unfreeze package group provider. Build parent child relations and do no more support new unknown groups
	 * @return the provider itself
	 */
	public PackageGroupProvider unfreeze() {
		freezed=false;
		for (PackageGroupImpl p: map.values()){
			p.parent=null;
			p.children.clear();
		}
		return this;
	}

	private void buildParentChildRelationShip() {
		 /* FIXME de-jcup, 17.03.2015 :impl */
		for (String packageKey: map.keySet()){
			PackageGroupImpl group = map.get(packageKey);
			/* resolve parent*/
			Collection<String> potentialParents = createParentPackageNames(packageKey);
			PackageGroupImpl parentGroup = null; 
			for (String potentialParent: potentialParents){
				parentGroup = map.get(potentialParent);
				if (parentGroup!=null){
					break;
				}
			}
			group.parent=parentGroup;
			if (group.parent!=null){
				group.parent.children.add(group);
			}
		}
		
	}

	/**
	 * Returns parent group or null if no parent available
	 * 
	 * @param packageGroupImpl
	 * @return
	 * @throws IllegalStateException when not freezed
	 */
	Group getParent(PackageGroupImpl packageGroupImpl) {
		assertFreezed();
		return packageGroupImpl.parent;
	}

	Collection<String> createParentPackageNames(String packageName) {
		List<String> list = new ArrayList<String>();
		if (packageName==null){
			return list;
		}
		/* ->example: de.jcup.test.package1.package2*/
		int lastIndex = packageName.lastIndexOf(".");
		if (lastIndex == -1) {
			return list;
		}
		/* remove last .*/
		packageName= packageName.substring(0,lastIndex);
		/* ->example: de.jcup.test.package1*/
		
		/* split into parts of package */
		String[] parts = packageName.split("\\.");
		/* ->example: parts: de, jcup, test, package1*/
		for (int currentMax = parts.length; currentMax >= 0; currentMax--) {
			StringBuilder sb = new StringBuilder();
			for (int pos = 0; pos < currentMax; pos++) {
				sb.append(parts[pos]);
				if (pos < currentMax-1) {
					sb.append(".");
				}
			}
			list.add(sb.toString());
		}
		return list;
	}

	/**
	 * Returns children of given package group
	 * 
	 * @param packageGroupImpl
	 * @return
	 * 
	 * @throws IllegalStateException
	 */
	@SuppressWarnings("unchecked")
	Collection<Group> getChildren(PackageGroupImpl packageGroupImpl) {
		assertFreezed();
		Set<? extends Group> l = Collections.unmodifiableSet(packageGroupImpl.children);
		return (Collection<Group>) l;
	}

	
	/**
	 * 
	 * @return true when freezed
	 */
	public boolean isFreezed() {
		return freezed;
	}

	private void assertFreezed() {
		if (!freezed) {
			throw new IllegalStateException("Packages are not freezed. This is necessary for this action!");
		}
	}

	private void assertNotFreezed() {
		if (freezed) {
			throw new IllegalStateException("Packages are freezed. So this action is not allowed!");
		}
	}


}