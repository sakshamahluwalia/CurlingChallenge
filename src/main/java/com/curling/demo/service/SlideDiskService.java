package com.curling.demo.service;
import com.curling.demo.entity.Point;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class SlideDiskService {

    public static int MINIMUM_RADIUS_VALUE = 1;
    public static int MAXIMUM_RADIUS_VALUE = 1000;

    Integer radius = 0;
    List<Point> disks = new ArrayList<>();

    /**
     * Starts simulation of disks falling and calculates the new mid-points for each disk.
     */
    public List<Point> startSim() {

        if (!canSimulationStart()) {
            return List.of();
        }
        // process initial disk
        Point point = disks.get(0);
        point.setLocation(point.getX(), this.radius);

        // process other disks
        for (int i = 1; i < disks.size(); i++) {
            Point tmpPoint = disks.get(i);
            double currDiskXOrdinate = tmpPoint.getX();
            List<Point> collisions = disksICollideWith(i);
            if (collisions.isEmpty()) {
                // if there are no collisions then the disk drops all the way down
                tmpPoint.setLocation(currDiskXOrdinate, this.radius);
            } else {
                // if there are collisions then record the point with the highest Y Ordinate
                List<Double> possibleOrdinates = collisions.stream()
                                .map(p -> calculateYOrdinate(p, currDiskXOrdinate)).toList();
                tmpPoint.setLocation(currDiskXOrdinate, Collections.max(possibleOrdinates));
            }
        }

        return disks;
    }

    /**
     * Checks if the disks before collide with the current disk. If they do collect them in `collisions` list.
     * @param index index of current disk
     * @return List<Point> of disks that I have collided with.
     */
    private List<Point> disksICollideWith(int index) {

        List<Point> collisions = new ArrayList<>();
        Point currentDisk = disks.get(index);

        for (int i = 0; i < index; i++) {
            Point previousDisk = disks.get(i);
            if (weCollide(previousDisk, currentDisk)) {
                collisions.add(previousDisk);
            }
        }
        return collisions;
    }

    /**
     * Helper to check if 2 disks collide
     * @param p1 represents disk 1
     * @param p2 represents disk 2
     * @return true/false
     */
    private boolean weCollide(Point p1, Point p2) {
        return modifiedEq(p1.getX(), p2.getX()) >= 0;
    }

    /**
     * Given x co-ordinates of 2 points with radius `radius` this equation yields the result of
     * the squared difference between the y-coOrdinates of 2 points.
     * @param p1X x-ordinate of disk 1
     * @param p2X x-ordinate of disk 2
     * @return double
     */
    private double modifiedEq(Double p1X, Double p2X) {
        return (4 * Math.pow(radius, 2)) - Math.pow(p2X - p1X, 2);
    }

    /**
     * The equation yields the y-ordinate of disk 2 given the x and y co-ordinates of point 1 and also the
     * x-ordinate of point 2.
     * @param point x and y co-ordinates of point 1
     * @param currXOrdinate x-ordinate of point 2
     * @return double
     */
    private double calculateYOrdinate(Point point, Double currXOrdinate) {
        return Math.sqrt(modifiedEq(point.getX(), currXOrdinate)) + point.getY();
    }

    /**
     * returns true/false based on if disks and radius has been initialized properly.
     * @return boolean
     */
    public boolean canSimulationStart() {
        return radius > 0 && !disks.isEmpty();
    }

    public int getRadius() {
        log.info("[{}]: getRadius called", this.getClass().getSimpleName());
        return this.radius;
    }

    public void setRadius(Integer input) {
        log.info("[{}]: setRadius called with params: {}", this.getClass().getSimpleName(), input);
        this.radius = input;
    }

    public int getNumDisks() {
        return disks.size();
    }
    public void setDisks(List<Point> list) {
        log.info("[{}]: setDisks called with params: {}", this.getClass().getSimpleName(), list);
        this.disks = list;
    }

}
