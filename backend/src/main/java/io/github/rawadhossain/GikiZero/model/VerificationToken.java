package io.github.rawadhossain.GikiZero.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "identifier", "token" }) })
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String identifier;

    @Column(unique = true)
    private String token;

    private LocalDateTime expires;
}
