package org.dukazo.repository;

import org.dukazo.entity.Shopkeeper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopkeeperRepository extends JpaRepository<Shopkeeper, String> {
    public Shopkeeper findByEmail(String email);
}
