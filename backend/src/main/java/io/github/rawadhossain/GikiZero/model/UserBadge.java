package io.github.rawadhossain.GikiZero.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "userId", "badgeId" }) })
public class UserBadge {
    @Id
    private String id;

    private LocalDateTime earnedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "badgeId", nullable = false)
    private Badge badge;
}
