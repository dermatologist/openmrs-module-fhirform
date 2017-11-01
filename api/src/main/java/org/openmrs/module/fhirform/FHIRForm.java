package org.openmrs.module.fhirform;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by beapen on 01/11/2017.
 */
@Entity(name = "fhirform.FHIRForm")
@Table(name = "fhirform_entries")
public class FHIRForm extends BaseOpenmrsData {

    @Id
    @GeneratedValue
    @Column(name = "fhirform_id")
    private Integer id;

    private String created_by;

    private String deleted_by;

    private String last_edited_by;

    private Date created_on;

    private Date deleted_on;

    private Date last_edited_on;


    private String submitted_by;

    private Date submitted_on;

    private String submissionUrl;


    private Patient patient;

    private String response;

    private String questionnaire_id;

    private String status = "ACTIVE";

    private String comments;

    @ManyToOne
    @JoinColumn(name = "fhirform_def_id", nullable = false)
    private FHIRFormDef fhirFormDef;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUuid() {
        return super.getUuid();
    }

    @Override
    public void setUuid(String uuid) {
        super.setUuid(uuid);
    }
}
