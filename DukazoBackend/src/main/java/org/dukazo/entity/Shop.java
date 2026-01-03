package org.dukazo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @Column(length = 8)
    private String id;

    private String shopName;
    private String shopType;
    private String shopKeeperName;
    private String gst;

    private String openingHour;
    private String closingHour;

    private String address;
    private String state;
    private String district;
    private String pinCode;

    private String shopImage;
    private String shopKeeperImage;

    @ManyToOne
    @JoinColumn(name = "shopkeeper_id")
    private Shopkeeper shopkeeper;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Product> products;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8);
    }
}
