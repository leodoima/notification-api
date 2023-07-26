package com.notificationapi.model.token;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import static java.util.Calendar.HOUR;

@Data
@Getter
@Setter
public class TokenModel {

    private UUID id;

    private String hashToken;

    private String contentCode;

    private Date createdAt;

    private Date expirationAt;

    private static final Integer HOURS_EXPIRATION_CODE = 2;


    public TokenModel() {
        this.id = UUID.randomUUID();
        this.contentCode = this.generateToken();
        this.hashToken = this.cryptToken();
        this.createdAt = new Date();
        this.expirationAt = this.timeExpiration();
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
