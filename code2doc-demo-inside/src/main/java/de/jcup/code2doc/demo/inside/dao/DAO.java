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
package de.jcup.code2doc.demo.inside.dao;

import static de.jcup.code2doc.demo.inside.documentation.architecture.PersistenceConcept.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.jcup.code2doc.api.LinkToArchitecture;
import de.jcup.code2doc.demo.inside.documentation.architecture.PersistenceConcept;

@LinkToArchitecture(value=PersistenceConcept.class,group=GROUP_DAO_BASE)
public abstract class DAO<T> {

	private static final String PERSISTENCE_UNIT_NAME = "code2doc-demo-internal";
	private static EntityManagerFactory factory;

	static{
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	protected EntityManager em;
	
	public DAO(){
		 if (getEntityClass()==null){
			 throw new IllegalStateException("entity class not implemented!");
		 }
		 em = factory.createEntityManager();
	}
	
	@LinkToArchitecture(value=PersistenceConcept.class,group=GROUP_DAO_BASE)
	public T findById(Long id){
		T entity = em.find(getEntityClass(), id);
		return entity;
	}
	protected abstract Class<T> getEntityClass();
	
	@LinkToArchitecture(value=PersistenceConcept.class,group=GROUP_DAO_BASE)
	public T update(T entity){
		em.getTransaction().begin();
		
		em.merge(entity);
		
		em.getTransaction().commit();
		return entity;
	}

	@LinkToArchitecture(value=PersistenceConcept.class,group=GROUP_DAO_BASE)
	public T create(T entity){
		em.getTransaction().begin();
		
		em.persist(entity);
		
		em.getTransaction().commit();
		return entity;
	}
	
	@LinkToArchitecture(value=PersistenceConcept.class,group=GROUP_DAO_BASE)
	public T delete(T entity){
		em.getTransaction().begin();
		
		em.remove(entity);
		
		em.getTransaction().commit();
		return entity;
	}
	
	@LinkToArchitecture(value=PersistenceConcept.class,group=GROUP_DAO_BASE, type="base query method")
	protected List<T> fetchByQuery(String queryString){
		Query query = em.createQuery(queryString);
		@SuppressWarnings("unchecked")
		List<T> list = query.getResultList();
		return list;
	}
	
}
	