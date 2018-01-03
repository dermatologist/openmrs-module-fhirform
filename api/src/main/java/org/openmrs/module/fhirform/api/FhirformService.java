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
import org.openmrs.module.fhirform.Fhirform;
import org.openmrs.module.fhirform.FhirformConfig;
import org.openmrs.module.fhirform.FhirformDef;
import org.openmrs.module.fhirform.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The main service of this module, which is exposed for other modules. See
 * moduleApplicationContext.xml on how it is wired up.
 */
public interface FhirformService extends OpenmrsService {

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
	@Authorized(FhirformConfig.MODULE_PRIVILEGE)
	@Transactional
	Item saveItem(Item item) throws APIException;

    @Transactional(readOnly = true)
	List<FhirformDef> getAllDef(String formtype);

    @Transactional(readOnly = true)
	List<Fhirform> getAllFhirforms(String status);

    @Transactional(readOnly = true)
	Fhirform getFhirformById(int id);

    @Transactional
	Fhirform saveFhirform(Fhirform Fhirform);

    @Transactional
	void purgeFhirform(Fhirform Fhirform);

    @Transactional(readOnly = true)
	FhirformDef getFhirformDefById(int id);

    @Transactional
	FhirformDef saveFhirformDef(FhirformDef FhirformDef);

    @Transactional
	void purgeFhirformDef(FhirformDef FhirformDef);

    @Transactional(readOnly = true)
	List<Fhirform> getAllFhirformsByDef(FhirformDef FhirformDef);

    @Transactional(readOnly = true)
	List<Fhirform> getAllFhirformsByPatient(Patient patient);

    String getJsonForm(FhirformDef FhirformDef);
}
