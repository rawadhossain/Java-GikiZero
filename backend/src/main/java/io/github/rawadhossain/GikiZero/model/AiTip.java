package io.github.rawadhossain.GikiZero.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AiTip {
    @Id
    private String id;

    private String title;

    @Lob
    private String description;

    private String category;
    private String impact;

    @Lob
    private String reasoning;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
