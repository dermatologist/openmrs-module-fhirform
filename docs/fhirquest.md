FHIR (Fast Healthcare Interoperability Resources) Specification, is a standard for exchanging healthcare information electronically. The FHIR Questionnaire resource is intended to guide the collection of answers from end-users and provide control over presentation, semantics, and grouping to allow consistent data collection. However, there are no standard rendering engines for the FHIR Questionnaire.

FHIR QuesT is a template to transform any json Questionnaire resource to a form that can be rendered.

The transformation will be achieved using Rob Tweed's qewd-transform-JSON (https://github.com/robtweed/qewd-transform-json). Rendering will be achieved using jsonform (https://github.com/joshfire/jsonform)


Usage

```
var fhirquest;
var questionnaire;
$.getJSON('relative-url/fhirquest', function(data) {
    fhirquest = data; // get the fhirquest json
});
$.getJSON('http://fhir-server.ca/example', function(data) {
    questionnaire = data; // get the questionnaire data
});
var transform = require('qewd-transform-json').transform;
var render = transform(fhirquet, questionnaire); // The json to be rendered.

   
      $('form').jsonForm({
        schema: render,
        onSubmit: function (errors, values) {
          if (errors) {
           
          }
          else {
            // format values as QuestionnaireResponse
          }
        }
      });
```

   