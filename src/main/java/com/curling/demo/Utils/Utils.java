package com.curling.demo.Utils;

import com.curling.demo.service.GenerateCoordinatesService;
import com.curling.demo.service.SlideDiskService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import com.curling.demo.entity.Point;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Utils {

    /**
     * Very basic util function to validate incoming requests.
     * @param body incoming params from client
     * @param fieldName input field to validate for
     * @return boolean
     */
    public boolean validateRequest(JSONObject body, String fieldName) {

        Optional<Object> bodyOptional = Optional.of(body);
        Optional<Object> fieldOptional = Optional.ofNullable(body.get(fieldName));

        if (bodyOptional.isPresent() && fieldOptional.isPresent()) {

            boolean result;
            switch(fieldName) {
                case "numbersToGenerate":
                    result = validateInputForDisks(Integer.parseInt((String) body.getOrDefault("numbersToGenerate", 0)));
                    break;
                case "radius":
                    result = validateInputForRadius(Integer.parseInt((String) body.getOrDefault("radius", 0)));
                    break;
                default:
                    // code block
                    result = false;
            }
            return result;
        }

        log.info("{}: validateRequest failed", this.getClass().getSimpleName());
        return false;
    }

    /**
     * Helper used in validateRequest
     * Check if the number of disks requested are within our constraints.
     */
    private boolean validateInputForRadius(Integer input) {
        if (SlideDiskService.MINIMUM_RADIUS_VALUE <= input &&
                input <= SlideDiskService.MAXIMUM_RADIUS_VALUE) {
            return true;
        }
        log.info("{}: validateRequest failed for validateInputForRadius", this.getClass().getSimpleName());
        return false;
    }

    /**
     * Helper used in validateRequest
     * Check if the number of disks requested are within our constraints.
     */
    private boolean validateInputForDisks(Integer input) {
        if (GenerateCoordinatesService.MINIMUM_X_ORDINATE <= input &&
                input <= GenerateCoordinatesService.MAXIMUM_X_ORDINATE) {
            return true;
        }
        log.info("{}: validateRequest failed for validateInputForDisks", this.getClass().getSimpleName());
        return false;
    }

}
