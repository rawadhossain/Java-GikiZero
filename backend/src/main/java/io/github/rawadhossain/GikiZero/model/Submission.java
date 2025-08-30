package io.github.rawadhossain.GikiZero.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Submission {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private String transportationType;
    private String transportationFrequency;
    private String transportationDistance;
    private double transportationScore = 0;

    private String electricityUnits;
    private boolean renewableEnergy = false;
    private double energyScore = 0;

    private String waterUsage;
    private double waterScore = 0;

    private String dietType;
    private String meatIntakeFreq;
    private double dietScore = 0;

    private String foodWasteLevel;
    private double foodWasteScore = 0;

    private String clothesPerMonth;
    private double shoppingScore = 0;

    private String recyclingHabits;
    private double wasteScore = 0;

    private String streamingHabits;
    private double electronicsScore = 0;

    private String airTravelFreq;
    private double travelScore = 0;

    private String applianceUsage;
    private double applianceScore = 0;

    private double totalEmissionScore = 0;
    private String impactCategory = "Medium";

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
