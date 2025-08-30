package io.github.rawadhossain.GikiZero.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Badge {
    @Id
    private String id;

    @Column(unique = true)
    private String name;

    private String description;
    private String icon;
    private String category;
    private String requirement;

    @OneToMany(mappedBy = "badge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBadge> users = new ArrayList<>();
}
