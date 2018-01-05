package org.openmrs.module.fhirform;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JsonObjectFromFhirTest {

    JsonObjectProperty jsonObjectProperty;
    JsonObjectSet jsonObjectSet;
    JsonObjectForm jsonObjectForm;
    JsonObjectFunction jsonObjectFunction;
    JsonObjectFromFhir jsonObjectFromFhir;

    @Before
    public void setUp() {
        jsonObjectProperty = new JsonObjectProperty();
        jsonObjectSet = new JsonObjectSet();
        jsonObjectForm = new JsonObjectForm();
        jsonObjectFunction = new JsonObjectFunction();
        jsonObjectFromFhir = new JsonObjectFromFhir();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void get__schema() {
    }

    @Test
    public void set__schema() {
    }

    @Test
    public void get__form() {
    }

    @Test
    public void set__form() {
    }

    @Test
    public void get__onSubmitValid() {
    }

    @Test
    public void set__onSubmitValid() {
    }

    @Test
    public void getForm() {
        jsonObjectProperty.set__linkId("nick");
        jsonObjectProperty.set__type("string");
        jsonObjectProperty.set__title("Nickname");
        jsonObjectProperty.set__required(true);
        //System.out.print(jsonObjectProperty.getForm());

        jsonObjectSet.set__title("Friend");
        jsonObjectSet.add_item(jsonObjectProperty);

        jsonObjectProperty = new JsonObjectProperty();
        jsonObjectProperty.set__linkId("gender");
        jsonObjectProperty.set__type("string");
        jsonObjectProperty.set__title("Gender");
        jsonObjectProperty.add_item("male");
        jsonObjectProperty.add_item("female");
        jsonObjectSet.add_item(jsonObjectProperty);

        jsonObjectProperty = new JsonObjectProperty();
        jsonObjectProperty.set__linkId("age");
        jsonObjectProperty.set__type("integer");
        jsonObjectProperty.set__title("Age");
        jsonObjectSet.add_item(jsonObjectProperty);


        jsonObjectFromFhir.add_schema("Section_1", jsonObjectSet);
        jsonObjectFromFhir.add_schema("Section_2", jsonObjectSet);


        String form = jsonObjectFromFhir.getForm();

        System.out.print(form);

//        String toReplace = form.substring(form.indexOf("\"properties\":["), form.indexOf("}]"));
//
//        String result = "{" + form.substring(form.indexOf("\"properties\":["), form.indexOf("}]")) + "}]}";
//
//        String toSubstitute = "{\n" +
//                "  \"nick\" : {\n" +
//                "    \"title\" : \"Nickname\",\n" +
//                "    \"type\" : \"string\",\n" +
//                "    \"required\" : true\n" +
//                "  },\n" +
//                "  \"gender\" : {\n" +
//                "    \"title\" : \"Gender\",\n" +
//                "    \"type\" : \"string\",\n" +
//                "    \"required\" : false,\n" +
//                "    \"enum\" : [ \"male\", \"female\" ]\n" +
//                "  },\n" +
//                "  \"age\" : {\n" +
//                "    \"title\" : \"Age\",\n" +
//                "    \"type\" : \"integer\",\n" +
//                "    \"required\" : false\n" +
//                "  }\n" +
//                "}\n";
//
//        String finalSubstitute = "  \"properties\":" + toSubstitute;
//        //System.out.println(toReplace);
//        //System.out.println(finalSubstitute);
//
//        String bellraj = form.replace(toReplace + "}]", finalSubstitute).replace(",\"form\":{},\"onSubmitValid\":{}", "");
//        System.out.println(bellraj);

    }
}