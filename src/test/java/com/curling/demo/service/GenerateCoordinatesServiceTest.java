package com.curling.demo.service;

import com.curling.demo.entity.Point;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

class GenerateCoordinatesServiceTest {

    GenerateCoordinatesService generateCoordinatesService = new GenerateCoordinatesService();

    @Test
    void generate_10_values() {
        List<Point> points = generateCoordinatesService.generateInitialXValues(10);
        assert(!points.isEmpty());
        assert(points.size() == 10);
    }

    @Test
    void verify_values_inbound() {

        List<Point> points = generateCoordinatesService.generateInitialXValues(10);
        List<Point> filtered_points = points.stream()
                .filter(point -> point.getX() > GenerateCoordinatesService.MINIMUM_X_ORDINATE
                        && point.getX() < GenerateCoordinatesService.MAXIMUM_X_ORDINATE)
                .toList();
        assert(points.size() == filtered_points.size());
    }

}