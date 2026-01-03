package org.dukazo.service;

import org.dukazo.entity.Admin;
import org.dukazo.entity.Shopkeeper;
import org.dukazo.entity.User;
import org.dukazo.repository.AdminRepository;
import org.dukazo.repository.ShopkeeperRepository;
import org.dukazo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ShopkeeperRepository shopkeeperRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Check Admin table
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(admin.getEmail())
                    .password(admin.getPassword())
                    .roles("ADMIN") // Manually assign role here
                    .build();
        }

        // 2. Check Shopkeeper table
        Shopkeeper shopkeeper = shopkeeperRepository.findByEmail(email);
        if (shopkeeper != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(shopkeeper.getEmail())
                    .password(shopkeeper.getPassword())
                    .roles("SHOPKEEPER")
                    .build();
        }

        // 3. Check Normal User table
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles("USER")
                    .build();
        }

        throw new UsernameNotFoundException("No user found with email: " + email);
    }

}
