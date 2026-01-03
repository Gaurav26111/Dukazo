package org.dukazo.repository;

import org.dukazo.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
    public Admin findByEmail(String email);
}
