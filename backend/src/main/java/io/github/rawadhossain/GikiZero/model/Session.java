package io.github.rawadhossain.GikiZero.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Session {
    @Id
    private String id;

    @Column(unique = true)
    private String sessionToken;

    private LocalDateTime expires;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
