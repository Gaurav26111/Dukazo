package org.dukazo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @Column(length = 10)
    private String id;

    @Enumerated(EnumType.STRING)
    private OfferType offerType;

    // Common fields
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;

    // Condition fields
    private Double minPurchaseAmount; // e.g. 10000
    private Integer buyQuantity;       // X in Buy X Get Y
    private Integer getQuantity;       // Y in Buy X Get Y

    // Benefit fields
    private Double discountPercentage;
    private Double flatDiscountAmount;
    private Double cashbackAmount;

    // Free product
    private String freeProductName;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("offers")
    private Product product;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8);
    }
}
