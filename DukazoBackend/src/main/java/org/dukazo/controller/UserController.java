package org.dukazo.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.dukazo.entity.Product;
import org.dukazo.entity.User;
import org.dukazo.service.ProductService;
import org.dukazo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Object> getAllProduct(){
        List<Product> product = productService.getAllProduct();
        if(product!=null){
            return Collections.singletonList(product);
        }
        return Collections.singletonList("There is not any product");
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // This invalidates the session and automatically deletes the key from Redis Cloud
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully. Redis key deleted.");
    }
}
