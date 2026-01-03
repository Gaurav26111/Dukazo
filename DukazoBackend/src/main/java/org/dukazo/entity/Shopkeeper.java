package org.dukazo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "shopkeeper")
public class Shopkeeper implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 10)
    private String id;

    private String mobileNumber;
    private String email;
    private String password;

    private String adharCardNumber;
    private String panCardNumber;

    private String panCardFile;
    private String adharCardFile;

    @OneToMany(mappedBy = "shopkeeper")
    @JsonIgnore
    private List<Shop> shops;




    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
