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

import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.fhirform.FHIRForm;
import org.openmrs.module.fhirform.FHIRFormDef;
import org.openmrs.module.fhirform.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository("fhirform.FHIRFormDao")
public class FHIRFormDao {

    @Autowired
    DbSessionFactory sessionFactory;

    private DbSession getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Item getItemByUuid(String uuid) {
        return (Item) getSession().createCriteria(Item.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
    }

    public Item saveItem(Item item) {
        getSession().saveOrUpdate(item);
        return item;
    }

    // REF: http://levelup.lishman.com/spring/hibernate-orm/quick-start.php

    public List getAllDef(String formtype) {
        if (formtype.isEmpty())
            return getSession()
                    .createCriteria(FHIRFormDef.class)
                    .list();
        return getSession()
                .createCriteria(FHIRFormDef.class)
                .add(Restrictions.eq("formtype", formtype))
                .list();

    }


    public List getAllFHIRForms(String status) {
        if (status.isEmpty())
            return getSession()
                    .createCriteria(FHIRForm.class)
                    .list();
        return getSession()
                .createCriteria(FHIRForm.class)
                .add(Restrictions.eq("status", status))
                .list();
    }


    public List getAllFHIRFormsByDef(FHIRFormDef FHIRFormDef) {
        return getSession()
                .createCriteria(FHIRForm.class)
                .add(Restrictions.eq("FHIRFormDef", FHIRFormDef))
                .list();
    }

    public List getAllFHIRFormsByPatient(Patient patient) {
        return getSession()
                .createCriteria(FHIRForm.class)
                .add(Restrictions.eq("patient", patient))
                .list();
    }


    public FHIRForm getFHIRFormById(int id) {
        return (FHIRForm) getSession()
                .createCriteria(FHIRForm.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }


    public FHIRFormDef getFHIRFormDefById(int id) {
        return (FHIRFormDef) getSession()
                .createCriteria(FHIRFormDef.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }


    public FHIRForm saveFHIRForm(FHIRForm FHIRForm) {
        getSession().saveOrUpdate(FHIRForm);
        return FHIRForm;
    }


    public void purgeFHIRForm(FHIRForm FHIRForm) {
        getSession().delete(FHIRForm);
    }


    public FHIRFormDef saveFHIRFormDef(FHIRFormDef FHIRFormDef) {
        getSession().saveOrUpdate(FHIRFormDef);
        return FHIRFormDef;
    }


    public void purgeFHIRFormDef(FHIRFormDef FHIRFormDef) {
        getSession().delete(FHIRFormDef);
    }


}
