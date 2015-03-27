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
import de.jcup.code2doc.demo.inside.documentation.usecase.entries.UC_02_SHOW_ENTRY_LIST;
import de.jcup.code2doc.demo.inside.documentation.usecase.entries.UC_03_CREATE_NEW_ENTRY;
import de.jcup.code2doc.demo.inside.documentation.usecase.entries.UC_04_DELETE_ENTRY;
import de.jcup.code2doc.demo.inside.documentation.usecase.entries.UC_05_EDIT_ENTRY;
import de.jcup.code2doc.demo.inside.entity.Entry;

@LinkToArchitecture(PersistenceConcept.class)
@LinkToUseCase(value = { UC_02_SHOW_ENTRY_LIST.class, UC_03_CREATE_NEW_ENTRY.class, UC_04_DELETE_ENTRY.class, UC_05_EDIT_ENTRY.class }, techInfoGroup = GROUP_DATA, type = TYPE_DAO)
public class EntryDAO extends DAO<Entry> {

	@LinkToUseCase(value = UC_02_SHOW_ENTRY_LIST.class, techInfoGroup = GROUP_DATA, type = TYPE_QUERY)
	private String QUERY_FETCH_ALL_ENTRIES = "select * from ENTRIES";

	/**
	 * Fetches all entries via query
	 * @return all entries
	 */
	@LinkToUseCase(value = UC_02_SHOW_ENTRY_LIST.class, techInfoGroup = GROUP_DATA, type= TYPE_DAO)
	public List<Entry> fetchAllEntries() {
		return fetchByQuery(QUERY_FETCH_ALL_ENTRIES);
	}

	@Override
	protected Class<Entry> getEntityClass() {
		return Entry.class;
	}

}