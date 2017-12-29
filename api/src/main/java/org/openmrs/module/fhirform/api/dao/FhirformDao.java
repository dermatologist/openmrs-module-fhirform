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
import org.openmrs.module.fhirform.Fhirform;
import org.openmrs.module.fhirform.FhirformDef;
import org.openmrs.module.fhirform.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository("fhirform.FhirformDao")
public class FhirformDao {

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
            return getSession().createCriteria(FhirformDef.class).list();
        return getSession().createCriteria(FhirformDef.class).add(Restrictions.eq("formtype", formtype)).list();

    }

    public List getAllFhirforms(String status) {
        if (status.isEmpty())
            return getSession().createCriteria(Fhirform.class).list();
        return getSession().createCriteria(Fhirform.class).add(Restrictions.eq("status", status)).list();
    }

    public List getAllFhirformsByDef(FhirformDef FhirformDef) {
        return getSession().createCriteria(Fhirform.class).add(Restrictions.eq("FhirformDef", FhirformDef)).list();
    }

    public List getAllFhirformsByPatient(Patient patient) {
        return getSession().createCriteria(Fhirform.class).add(Restrictions.eq("patient", patient)).list();
    }

    public Fhirform getFhirformById(int id) {
        return (Fhirform) getSession().createCriteria(Fhirform.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    public FhirformDef getFhirformDefById(int id) {
        return (FhirformDef) getSession().createCriteria(FhirformDef.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    public Fhirform saveFhirform(Fhirform Fhirform) {
        getSession().saveOrUpdate(Fhirform);
        return Fhirform;
    }

    public void purgeFhirform(Fhirform Fhirform) {
        getSession().delete(Fhirform);
    }

    public FhirformDef saveFhirformDef(FhirformDef FhirformDef) {
        getSession().saveOrUpdate(FhirformDef);
        return FhirformDef;
    }

    public void purgeFhirformDef(FhirformDef FhirformDef) {
        getSession().delete(FhirformDef);
    }

}
