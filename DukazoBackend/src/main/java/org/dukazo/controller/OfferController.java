package org.dukazo.controller;

import org.dukazo.entity.Offer;
import org.dukazo.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping
    public Offer createOffer(@RequestBody Offer offer){
        return offerService.createOffer(offer);
    }
}
