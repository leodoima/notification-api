package com.notificationapi.model;

import com.notificationapi.enums.TokenStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import static java.util.Calendar.HOUR;

@Getter
@Setter
@Entity
public class Token {

    private static final Integer HOURS_EXPIRATION_CODE = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @NotBlank
    private String contentCode;

    @NotBlank
    private String hashToken;

    @CreatedDate
    @NotNull
    private Date createdAt;

    @NotNull
    private Date expirationAt;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "token_status")
    private TokenStatusEnum tokenStatusEnum;


    public Token() {
        this.contentCode = this.generateToken();
        this.hashToken = this.cryptToken();
        this.createdAt = new Date();
        this.expirationAt = this.timeExpiration();
        this.tokenStatusEnum = TokenStatusEnum.CREATED;
    }

    private String generateToken() {
        Random random = new Random();
        return String.valueOf(random.nextInt(999999));
    }

    private Date timeExpiration() {
        return new Date(this.createdAt.getTime() + HOURS_EXPIRATION_CODE * HOUR);
    }

    private String cryptToken() {
        return BCrypt.hashpw(this.contentCode, BCrypt.gensalt());
    }

    public boolean isTokenExpired() {
        return this.expirationAt.before(new Date());
    }

    public boolean isTokenValid(String contentCode) {
        return BCrypt.checkpw(contentCode, this.hashToken);
    }
}
