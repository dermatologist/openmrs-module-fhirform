# openmrs-module-fhirform

FHIR (Fast Healthcare Interoperability Resources) Specification, is a standard for exchanging healthcare information electronically. The FHIR Questionnaire resource is intended to guide the collection of answers from end-users and provide control over presentation, semantics, and grouping to allow consistent data collection. 

openMRS is a highly customizable EMR platform that can be extended and customized by user-contributed modules. More details here: http://openmrs.org

openmrs-module-fhirform is an OpenMRS module for rendering a FHIR questionnaire. The submission can be stored as a string locally and sent to a FHIR compliant server as a QuestionnaireResponse.

Approach:
The transformation will be achieved using Rob Tweed's qewd-transform-JSON (https://github.com/robtweed/qewd-transform-json). Rendering will be achieved using jsonform (https://github.com/joshfire/jsonform)

Status
Work in progress.

Contributing
* Please read contributing.md (A GitFlow model is recommended)
* We recommend OpenMRS SDK for development ( https://wiki.openmrs.org/display/docs/OpenMRS+SDK )

Installation
Work in progress. Not yet ready for installation.