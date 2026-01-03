package org.dukazo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.dukazo.entity.LoginRequest;
import org.dukazo.entity.Shopkeeper;
import org.dukazo.entity.User;
import org.dukazo.repository.ShopkeeperRepository;
import org.dukazo.repository.UserRepository;
import org.dukazo.service.ShopkeeperService;
import org.dukazo.service.UserDetailsServiceImp;
import org.dukazo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;
    @Autowired
    private ShopkeeperService shopkeeperService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShopkeeperRepository shopkeeperRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SecurityContextRepository securityContextRepository;

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/createShopkeeper")
    public Shopkeeper createShopkeeper(@RequestBody Shopkeeper shopkeeper){
        return shopkeeperService.createShopkeeper(shopkeeper);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        try {
            // 1. Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            // 2. Set the Security Context
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            // 3. Save the Context to the Session (This triggers the Redis Save)
            // Force creation of the session and set an attribute
            request.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT", context);
            securityContextRepository.saveContext(context, request, response);
            System.out.println("Session Class: " + request.getSession().getClass().getName());

            return ResponseEntity.ok("Login Successful! Session created in Redis.");

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Email or Password");
        }
    }

    @PostMapping("/loginShopkeeper")
    public Shopkeeper loginShopkeeper(String email, String password){
        Shopkeeper shopkeeper = shopkeeperRepository.findByEmail(email);
        if(shopkeeper!= null && passwordEncoder.matches(shopkeeper.getPassword(),password)){
            return shopkeeper;
        }
        return null;
    }
}
