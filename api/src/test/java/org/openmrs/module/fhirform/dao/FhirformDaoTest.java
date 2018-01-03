/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.fhirform.api.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.fhirform.FhirformDef;
import org.openmrs.module.fhirform.Item;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * It is an integration test (extends BaseModuleContextSensitiveTest), which verifies DAO methods
 * against the in-memory H2 database. The database is initially loaded with data from
 * standardTestDataset.xml in openmrs-api. All test methods are executed in transactions, which are
 * rolled back by the end of each test method.
 */
public class FhirformDaoTest extends BaseModuleContextSensitiveTest {
	
	@Autowired
	FhirformDao dao;
	
	@Autowired
	UserService userService;
	
	@Test
	@Ignore("Unignore if you want to make the Item class persistable, see also Item and liquibase.xml")
	public void saveItem_shouldSaveAllPropertiesInDb() {
		//Given
		Item item = new Item();
		item.setDescription("some description");
		item.setOwner(userService.getUser(1));
		
		//When
		dao.saveItem(item);
		
		//Let's clean up the cache to be sure getItemByUuid fetches from DB and not from cache
		Context.flushSession();
		Context.clearSession();
		
		//Then
		Item savedItem = dao.getItemByUuid(item.getUuid());
		
		assertThat(savedItem, hasProperty("uuid", is(item.getUuid())));
		assertThat(savedItem, hasProperty("owner", is(item.getOwner())));
		assertThat(savedItem, hasProperty("description", is(item.getDescription())));
	}

    @Test
    public void saveFhirformDef_shouldSaveAllPropertiesinDb() {
        FhirformDef fhirformDef = new FhirformDef();
        fhirformDef.setQuestionnaireUrl("http://example.com/dstu3/");
        fhirformDef.setQuestionnaire_id("123245");
        fhirformDef.setVersion("10");
        fhirformDef.setComments("Test");

        dao.saveFhirformDef(fhirformDef);

        //Let's clean up the cache to be sure getItemByUuid fetches from DB and not from cache
        Context.flushSession();
        Context.clearSession();

        //Then
        FhirformDef savedFhirformDef = dao.getFhirformDefById(fhirformDef.getId());

        assertThat(savedFhirformDef, hasProperty("uuid", is(savedFhirformDef.getUuid())));
        assertThat(savedFhirformDef, hasProperty("version", is(savedFhirformDef.getVersion())));
        assertThat(savedFhirformDef, hasProperty("questionnaire_id", is(savedFhirformDef.getQuestionnaire_id())));

        dao.purgeFhirformDef(savedFhirformDef);
    }
}
