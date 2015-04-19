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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import de.jcup.code2doc.core.internal.define.Group;

/**
 * A package based group. So its possible to add groups automatically via
 * element classes inside specificatio. <br>
 * <br>
 * An example:<br>
 * 
 * <pre>
 *  de.jcup.code2doc.test.usecases.UC1.java
 *  de.jcup.code2doc.test.usecases.users.user.UC2.java
 *  de.jcup.code2doc.test.usecases.users.createUser.UC3.java
 * </pre>
 * 
 * In the example we got the root group for usecases at
 * "de.jcup.code2doc.test.usecases" - where UC1 is contained. If UC1 would not
 * exist UC2 and UC3 had "de.jcup.code2doc.test.usecases.users" as root group.
 * <br>
 * The root of the children parent ship resolvement is done by the {@linkPlain PackageGroupProvider}
 * @author Albert Tregnaghi
 *
 */
class PackageGroupImpl implements Group {
	private String packageName;
	private PackageGroupProvider provider;
	
	Set<PackageGroupImpl> children = new LinkedHashSet<PackageGroupImpl>();
	PackageGroupImpl parent;

	PackageGroupImpl(PackageGroupProvider provider ,String packageName) {
		if (packageName == null) {
			packageName = "";
		}
		this.provider=provider;
		this.packageName = packageName;
	}


	@Override
	public int compareTo(Group o) {
		if (o==null){
			return 1;
		}
		if (o==this){
			return 0;
		}
		if (! (o instanceof PackageGroupImpl)){
			/* we cannot differ this so we say - its equal...*/
			return 0;
		}
		PackageGroupImpl impl = (PackageGroupImpl) o;
		/* we simply compare the package name */
		int compareResult= packageName.compareTo(impl.packageName);
		return compareResult;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
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
		PackageGroupImpl other = (PackageGroupImpl) obj;
		if (packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!packageName.equals(other.packageName))
			return false;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName()+":"+getName()+", provider="+provider;
	}


	@Override
	public String getName() {
		return packageName;
	}

	@Override
	public Group getParent() {
		/* the provider knows all other groups - so just ask ...*/
		return provider.getParent(this);
	}


	@Override
	public Collection<Group> getChildren() {
		return provider.getChildren(this);
	}
	

}