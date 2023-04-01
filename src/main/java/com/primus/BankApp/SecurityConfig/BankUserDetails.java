package com.primus.BankApp.SecurityConfig;

import com.primus.BankApp.repository.BankUserRepository;
import com.primus.BankApp.model.BankUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class BankUserDetails implements UserDetailsService {

    @Autowired
    BankUserRepository bankUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password = null;
        List<GrantedAuthority> authorityList = null;

        List<BankUser> bankUser = bankUserRepository.findByEmail(username);

        if(bankUser.size()==0){
            throw new UsernameNotFoundException("User details not found for the user : " + username);
        } else {
            userName = bankUser.get(0).getEmail();
            password = bankUser.get(0).getPwd();
            authorityList = new ArrayList<>();
            authorityList.add(new SimpleGrantedAuthority(bankUser.get(0).getRole()));
        }
        return new User(userName,password,authorityList);
    }
}
