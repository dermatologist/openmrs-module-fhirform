/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.fhirform.api;

import org.openmrs.Patient;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.fhirform.FHIRForm;
import org.openmrs.module.fhirform.FHIRFormConfig;
import org.openmrs.module.fhirform.FHIRFormDef;
import org.openmrs.module.fhirform.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The main service of this module, which is exposed for other modules. See
 * moduleApplicationContext.xml on how it is wired up.
 */
public interface FHIRFormService extends OpenmrsService {
	
	/**
	 * Returns an item by uuid. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized()
	@Transactional(readOnly = true)
	Item getItemByUuid(String uuid) throws APIException;
	
	/**
	 * Saves an item. Sets the owner to superuser, if it is not set. It can be called by users with
	 * this module's privilege. It is executed in a transaction.
	 * 
	 * @param item
	 * @return
	 * @throws APIException
	 */
	@Authorized(FHIRFormConfig.MODULE_PRIVILEGE)
	@Transactional
	Item saveItem(Item item) throws APIException;
	
	@Transactional(readOnly = true)
	List<FHIRFormDef> getAllDef(String formtype);
	
	@Transactional(readOnly = true)
	List<FHIRForm> getAllFHIRForms(String status);
	
	@Transactional(readOnly = true)
	FHIRForm getFHIRFormById(int id);
	
	FHIRForm saveFHIRForm(FHIRForm FHIRForm);
	
	void purgeFHIRForm(FHIRForm FHIRForm);
	
	@Transactional(readOnly = true)
	FHIRFormDef getFHIRFormDefById(int id);
	
	FHIRFormDef saveFHIRFormDef(FHIRFormDef FHIRFormDef);
	
	void purgeFHIRFormDef(FHIRFormDef FHIRFormDef);
	
	@Transactional(readOnly = true)
	List<FHIRForm> getAllFHIRFormsByDef(FHIRFormDef FHIRFormDef);
	
	@Transactional(readOnly = true)
	List<FHIRForm> getAllFHIRFormsByPatient(Patient patient);
}
