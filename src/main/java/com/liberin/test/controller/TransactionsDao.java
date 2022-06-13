package com.liberin.test.controller;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsDao extends JpaRepository<MT940Transactions, UUID> {
    
    // @Query("select t from MT940Transactions t where t.customerId= :id and t.accountNumber = :a and t.transactionDate = :d")
    // List<MT940Transactions> findTransactions(@Param("id") Long customerId, @Param("a") Long paramLong,
    //         @Param("d") Date paramDate);
            
}
