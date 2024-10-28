package com.example.account.repository;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import com.example.account.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {
    // 엔티티를 DB에 저장하기 위해서는 JPA에서 제공하는 Repository가 필요함
    Optional<Transaction> findByTransactionId(String transactionId);
}
