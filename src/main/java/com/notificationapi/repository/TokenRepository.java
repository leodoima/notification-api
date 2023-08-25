package com.notificationapi.repository;

import com.notificationapi.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = "select new com.notificationapi.model.Token(t.id, t.hashToken, t.createdAt, t.expirationAt, t.tokenStatusEnum) from Sms s inner join s.token t where s.phoneNumber = :phoneNumber order by t.createdAt desc limit 1")
    Token findLastTokenForNumber(String phoneNumber);
}
