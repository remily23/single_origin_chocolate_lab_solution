package com.bnta.chocolate.services;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.models.ChocolateDTO;
import com.bnta.chocolate.models.Estate;
import com.bnta.chocolate.repositories.ChocolateRepository;
import com.bnta.chocolate.repositories.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChocolateService {

    @Autowired
    ChocolateRepository chocolateRepository;

    @Autowired
    EstateService estateService;

    public void saveChocolate(ChocolateDTO chocolateDTO){
//        find the estate for this chocolate
        Estate estate = estateService.findSingleEstate(chocolateDTO.getEstateId());
//        Create a chocolate object from the all the data we now have
        Chocolate chocolate = new Chocolate(chocolateDTO.getName(), chocolateDTO.getCocoaPercentage(), estate);
        chocolateRepository.save(chocolate);
    }

    public List<Chocolate> getAllChocolates(){
        return chocolateRepository.findAll();
    }

    public List<Chocolate> getChocolatesWithCocoaPercentageGreaterThan(int cocoaPercentage){
        return chocolateRepository.findByCocoaPercentageGreaterThan(cocoaPercentage);
    }

    public Chocolate findSingleChocolate(Long id){
        return chocolateRepository.findById(id).get();
    }

}
