package org.openmrs.module.fhirform;

import org.springframework.stereotype.Component;

/**
 * Created by beapen on 04/12/2017.
 */
@Component
public class JSONFormItem {

    public String __type = "string";

    public String __title = "Title";

    public String[] __enum;

    public boolean __required = false;

    public String __default = "";

    public String get_type() {
        return __type;
    }

    public void set_type(String __type) {
        this.__type = __type;
    }

    public String get_title() {
        return __title;
    }

    public void set_title(String __title) {
        this.__title = __title;
    }

    public String[] get_enum() {
        return __enum;
    }

    public void set_enum(String[] __enum) {
        this.__enum = __enum;
    }

    public boolean is_required() {
        return __required;
    }

    public void set_required(boolean __required) {
        this.__required = __required;
    }

    public String get_default() {
        return __default;
    }

    public void set_default(String __default) {
        this.__default = __default;
    }
}
