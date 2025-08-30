package io.github.rawadhossain.GikiZero.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "provider", "providerAccountId" }) })
public class Account {
    @Id
    private String id;

    private String type;
    private String provider;
    private String providerAccountId;

    @Lob
    private String refresh_token;

    @Lob
    private String access_token;

    private Integer expires_at;
    private String token_type;
    private String scope;

    @Lob
    private String id_token;

    private String session_state;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
