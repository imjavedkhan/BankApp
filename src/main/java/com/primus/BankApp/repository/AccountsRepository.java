package com.primus.BankApp.repository;

import com.primus.BankApp.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    Accounts findByCustomerId(int customerId);
}
