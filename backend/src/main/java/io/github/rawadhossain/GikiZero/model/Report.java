package io.github.rawadhossain.GikiZero.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Report {
    @Id
    private String id;

    private String type;
    private String filename;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
