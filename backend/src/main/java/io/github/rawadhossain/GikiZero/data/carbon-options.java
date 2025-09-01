import java.util.*;

class EnvironmentalData {
    String transportMode;
    String transportFrequency;
    String travelDistance;
    String energyConsumption;
    Boolean usesRenewableEnergy;
    String waterConsumption;
    String foodPreferences;
    String meatConsumptionFrequency;
    String wasteGeneration;
    String clothingPurchases;
    String recyclingBehaviour;
    String onlineStreamingTime;
    String airTravelFrequency;
    String applianceUse;
    String houseSize;
    String heatingSystem;
    String wasteManagement;
    String gadgetCount;
    String petStatus;
    String gardeningHabits;
}

class ImpactScore {
    Map<String, Double> categoryScores;
    double overallScore;
    String environmentalImpact;

    public ImpactScore(Map<String, Double> categoryScores, double overallScore, String environmentalImpact) {
        this.categoryScores = categoryScores;
        this.overallScore = overallScore;
        this.environmentalImpact = environmentalImpact;
    }
}

class Option {
    String identifier;
    String label;

    public Option(String identifier, String label) {
        this.identifier = identifier;
        this.label = label;
    }
}

class Inquiry {
    String id;
    String category;
    String prompt;
    String inputType; // select or radio
    List<Option> availableOptions;

    public Inquiry(String id, String category, String prompt, String inputType, List<Option> availableOptions) {
        this.id = id;
        this.category = category;
        this.prompt = prompt;
        this.inputType = inputType;
        this.availableOptions = availableOptions;
    }
}

public class EnvironmentalImpactCalculator {

    public static ImpactScore calculateImpactScore(EnvironmentalData data) {
        Map<String, Double> categoryScores = new HashMap<>();
        categoryScores.put("transportation", 0.0);
        categoryScores.put("energy", 0.0);
        categoryScores.put("water", 0.0);
        categoryScores.put("food", 0.0);
        categoryScores.put("waste", 0.0);
        categoryScores.put("shopping", 0.0);
        categoryScores.put("electronics", 0.0);
        categoryScores.put("travel", 0.0);
        categoryScores.put("appliances", 0.0);
        categoryScores.put("house", 0.0);
        categoryScores.put("heating", 0.0);
        categoryScores.put("gadgets", 0.0);
        categoryScores.put("pets", 0.0);
        categoryScores.put("garden", 0.0);

        // Transportation
        Map<String, Integer> transportScoreMapping = Map.of(
                "car-petrol", 100,
                "car-diesel", 95,
                "car-hybrid", 50,
                "public-transport", 40,
                "bike", 10,
                "foot", 0,
                "motorbike", 70
        );

        Map<String, Double> frequencyMultipliers = Map.of(
                "daily", 1.0,
                "weekly", 0.6,
                "monthly", 0.3,
                "rarely", 0.1,
                "never", 0.0
        );

        Map<String, Double> distanceMultipliers = Map.of(
                "short", 0.4,
                "medium", 0.9,
                "long", 1.3,
                "very-long", 1.7
        );

        if (data.transportMode != null && data.transportFrequency != null && data.travelDistance != null) {
            double baseScore = transportScoreMapping.getOrDefault(data.transportMode, 60);
            double frequencyMultiplier = frequencyMultipliers.getOrDefault(data.transportFrequency, 0.5);
            double distanceMultiplier = distanceMultipliers.getOrDefault(data.travelDistance, 1.0);
            categoryScores.put("transportation", baseScore * frequencyMultiplier * distanceMultiplier);
        }

        // Energy
        Map<String, Integer> energyScores = Map.of(
                "low", 30,
                "medium", 70,
                "high", 100,
                "very-high", 150
        );

        if (data.energyConsumption != null) {
            double energyImpact = energyScores.getOrDefault(data.energyConsumption, 70);
            if (Boolean.TRUE.equals(data.usesRenewableEnergy)) {
                energyImpact *= 0.4; // 60% reduction for renewable energy
            }
            categoryScores.put("energy", energyImpact);
        }
        // Water
        Map<String, Integer> waterImpactScores = Map.of(
                "low", 10,
                "medium", 30,
                "high", 50,
                "very-high", 70
        );
        if (data.waterConsumption != null) {
            categoryScores.put("water", (double) waterImpactScores.getOrDefault(data.waterConsumption, 40));
        }

        // Food
        Map<String, Integer> foodImpactScores = Map.of(
                "vegan", 15,
                "vegetarian", 30,
                "pescatarian", 45,
                "omnivore", 70,
                "meat-heavy", 100
        );
        if (data.foodPreferences != null) {
            categoryScores.put("food", (double) foodImpactScores.getOrDefault(data.foodPreferences, 70));
        }

        // Water
        Map<String, Integer> waterImpactScores = Map.of(
                "low", 10,
                "medium", 30,
                "high", 50,
                "very-high", 70
        );
        if (data.waterConsumption != null) {
            categoryScores.put("water", (double) waterImpactScores.getOrDefault(data.waterConsumption, 40));
        }

        // Food
        Map<String, Integer> foodImpactScores = Map.of(
                "vegan", 15,
                "vegetarian", 30,
                "pescatarian", 45,
                "omnivore", 70,
                "meat-heavy", 100
        );
        if (data.foodPreferences != null) {
            categoryScores.put("food", (double) foodImpactScores.getOrDefault(data.foodPreferences, 70));
        }

        // Waste
        Map<String, Integer> wasteImpactScores = Map.of(
                "none", 0,
                "minimal", 10,
                "moderate", 30,
                "high", 50
        );
        if (data.wasteGeneration != null) {
            categoryScores.put("waste", (double) wasteImpactScores.getOrDefault(data.wasteGeneration, 20));
        }

        // Total Impact
        double totalScore = categoryScores.values().stream().mapToDouble(Double::doubleValue).sum();
        String environmentalImpact = totalScore < 200 ? "Low" : totalScore < 400 ? "Moderate" : "High";

        return new ImpactScore(categoryScores, totalScore, environmentalImpact);
    }

    public static List<Inquiry> generateSampleQuestions() {
        List<Inquiry> questionList = new ArrayList<>();

        questionList.add(new Inquiry(
                "transportation",
                "Transportation",
                "How do you primarily commute?",
                "select",
                Arrays.asList(
                        new Option("car-petrol", "Petrol Car"),
                        new Option("car-diesel", "Diesel Car"),
                        new Option("car-hybrid", "Hybrid Car"),
                        new Option("public-transport", "Public Transport"),
                        new Option("bike", "Bicycle"),
                        new Option("foot", "Walking"),
                        new Option("motorbike", "Motorbike")
                )
        ));

        // Add similar question definitions for other categories (energy, food, water, etc.)

        Collections.shuffle(questionList);
        int size = 8 + new Random().nextInt(5); // 8–12
        return questionList.subList(0, Math.min(size, questionList.size()));
    }
}
