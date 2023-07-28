package com.notificationapi.model;

import com.notificationapi.enums.TokenStatus;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import static java.util.Calendar.HOUR;

@Data
@Getter
@Setter
public class Token {

    private static final Integer HOURS_EXPIRATION_CODE = 2;

    private UUID id;

    private String hashToken;

    private String contentCode;

    private Date createdAt;

    private Date expirationAt;

    private TokenStatus tokenStatus;


    public Token() {
        this.id = UUID.randomUUID();
        this.contentCode = this.generateToken();
        this.hashToken = this.cryptToken();
        this.createdAt = new Date();
        this.expirationAt = this.timeExpiration();
        this.tokenStatus = TokenStatus.CREATED;
    }

    private String generateToken() {
        Random random = new Random();
        return String.valueOf(random.nextInt(999999));
    }

    private Date timeExpiration() {
        return new Date(this.getCreatedAt().getTime() + HOURS_EXPIRATION_CODE * HOUR);
    }

    private String cryptToken() {
        return BCrypt.hashpw(this.contentCode, BCrypt.gensalt());
    }

    public boolean isTokenExpired() {
        return this.getExpirationAt().before(new Date());
    }

    public boolean isTokenValid(String contentCode) {
        return BCrypt.checkpw(contentCode, this.hashToken);
    }
}
