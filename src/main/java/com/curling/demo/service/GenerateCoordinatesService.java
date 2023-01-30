package com.curling.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.curling.demo.entity.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is responsible for generating initial points
 * required to start the simulation.
 *
 * This class also contains the boundaries of our cartesian plane.
 */
@Slf4j
@Component
public class GenerateCoordinatesService {

    public static int MINIMUM_X_ORDINATE = 1;
    public static int MAXIMUM_X_ORDINATE = 1000;
    public static Double MAXIMUM_Y_ORDINATE = Math.pow(10, 100);


    // TODO: add unit testing for this function
    /**
     * This function is responsible for creating <total_points> random integers.
     * @param total_points The number of integers to generate.
     * @return A list of the generated random integers.
     */
    public List<Point> generateInitialXValues(int total_points) {
        log.info("[{}]: generateNumbers called with params: {}", this.getClass().getSimpleName(), total_points);
        int xOrdinate;
        List<Point> results = new ArrayList<>();
        for (int i = 0; i < total_points; i++) {
            xOrdinate = ThreadLocalRandom.current().nextInt(
                    GenerateCoordinatesService.MINIMUM_X_ORDINATE,
                    GenerateCoordinatesService.MAXIMUM_X_ORDINATE + 1);
            Point tempPoint = new Point();
            tempPoint.setLocation(xOrdinate, MAXIMUM_Y_ORDINATE);
            results.add(tempPoint);
        }
        return results;
    }
}
