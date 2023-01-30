package com.curling.demo.service;

import com.curling.demo.entity.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SlideDiskServiceTest {

    SlideDiskService slideDiskService = new SlideDiskService();

    @Test
    void startSim_testInput() {

        Point point1 = new Point();
        Point point2 = new Point();
        Point point3 = new Point();
        Point point4 = new Point();
        Point point5 = new Point();
        Point point6 = new Point();
        point1.setLocation(5, 1000);
        point2.setLocation(5, 1000);
        point3.setLocation(6, 1000);
        point4.setLocation(8, 1000);
        point5.setLocation(3, 1000);
        point6.setLocation(12, 1000);

        List<Point> disks = new ArrayList<>();
        disks.add(point1);
        disks.add(point2);
        disks.add(point3);
        disks.add(point4);
        disks.add(point5);
        disks.add(point6);

        slideDiskService.setDisks(disks);
        slideDiskService.setRadius(2);

        assertTrue(slideDiskService.canSimulationStart());

        List<Point> results = slideDiskService.startSim();
        for (Point p: results) {
            System.out.println(p.getY());
        }

        assert(results.get(0).getY() == 2);
        assert(results.get(1).getY() == 6);
        assert(results.get(2).getY() == 9.872983346207416);
        assert(results.get(3).getY() == 13.33708496134517);
        assert(results.get(4).getY() == 12.518734657272006);
        assert(results.get(5).getY() == results.get(3).getY());

    }
    @Test
    void startSim_collision() {

        Point point1 = new Point();
        Point point2 = new Point();
        point1.setLocation(5, 1000);
        point2.setLocation(5, 1000);

        List<Point> disks = new ArrayList<>();
        disks.add(point1);
        disks.add(point2);

        slideDiskService.setDisks(disks);
        slideDiskService.setRadius(2);

        assertTrue(slideDiskService.canSimulationStart());

        List<Point> results = slideDiskService.startSim();

        assert(results.get(0).getY() == 2);
        assert(results.get(1).getY() == 6);

    }
    @Test
    void startSim_noCollision() {

        Point point1 = new Point();
        Point point2 = new Point();
        point1.setLocation(5, 1000);
        point2.setLocation(12, 1000);

        List<Point> disks = new ArrayList<>();
        disks.add(point1);
        disks.add(point2);

        slideDiskService.setDisks(disks);
        slideDiskService.setRadius(2);

        assertTrue(slideDiskService.canSimulationStart());

        List<Point> results = slideDiskService.startSim();

        assert(results.get(0).getY() == 2);
        assert(results.get(1).getY() == 2);

    }
    @Test
    void canSimulationStart_emptyDisks() {
        slideDiskService.setDisks(List.of());
        slideDiskService.setRadius(1);
        boolean result = slideDiskService.canSimulationStart();
        assert(!result);
    }
    @Test
    void canSimulationStart_radius_outOfBounds() {
        Point point = new Point();
        slideDiskService.setDisks(List.of(point));
        slideDiskService.setRadius(0);
        boolean result = slideDiskService.canSimulationStart();
        assert(!result);
    }
    @Test
    void canSimulationStart_greenPath() {
        Point point = new Point();
        slideDiskService.setDisks(List.of(point));
        slideDiskService.setRadius(10);
        boolean result = slideDiskService.canSimulationStart();
        assert(result);
    }
}