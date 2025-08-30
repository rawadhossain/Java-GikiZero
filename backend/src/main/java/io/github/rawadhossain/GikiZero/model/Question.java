package io.github.rawadhossain.GikiZero.model;

import jakarta.persistence.*;

@Entity
public class Question {
    @Id
    private String id;

    private String category;
    private String question;
    private String type;

    @Lob
    private String options; // JSON stored as text

    private double weight = 1.0;
    private boolean isActive = true;
}
