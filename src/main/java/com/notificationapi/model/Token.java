package com.notificationapi.model;

import com.notificationapi.enums.TokenStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
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
    private String contentCode;

    @NotBlank
    private String hashToken;

    @NotNull
    @CreatedDate
    private Date createdAt;

    @NotNull
    private Date expirationAt;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "token_status")
    private TokenStatusEnum tokenStatusEnum;


    @Builder(toBuilder = true)
    public Token() {
        this.contentCode = this.generateToken();
        this.hashToken = this.cryptToken();
        this.createdAt = new Date();
        this.expirationAt = this.timeExpiration();
        this.tokenStatusEnum = TokenStatusEnum.CREATED;
    }

    public Token(Long id, String hashToken, Date createdAt, Date expirationAt, TokenStatusEnum tokenStatusEnum) {
        this.id = id;
        this.hashToken = hashToken;
        this.createdAt = createdAt;
        this.expirationAt = expirationAt;
        this.tokenStatusEnum = tokenStatusEnum;
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

    private boolean isTokenExpired() {
        return this.expirationAt.before(new Date());
    }

    private boolean isTokenPending() {
        return this.tokenStatusEnum.equals(TokenStatusEnum.CREATED);
    }

    public boolean isTokenValid() {
        return isTokenPending() && !isTokenExpired();
    }

    public boolean isTokenChecked(String contentCode) {
        return BCrypt.checkpw(contentCode, this.hashToken);
    }
}
