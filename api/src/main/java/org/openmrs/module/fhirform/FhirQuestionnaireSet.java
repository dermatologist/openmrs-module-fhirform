package org.openmrs.module.fhirform;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by beapen on 04/12/2017.
 */
@Component
public class FhirQuestionnaireSet {

    public ArrayList<FhirQuestionnaireItem> __items;

    public String __type = "object";

    public String __title = "Title";

    FhirQuestionnaireSet() {
        __items = new ArrayList<FhirQuestionnaireItem>();
    }

    public ArrayList<FhirQuestionnaireItem> get_items() {
        return __items;
    }

    public void add_item(FhirQuestionnaireItem __item) {
        this.__items.add(__item);
    }

    public String getForm() {
        Gson gson = new Gson();
        return gson.toJson(this).replace("__", "");
    }

}
