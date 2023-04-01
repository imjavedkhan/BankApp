package com.primus.BankApp.controller;

import com.primus.BankApp.model.BankUser;
import com.primus.BankApp.repository.BankUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody BankUser bankUser) {
        BankUser savedUser = null;
        ResponseEntity response = null;
        try {
            String hashPwd = passwordEncoder.encode(bankUser.getPwd());
            bankUser.setPwd(hashPwd);
            bankUser.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
            savedUser = bankUserRepository.save(bankUser);
            if (savedUser.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
        }
        return response;
    }

    @GetMapping("/user")
    public BankUser getUserDetailsAfterLogin(Authentication authentication) {
        List<BankUser> customers = bankUserRepository.findByEmail(authentication.getName());
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }

    }
}
