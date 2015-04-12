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
package de.jcup.code2doc.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Use case abstract base class.<br>
 * <b>Every use case must be defined in its own class and have a DEFAULT
 * constructor without parameters</b> (because annotations can only handle class
 * types). To force this this code is not an interface but an abstract class
 * which must be extended. To avoid nerving getter implementations etc. there is
 * only one abstract setup method with a little fluent API.
 * 
 * @author de-jcup
 *
 */
public abstract class UseCase extends ContentElement {
	/**
	 * Definition for no constraint - is equivalent to null, but more readable
	 */
	public static final Class<? extends Constraint> NO_CONSTRAINT = null;

	private Map<Class<? extends Role>, Class<? extends Constraint>> rolesAndConstraints = new HashMap<Class<? extends Role>, Class<? extends Constraint>>();
	Set<Class<? extends Concept>> linkToConcepts = new LinkedHashSet<Class<? extends Concept>>();

	String exampleURL;
	String exactURL;
	String examplePictureResourcePath;

	public UseCase() {
		UseCaseSetup useCaseSetup = new UseCaseSetup();
		doSetup(useCaseSetup);
	}

	/**
	 * Setup the use case. Via given setup object all definitions can be done
	 * 
	 * @param setup
	 *            - used to do the setup
	 */
	protected abstract void doSetup(UseCaseSetup setup);

	/**
	 * Get an exact URL of this use case - normally only possible for web
	 * clients or web services
	 * 
	 * @return exact URL
	 */
	public final String getExactURL() {
		return exactURL;
	}

	/**
	 * Get an example URL of this use case - normally only possible for web
	 * clients or web services
	 * 
	 * @return example URL
	 */
	public final String getExampleURL() {
		return exampleURL;
	}

	/**
	 * Return all roles the use case is available for. When the returned
	 * collection is empty this means its available for EVERY role!<br>
	 * <br>
	 * The resulting collecting will be sorted by role class names
	 * @return sorted collection - never null
	 */
	public final Collection<Class<? extends Role>> getRoles() {
		List<Class<? extends Role>> list = new ArrayList<Class<? extends Role>>(rolesAndConstraints.keySet());
		Collections.sort(list, new RoleSortComparator());
		return list;
	}
	
	/**
	 * Returns constraint for given role - if there is one existing
	 * 	
	 * @param role - role for which a constraint is defined
	 * @return existing constraint or <code>null</code>
	 */
	public final Class<? extends Constraint> getConstraint(Class<? extends Role> role) {
		return rolesAndConstraints.get(role);
	}

	/**
	 * Returns example picture resource path or <code>null</code>
	 * 
	 * @return example picture resource path or <code>null</code>
	 */
	public final String getExamplePictureResourcePath() {
		return examplePictureResourcePath;
	}

	/**
	 * Returns all linked Concepts
	 * 
	 * @return all linked Concepts
	 */
	public Set<Class<? extends Concept>> getLinkedConcepts() {
		return Collections.unmodifiableSet(linkToConcepts);
	}

	/**
	 * Inner class for setup
	 * 
	 * @author de-jcup
	 *
	 */
	protected final class UseCaseSetup extends ContentSetup<UseCaseSetup> {

		/**
		 * Set roles for this use case is available. WITHOUT any constraint!
		 * 
		 * @param roles
		 * @return setup
		 */
		public final UseCaseSetup addRole(Class<? extends Role> role) {
			rolesAndConstraints.put(role, NO_CONSTRAINT);
			return this;
		}

		/**
		 * Set available for every potential role without any constraint - same
		 * as not defining anything, but more readable...
		 * 
		 * @param roles
		 * @return setup
		 */
		public final UseCaseSetup addAllRoles() {
			rolesAndConstraints.clear();
			return this;
		}

		/**
		 * Add roles where use case is available for but only with given
		 * constraint
		 * 
		 * @param role - a role where use case is available (with given constraint)
		 * @param constraint - constraining access of role
		 * @return setup
		 */
		public final UseCaseSetup addRole(Class<? extends Role> role, Class<? extends Constraint> constraint) {
			rolesAndConstraints.put(role, constraint);
			return this;
		}

		/**
		 * Set a example URL for this use case. The example URL depends on
		 * existing data! So it is possible this URL does not work without pre
		 * work. This is normally only interesting for WebClients or
		 * WebServices.
		 * 
		 * @param url
		 * @return setup
		 */
		public final UseCaseSetup setExampleURL(String url) {
			UseCase.this.exampleURL = url;
			return this;
		}

		/**
		 * Set the exact URL for this use case. This is normally only
		 * interesting for WebClients or WebServices.
		 * 
		 * @param url
		 * @return setup
		 */
		public final UseCaseSetup setExactURL(String url) {
			UseCase.this.exactURL = url;
			return this;
		}

		/**
		 * Set an example picture describing this use case.
		 * 
		 * @param resourcePath
		 * @return setup
		 */
		public final UseCaseSetup setExamplePicture(String resourcePath) {
			UseCase.this.examplePictureResourcePath = prepareResourcePath(resourcePath);
			return this;
		}

		/**
		 * Adds a link to a concept - normally this should be used when the use
		 * cases has a dedicated concept (with more information).
		 * 
		 * @param concept
		 * @return setup
		 */
		public final UseCaseSetup addLinkToConcept(Class<? extends Concept> concept) {
			if (concept == null) {
				return this;
			}
			linkToConcepts.add(concept);
			return this;
		}
	}

	private class RoleSortComparator implements Comparator<Class<? extends Role>>{
	
		@Override
		public int compare(Class<? extends Role> role1, Class<? extends Role> role2) {
			if (role1==null){
				if (role2==null){
					return 0;
				}
				return -1;
			}
			if (role2==null){
				return 1;
			}
			String name1= role1.getName();
			String name2= role2.getName();
			return name1.compareTo(name2);
		}
		
	}
}