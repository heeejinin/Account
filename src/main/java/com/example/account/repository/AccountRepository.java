package com.example.account.repository;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // 엔티티를 DB에 저장하기 위해서는 JPA에서 제공하는 Repository가 필요함
    // 인터페이스로 생성 JpaRepository를 확장해서 만드는 Repository임
    // <Account, Long> : Account는 Repository가 허용하게 될 Entity, Long은 Entity의 PK 타입
    Optional<Account> findFirstByOrderByIdDesc(); // Optional => 값이 있을 수도 없을 수도 있기 때문

    Integer countByAccountUser(AccountUser accountUser);

    Optional<Account> findByAccountNumber(String AccountNumber);

    List<Account> findByAccountUser(AccountUser accountUser);
}
