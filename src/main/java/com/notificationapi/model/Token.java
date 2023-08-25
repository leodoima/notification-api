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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Token {

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

    public Token(Long id, String hashToken, Date createdAt, Date expirationAt, TokenStatusEnum tokenStatusEnum) {
        this.id = id;
        this.hashToken = hashToken;
        this.createdAt = createdAt;
        this.expirationAt = expirationAt;
        this.tokenStatusEnum = tokenStatusEnum;
    }

    @Builder(builderMethodName = "builder")
    public static Token tokenBuilder(String contentCode, String hashToken, Date createdAt, Date expirationAt, TokenStatusEnum tokenStatusEnum) {
        return new Token(null, contentCode, hashToken, createdAt, expirationAt, tokenStatusEnum);
    }

    private boolean isTokenExpired() {
        return this.expirationAt.before(new Date());
    }

    private boolean isTokenPending() {
        return this.tokenStatusEnum.equals(TokenStatusEnum.CREATED);
    }

    public boolean isTokenValidated(String contentCode) {
        return isTokenPending() && !isTokenExpired() && BCrypt.checkpw(contentCode, this.hashToken);
    }
}
