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

import de.jcup.code2doc.api.LinkToArchitecture;
import de.jcup.code2doc.api.LinkToUseCase;
import de.jcup.code2doc.demo.inside.documentation.architecture.PersistenceConcept;
import de.jcup.code2doc.demo.inside.documentation.usecase.UC_01_SHOW_BLOG_LIST;
import de.jcup.code2doc.demo.inside.entity.Blog;

@LinkToArchitecture(PersistenceConcept.class)
public class BlogDAO extends DAO<Blog>{

	@LinkToUseCase(value=UC_01_SHOW_BLOG_LIST.class,group=GROUP_DATA,type=TYPE_QUERY)
	private String QUERY_FETCH_ALL_BLOGS = "select b from BloggerContest b "; 
	
	
	@LinkToUseCase(value=UC_01_SHOW_BLOG_LIST.class,group=GROUP_DATA)
	public List<Blog> fetchAllBlogs(){
		return fetchByQuery(QUERY_FETCH_ALL_BLOGS);
	}

	@Override
	protected Class<Blog> getEntityClass() {
		return Blog.class;
	}
	
}