package org.dukazo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(length = 10)
    private String id;

    private String name;
    private double price;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Offer> offers;

    public void addOffer(Offer offer) {
        offers.add(offer);
        offer.setProduct(this); // This line sets the foreign key!
    }

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
