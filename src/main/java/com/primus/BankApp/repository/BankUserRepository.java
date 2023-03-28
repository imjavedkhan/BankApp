package com.primus.BankApp.repository;

import com.primus.BankApp.model.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser,Long> {

    List<BankUser> findByEmail(String email);
}
