package org.openmrs.module.fhirform;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JsonObjectFromFhirTest {

    JsonObjectProperty jsonObjectProperty;
    JsonObjectItem jsonObjectItem;
    JsonObjectSchema jsonObjectSchema;
    JsonObjectForm jsonObjectForm;
    JsonObjectFunction jsonObjectFunction;
    JsonObjectFromFhir jsonObjectFromFhir;

    @Before
    public void setUp() {
        jsonObjectProperty = new JsonObjectProperty();
        jsonObjectItem = new JsonObjectItem();
        jsonObjectSchema = new JsonObjectSchema();
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
        System.out.print(jsonObjectProperty.getForm());

//        jsonObjectItem.set__title("Friend");
//        jsonObjectItem.add_item(jsonObjectProperty);
//
//        jsonObjectProperty = new JsonObjectProperty();
//        jsonObjectProperty.set__linkId("gender");
//        jsonObjectProperty.set__type("string");
//        jsonObjectProperty.set__title("Gender");
//        jsonObjectProperty.add_item("male");
//        jsonObjectProperty.add_item("female");
//        jsonObjectItem.add_item(jsonObjectProperty);
//
//        jsonObjectProperty = new JsonObjectProperty();
//        jsonObjectProperty.set__linkId("age");
//        jsonObjectProperty.set__type("integer");
//        jsonObjectProperty.set__title("Age");
//        jsonObjectItem.add_item(jsonObjectProperty);
//
//        System.out.print(jsonObjectItem.getForm());

    }
}