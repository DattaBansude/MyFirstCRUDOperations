package com.company.controller;

import com.company.entity.Developer;
import com.company.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    private static final Logger logger = LoggerFactory.getLogger(DeveloperController.class);

    @Autowired
    private DeveloperService developerService;

    @PostMapping("/add")
    public ResponseEntity<String> addDeveloper(@RequestBody Developer developer) {
        System.err.println(developer);
        logger.info("Adding new developer: {}", developer.getFName()); // INFO log
        developerService.saveDeveloper(developer);
        logger.debug("Developer details saved: {}", developer); // DEBUG log
        return new ResponseEntity<>("Developer Saved", HttpStatus.CREATED);

    }

    @GetMapping("/getAllDevelopers")
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        logger.info("Fetching all developers");  // INFO log
        List<Developer> dveList = developerService.getAllDevelopers();
        logger.debug("Total developers found: {}", dveList.size()); // DEBUG log
        return new ResponseEntity<>(dveList, HttpStatus.OK);
    }

    @GetMapping("/getDeveloperById/{id}")
    public ResponseEntity<Developer> getDeveloperById(@PathVariable("id") int id) {
        logger.info("Fetching developer with ID: {}", id);
        Developer developer = developerService.getDeveloperById(id);
        if (developer == null) {
            logger.warn("Developer with ID {} not found", id); // WARN log
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Developer details: {}", developer);
        return new ResponseEntity<>(developer, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDeveloperById/{id}")
    public ResponseEntity<String> deleteDeveloperById(@PathVariable("id") int id) {
        logger.info("Deleting developer with ID: {}", id);
        String msg = developerService.deleteDeveloperById(id);
        logger.debug("Delete result: {}", msg);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Developer> updateDeveloperById(@PathVariable("id") int id, @RequestBody Developer developer) {
        logger.info("Request received to update developer with ID: {}", id);
        Developer update = developerService.updateDeveloper(id, developer);
        logger.debug("Developer updated successfully: {}", update);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Developer>> filterDeveloper(@RequestParam(required = false) String city,
                                                           @RequestParam(required = false) String gender) {
        logger.info("Filter request received with params -> City: {}, Gender: {}", city, gender);
        List<Developer> sortedList = new ArrayList<>();
        if (gender != null) {
            logger.debug("Filtering developers by gender: {}", gender);
            sortedList = developerService.filterDataByGender(gender);
        } else {
            logger.debug("Filtering developers by city: {}", city);
            sortedList = developerService.filterDataByCity(city);
        }
        logger.info("Filter result size: {}", sortedList.size());
        return new ResponseEntity<>(sortedList, HttpStatus.OK);
    }

    @DeleteMapping("/deleteInGroup")
    public ResponseEntity<String> deleteDeveloperInGroup(@RequestParam (required = false) List<Integer> id){
        return new ResponseEntity("deleted", HttpStatus.OK);
    }

    @GetMapping("/byAge/{age}")
    public ResponseEntity<List<Developer>> getDeveloperbyAge(@PathVariable("age")int age){
        List<Developer> developerList=developerService.getDeveloperByAge(age);
        return new ResponseEntity<>(developerList, HttpStatus.OK);
    }
}
