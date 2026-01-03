package org.dukazo.service;

import org.dukazo.entity.Shopkeeper;
import org.dukazo.repository.ShopkeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ShopkeeperService {

    @Autowired
    private ShopkeeperRepository shopkeeperRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Shopkeeper createShopkeeper(Shopkeeper shopkeeper){
        shopkeeper.setPassword(passwordEncoder.encode(shopkeeper.getPassword()));
        return shopkeeperRepository.save(shopkeeper);
    }
}
