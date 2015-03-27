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
package de.jcup.code2doc.demo.inside.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.jcup.code2doc.api.LinkToArchitecture;
import de.jcup.code2doc.api.LinkToUseCase;
import de.jcup.code2doc.demo.inside.documentation.architecture.PersistenceConcept;
import de.jcup.code2doc.demo.inside.documentation.usecase.entries.UC_03_CREATE_NEW_ENTRY;
import de.jcup.code2doc.demo.inside.documentation.usecase.entries.UC_04_DELETE_ENTRY;

@LinkToArchitecture(value=PersistenceConcept.class, type=PersistenceConcept.TYPE_ENTITY)
@Entity
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private User owner;
	private List<Entry> entries = new ArrayList<Entry>();

	public Blog() {
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
		owner.addOwnedBlog(this);
	}
	
	public Long getId() {
		return id;
	}

	
	@LinkToUseCase(UC_03_CREATE_NEW_ENTRY.class)
	public void add(Entry entry){
		entries.add(entry);
	}
	
	@LinkToUseCase(UC_04_DELETE_ENTRY.class)
	public void delete(Entry entry){
		entries.remove(entry);
	}

	public User getOwner() {
		return owner;
	}
}