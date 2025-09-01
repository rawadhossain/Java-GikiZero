package io.github.rawadhossain.GikiZero.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String transportationType;
    private String transportationFrequency;
    private String transportationDistance;
    private double transportationScore;

    private String electricityUnits;
    private boolean renewableEnergy;
    private double energyScore;

    private String waterUsage;
    private double waterScore;

    private String dietType;
    private double dietScore;

    private String foodWasteLevel;
    private double foodWasteScore;

    private String clothesPerMonth;
    private double shoppingScore;

    private String recyclingHabits;
    private double wasteScore;

    private String streamingHabits;
    private double electronicsScore;

    private String airTravelFreq;
    private double travelScore;

    private String applianceUsage;
    private double applianceScore;

    private double totalEmissionScore;
    private String impactCategory;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Getters and setters
    public String getId() {
        return id;
    }
    public void setId(String id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getTransportationType() { return transportationType; }
    public void setTransportationType(String transportationType) { this.transportationType = transportationType; }

    public String getTransportationFrequency() { return transportationFrequency; }
    public void setTransportationFrequency(String transportationFrequency) { this.transportationFrequency = transportationFrequency; }

    public String getTransportationDistance() { return transportationDistance; }
    public void setTransportationDistance(String transportationDistance) { this.transportationDistance = transportationDistance; }

    public double getTransportationScore() { return transportationScore; }
    public void setTransportationScore(double transportationScore) { this.transportationScore = transportationScore; }

    public String getElectricityUnits() { return electricityUnits; }
    public void setElectricityUnits(String electricityUnits) { this.electricityUnits = electricityUnits; }

    public boolean isRenewableEnergy() { return renewableEnergy; }
    public void setRenewableEnergy(boolean renewableEnergy) { this.renewableEnergy = renewableEnergy; }

    public double getEnergyScore() { return energyScore; }
    public void setEnergyScore(double energyScore) { this.energyScore = energyScore; }

    public String getWaterUsage() { return waterUsage; }
    public void setWaterUsage(String waterUsage) { this.waterUsage = waterUsage; }

    public double getWaterScore() { return waterScore; }
    public void setWaterScore(double waterScore) { this.waterScore = waterScore; }

    public String getDietType() { return dietType; }
    public void setDietType(String dietType) { this.dietType = dietType; }

    public double getDietScore() { return dietScore; }
    public void setDietScore(double dietScore) { this.dietScore = dietScore; }

    public String getFoodWasteLevel() { return foodWasteLevel; }
    public void setFoodWasteLevel(String foodWasteLevel) { this.foodWasteLevel = foodWasteLevel; }

    public double getFoodWasteScore() { return foodWasteScore; }
    public void setFoodWasteScore(double foodWasteScore) { this.foodWasteScore = foodWasteScore; }

    public String getClothesPerMonth() { return clothesPerMonth; }
    public void setClothesPerMonth(String clothesPerMonth) { this.clothesPerMonth = clothesPerMonth; }

    public double getShoppingScore() { return shoppingScore; }
    public void setShoppingScore(double shoppingScore) { this.shoppingScore = shoppingScore; }

    public String getRecyclingHabits() { return recyclingHabits; }
    public void setRecyclingHabits(String recyclingHabits) { this.recyclingHabits = recyclingHabits; }

    public double getWasteScore() { return wasteScore; }
    public void setWasteScore(double wasteScore) { this.wasteScore = wasteScore; }

    public String getStreamingHabits() { return streamingHabits; }
    public void setStreamingHabits(String streamingHabits) { this.streamingHabits = streamingHabits; }

    public double getElectronicsScore() { return electronicsScore; }
    public void setElectronicsScore(double electronicsScore) { this.electronicsScore = electronicsScore; }

    public String getAirTravelFreq() { return airTravelFreq; }
    public void setAirTravelFreq(String airTravelFreq) { this.airTravelFreq = airTravelFreq; }

    public double getTravelScore() { return travelScore; }
    public void setTravelScore(double travelScore) { this.travelScore = travelScore; }

    public String getApplianceUsage() { return applianceUsage; }
    public void setApplianceUsage(String applianceUsage) { this.applianceUsage = applianceUsage; }

    public double getApplianceScore() { return applianceScore; }
    public void setApplianceScore(double applianceScore) { this.applianceScore = applianceScore; }

    public double getTotalEmissionScore() { return totalEmissionScore; }
    public void setTotalEmissionScore(double totalEmissionScore) { this.totalEmissionScore = totalEmissionScore; }

    public String getImpactCategory() { return impactCategory; }
    public void setImpactCategory(String impactCategory) { this.impactCategory = impactCategory; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
