package org.dukazo.service;

import org.dukazo.entity.Admin;
import org.dukazo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {
        return  adminRepository.save(admin);
    }
}
