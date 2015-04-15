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
package de.jcup.code2doc.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.jcup.code2doc.api.LinkToArchitecture;
import de.jcup.code2doc.api.LinkToUseCase;
import de.jcup.code2doc.demo.documentation.architecture.PersistenceConcept;
import de.jcup.code2doc.demo.documentation.usecase.UC_12_CREATE_BLOG;
import de.jcup.code2doc.demo.documentation.usecase.lastbutnotleast.UC_08_LOGIN;

@LinkToArchitecture(value=PersistenceConcept.class, type=PersistenceConcept.TYPE_ENTITY)
@LinkToUseCase(UC_08_LOGIN.class)
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@LinkToUseCase(value = UC_08_LOGIN.class, type = "necessary fields for login", group = "internal fields")
	private String uid;

	private List<Blog> blogs = new ArrayList<Blog>();

	
	
	public User() {
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getUid() {
		return uid;
	}

	@LinkToUseCase(UC_12_CREATE_BLOG.class)
	public void addOwnedBlog(Blog blog) {
		blogs.add(blog);
	}

	public boolean hasBlogs() {
		return !blogs.isEmpty();
	}

}