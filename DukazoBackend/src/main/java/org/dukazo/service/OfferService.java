package org.dukazo.service;

import org.dukazo.entity.Offer;
import org.dukazo.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }
}
