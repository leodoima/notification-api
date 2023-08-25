package com.notificationapi.model;

import com.notificationapi.enums.OwnerRequestEnum;
import com.notificationapi.enums.StatusSendEnum;
import com.notificationapi.enums.TokenTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class SenderToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status_send")
    private StatusSendEnum statusSendEnum;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "owner_request")
    private OwnerRequestEnum ownerRequestEnum;

    @NotNull
    @CreatedDate
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "token_type")
    private TokenTypeEnum tokenTypeEnum;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "token_id", referencedColumnName = "id")
    private Token token;
}
