package com.primus.BankApp.controller;

import com.primus.BankApp.model.BankUser;
import com.primus.BankApp.repository.BankUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    BankUserRepository bankUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity registerBankUser(@RequestBody BankUser bankUser){
        BankUser savedUser;
        ResponseEntity response = null;

        try{
            String encrypt_pwd = passwordEncoder.encode(bankUser.getPwd());
            bankUser.setPwd(encrypt_pwd);
            savedUser = bankUserRepository.save(bankUser);
            if(savedUser.getId()>0){
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("User created");
            }
        }catch (Exception ex){
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception occured" + ex.getMessage());
        }

        return response;
    }
}
