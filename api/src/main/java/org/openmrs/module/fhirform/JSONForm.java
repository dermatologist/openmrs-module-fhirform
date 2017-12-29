package org.openmrs.module.fhirform;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by beapen on 04/12/2017.
 */
@Component
public class JSONForm {
	
	public ArrayList<JSONFormSet> __forms = new ArrayList<JSONFormSet>();
	
	public void add_item(JSONFormSet __item) {
		this.__forms.add(__item);
	}
	
	public String getForm() {
		Gson gson = new Gson();
		return gson.toJson(this).replace("__", "");
	}
	
}
