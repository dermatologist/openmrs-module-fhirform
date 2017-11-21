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

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getDeleted_by() {
        return deleted_by;
    }

    public void setDeleted_by(String deleted_by) {
        this.deleted_by = deleted_by;
    }

    public String getLast_edited_by() {
        return last_edited_by;
    }

    public void setLast_edited_by(String last_edited_by) {
        this.last_edited_by = last_edited_by;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public Date getDeleted_on() {
        return deleted_on;
    }

    public void setDeleted_on(Date deleted_on) {
        this.deleted_on = deleted_on;
    }

    public Date getLast_edited_on() {
        return last_edited_on;
    }

    public void setLast_edited_on(Date last_edited_on) {
        this.last_edited_on = last_edited_on;
    }

    public String getSubmitted_by() {
        return submitted_by;
    }

    public void setSubmitted_by(String submitted_by) {
        this.submitted_by = submitted_by;
    }

    public Date getSubmitted_on() {
        return submitted_on;
    }

    public void setSubmitted_on(Date submitted_on) {
        this.submitted_on = submitted_on;
    }

    public String getSubmissionUrl() {
        return submissionUrl;
    }

    public void setSubmissionUrl(String submissionUrl) {
        this.submissionUrl = submissionUrl;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getQuestionnaire_id() {
        return questionnaire_id;
    }

    public void setQuestionnaire_id(String questionnaire_id) {
        this.questionnaire_id = questionnaire_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public FHIRFormDef getFhirFormDef() {
        return fhirFormDef;
    }

    public void setFhirFormDef(FHIRFormDef fhirFormDef) {
        this.fhirFormDef = fhirFormDef;
    }
}
