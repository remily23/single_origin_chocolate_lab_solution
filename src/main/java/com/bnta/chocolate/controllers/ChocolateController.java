package com.bnta.chocolate.controllers;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.models.ChocolateDTO;
import com.bnta.chocolate.repositories.ChocolateRepository;
import com.bnta.chocolate.services.ChocolateService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("chocolates")
public class ChocolateController {

    @Autowired
    ChocolateService chocolateService;

//    Handles following:
//    * INDEX: GET /chocolates
//    * GET /chocolates?cocoaPercentage=69
    @GetMapping
    public ResponseEntity<List<Chocolate>> getAllChocolatesAndFilters(@RequestParam(required = false, name = "cocoaPercentage") Integer cocoaPercentage){
//        GET /chocolates?cocoaPercentage=69
        if(cocoaPercentage != null){
            return new ResponseEntity<>(chocolateService.getChocolatesWithCocoaPercentageGreaterThan(cocoaPercentage), HttpStatus.OK);
        }
//        GET /chocolates
        return new ResponseEntity<>(chocolateService.getAllChocolates(), HttpStatus.OK);
    }

//    MVP
//    @GetMapping
//    public ResponseEntity<List<Chocolate>> getAllChocolates(){
//        return new ResponseEntity<>(chocolateService.getAllChocolates(), HttpStatus.OK);
//    }

//  SHOW: GET chocolates/id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Chocolate>> getChocolate(@PathVariable Long id){
        return new ResponseEntity(chocolateService.findSingleChocolate(id), HttpStatus.OK);
    }

//    CREATE
    @PostMapping
    public ResponseEntity<List<Chocolate>> postChocolate(@RequestBody ChocolateDTO chocolateDTO){
        chocolateService.saveChocolate(chocolateDTO);
        return new ResponseEntity<>(chocolateService.getAllChocolates(), HttpStatus.CREATED);
    }


}
