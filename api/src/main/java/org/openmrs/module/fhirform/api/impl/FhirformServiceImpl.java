/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.fhirform.api.impl;

import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.fhirform.Fhirform;
import org.openmrs.module.fhirform.FhirformDef;
import org.openmrs.module.fhirform.Item;
import org.openmrs.module.fhirform.api.FhirformService;
import org.openmrs.module.fhirform.api.dao.FhirformDao;

import java.util.List;

public class FhirformServiceImpl extends BaseOpenmrsService implements FhirformService {

	FhirformDao dao;

	UserService userService;

	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDao(FhirformDao dao) {
		this.dao = dao;
	}

	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Item getItemByUuid(String uuid) throws APIException {
		return dao.getItemByUuid(uuid);
	}

	@Override
	public Item saveItem(Item item) throws APIException {
		if (item.getOwner() == null) {
			item.setOwner(userService.getUser(1));
		}

		return dao.saveItem(item);
	}

	@Override
	public List getAllDef(String formtype) {
		return dao.getAllDef(formtype);
	}

	@Override
	public List getAllFhirforms(String status) {
		return dao.getAllFhirforms(status);
	}

	@Override
	public List getAllFhirformsByDef(FhirformDef FhirformDef) {
		return dao.getAllFhirformsByDef(FhirformDef);
	}

	@Override
	public List getAllFhirformsByPatient(Patient patient) {
		return dao.getAllFhirformsByPatient(patient);
	}

	@Override
	public Fhirform getFhirformById(int id) {
		return dao.getFhirformById(id);
	}

	@Override
	public FhirformDef getFhirformDefById(int id) {
		return dao.getFhirformDefById(id);
	}

	@Override
	public Fhirform saveFhirform(Fhirform Fhirform) {
		return dao.saveFhirform(Fhirform);
	}

	@Override
	public void purgeFhirform(Fhirform Fhirform) {
		dao.purgeFhirform(Fhirform);
	}

	@Override
	public FhirformDef saveFhirformDef(FhirformDef FhirformDef) {
		return dao.saveFhirformDef(FhirformDef);
	}

	@Override
	public void purgeFhirformDef(FhirformDef FhirformDef) {
		dao.purgeFhirformDef(FhirformDef);
	}
}
