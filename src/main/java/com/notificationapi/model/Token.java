package com.notificationapi.model;

import com.notificationapi.enums.TokenStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

@Getter
@Setter
@Entity
public class Token {

    private static final Integer ADD_SECONDS_EXPIRATION_CODE = 7200;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @NotBlank
    private String contentCode;

    @NotBlank
    private String hashToken;

    @NotNull
    private Date createdAt;

    @NotNull
    private Date expirationAt;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "token_status")
    private TokenStatusEnum tokenStatusEnum;


    @Builder
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
        return Date.from(Instant.now().plusSeconds(ADD_SECONDS_EXPIRATION_CODE));
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
