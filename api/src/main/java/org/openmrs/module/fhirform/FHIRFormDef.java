package org.openmrs.module.fhirform;

import org.openmrs.BaseOpenmrsData;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by beapen on 01/11/2017.
 */
@Entity(name = "FHIRFormDef")
@Table(name = "FHIRFormDef")
public class FHIRFormDef extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	private String created_by;
	
	private String deleted_by;
	
	private Date created_on;
	
	private Date deleted_on;
	
	private String formtype = "FHIRFORM";
	
	//private String questionnaire_id;
	
	private String version;
	
	private String questionnaireUrl;
	
	private String status = "ACTIVE";
	
	private String comments;
	
	private String submissionUrl;
	
	@OneToMany(mappedBy = "FHIRFormDef")
	private Set<FHIRForm> fhirforms;
	
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
	
	public String getFormtype() {
		return formtype;
	}
	
	public void setFormtype(String formtype) {
		this.formtype = formtype;
	}
	
	/*
	    public String getQuestionnaire_id() {
	        return questionnaire_id;
	    }

	    public void setQuestionnaire_id(String questionnaire_id) {
	        this.questionnaire_id = questionnaire_id;
	    }
	*/
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
	
	public String getSubmissionUrl() {
		return submissionUrl;
	}
	
	public void setSubmissionUrl(String submissionUrl) {
		this.submissionUrl = submissionUrl;
	}
	
	public Set<FHIRForm> getFhirforms() {
		return fhirforms;
	}
	
	public void setFhirforms(Set<FHIRForm> fhirforms) {
		this.fhirforms = fhirforms;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getQuestionnaireUrl() {
		return questionnaireUrl;
	}
	
	public void setQuestionnaireUrl(String questionnaireUrl) {
		this.questionnaireUrl = questionnaireUrl;
	}
}
