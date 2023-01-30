package com.curling.demo.controller;
import java.util.List;

import com.curling.demo.Utils.Utils;
import com.curling.demo.links.AppLinks;
import com.curling.demo.service.GenerateCoordinatesService;
import com.curling.demo.service.SlideDiskService;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curling.demo.entity.Point;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/")
public class ApplicationController {

    GenerateCoordinatesService generateCoordinatesService = new GenerateCoordinatesService();
    SlideDiskService slideDiskService = new SlideDiskService();
    Utils utils = new Utils();

    /**
     * Function to drive the simulation once all inputs have been initialized properly.
     * @return ResponseEntity false if something went wrong; List of x and y ordinates of disks.
     */
    @GetMapping(path = AppLinks.START_SIMULATION)
    public ResponseEntity<?> startSimulation() {
        log.info("{}: startSimulation called", this.getClass().getSimpleName());

        if (slideDiskService.canSimulationStart()) {

            log.info("{}: simulation started with {} disks and {} radius",
                    this.getClass().getSimpleName(),
                    slideDiskService.getNumDisks(),
                    slideDiskService.getRadius()
            );
            List<Point> results = slideDiskService.startSim();

            return ResponseEntity.ok(results);
        } else {

            log.info("{}: simulation start failed!", this.getClass().getSimpleName());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /**
     * Function to generate InitialXValues.
     * @param body JSONObject should contain `numbersToGenerate` field.
     * @return List<Integer> returns true indicating disks has been set in SlideDiskService.
     */
    @PostMapping(path = AppLinks.GENERATE_X_ORDINATES)
    public ResponseEntity<?> generateNumbers(@RequestBody JSONObject body) {
        log.info("{}: generateNumbers called with params: {}", this.getClass().getSimpleName(), body);

        if (!utils.validateRequest(body, "numbersToGenerate")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        int pointsToGenerate = Integer.parseInt((String) body.getOrDefault("numbersToGenerate", 0));
        List<Point> points = generateCoordinatesService.generateInitialXValues(pointsToGenerate);
        slideDiskService.setDisks(points);
        System.out.print("hihihih");
        return ResponseEntity.ok(true);
    }

    /**
     * Function to set the radius for each circle.
     * @param body JSONObject should contain `radius` field.
     * @return boolean return true indicating radius has been set in SlideDiskService.
     */
    @PostMapping(path = AppLinks.SET_RADIUS)
    public ResponseEntity<?> setRadius(@RequestBody JSONObject body) {
        log.info("{}: setRadius called with params: {}", this.getClass().getSimpleName(), body);
        if (!utils.validateRequest(body, "radius")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        int radius = Integer.parseInt((String) body.getOrDefault("radius", 0));
        slideDiskService.setRadius(radius);

        return ResponseEntity.ok(true);
    }
}