package org.dukazo.service;

import org.dukazo.entity.Shop;
import org.dukazo.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    public void createShop(Shop shop){
        shopRepository.save(shop);
    }
}
