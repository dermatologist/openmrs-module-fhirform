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
import org.openmrs.module.fhirform.FHIRForm;
import org.openmrs.module.fhirform.FHIRFormDef;
import org.openmrs.module.fhirform.Item;
import org.openmrs.module.fhirform.api.FHIRFormService;
import org.openmrs.module.fhirform.api.dao.FHIRFormDao;

import java.util.List;

public class FHIRFormServiceImpl extends BaseOpenmrsService implements FHIRFormService {
	
	FHIRFormDao dao;
	
	UserService userService;
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDao(FHIRFormDao dao) {
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
	public List getAllFHIRForms(String status) {
		return dao.getAllFHIRForms(status);
	}
	
	@Override
	public List getAllFHIRFormsByDef(FHIRFormDef FHIRFormDef) {
		return dao.getAllFHIRFormsByDef(FHIRFormDef);
	}
	
	@Override
	public List getAllFHIRFormsByPatient(Patient patient) {
		return dao.getAllFHIRFormsByPatient(patient);
	}
	
	@Override
	public FHIRForm getFHIRFormById(int id) {
		return dao.getFHIRFormById(id);
	}
	
	@Override
	public FHIRFormDef getFHIRFormDefById(int id) {
		return dao.getFHIRFormDefById(id);
	}
	
	@Override
	public FHIRForm saveFHIRForm(FHIRForm FHIRForm) {
		return dao.saveFHIRForm(FHIRForm);
	}
	
	@Override
	public void purgeFHIRForm(FHIRForm FHIRForm) {
		dao.purgeFHIRForm(FHIRForm);
	}
	
	@Override
	public FHIRFormDef saveFHIRFormDef(FHIRFormDef FHIRFormDef) {
		return dao.saveFHIRFormDef(FHIRFormDef);
	}
	
	@Override
	public void purgeFHIRFormDef(FHIRFormDef FHIRFormDef) {
		dao.purgeFHIRFormDef(FHIRFormDef);
	}
}
