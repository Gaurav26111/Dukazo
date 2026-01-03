package org.dukazo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "admins")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 10)
    private String id;

    private String name;
    private String email;
    private String password;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}

