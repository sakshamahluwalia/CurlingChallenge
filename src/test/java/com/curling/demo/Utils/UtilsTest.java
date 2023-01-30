package com.curling.demo.Utils;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    Utils utils = new Utils();

    @Test
    void no_field_should_return_false() {
        JSONObject jsonObject = new JSONObject();
        boolean result = utils.validateRequest(jsonObject, "radius");

        assert(!result);
    }

    @Test
    void invalid_field_should_return_false() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("radiuss", "1");
        boolean result = utils.validateRequest(jsonObject, "radius");

        assert(!result);
    }

    @Test
    void inrange_radius_input_should_return_true() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("radius", "1");
        boolean result = utils.validateRequest(jsonObject, "radius");

        assert(result);
    }

    @Test
    void out_of_range_radius_input_should_return_false() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("radius", "0");
        boolean result = utils.validateRequest(jsonObject, "radius");

        assert(!result);
    }

    @Test
    void inrange_disk_input_should_return_true() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("numbersToGenerate", "10");
        boolean result = utils.validateRequest(jsonObject, "numbersToGenerate");

        assert(result);
    }

    @Test
    void out_of_range_disk_input_should_return_false() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("numbersToGenerate", "0");
        boolean result = utils.validateRequest(jsonObject, "numbersToGenerate");

        assert(!result);
    }
}