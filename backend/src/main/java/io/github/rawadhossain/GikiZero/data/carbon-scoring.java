import java.util.*;

class SubmissionData {
    String transportationType;
    String transportationFrequency;
    String transportationDistance;
    String electricityUnits;
    Boolean renewableEnergy;
    String waterUsage;
    String dietType;
    String meatIntakeFreq;
    String foodWasteLevel;
    String clothesPerMonth;
    String recyclingHabits;
    String streamingHabits;
    String airTravelFreq;
    String applianceUsage;
    String homeSize;
    String heatingType;
    String wasteDisposal;
    String digitalDevices;
    String petOwnership;
    String gardenPractices;
}

class CarbonResult {
    Map<String, Double> scores;
    double totalScore;
    String impactCategory;

    public CarbonResult(Map<String, Double> scores, double totalScore, String impactCategory) {
        this.scores = scores;
        this.totalScore = totalScore;
        this.impactCategory = impactCategory;
    }
}

class QuestionOption {
    String value;
    String label;

    public QuestionOption(String value, String label) {
        this.value = value;
        this.label = label;
    }
}

class Question {
    String id;
    String category;
    String question;
    String type; // select or radio
    List<QuestionOption> options;

    public Question(String id, String category, String question, String type, List<QuestionOption> options) {
        this.id = id;
        this.category = category;
        this.question = question;
        this.type = type;
        this.options = options;
    }
}

public class CarbonCalculator {

    public static CarbonResult calculateCarbonScore(SubmissionData data) {
        Map<String, Double> scores = new HashMap<>();
        scores.put("transportation", 0.0);
        scores.put("energy", 0.0);
        scores.put("water", 0.0);
        scores.put("diet", 0.0);
        scores.put("foodWaste", 0.0);
        scores.put("shopping", 0.0);
        scores.put("waste", 0.0);
        scores.put("electronics", 0.0);
        scores.put("travel", 0.0);
        scores.put("appliance", 0.0);
        scores.put("home", 0.0);
        scores.put("heating", 0.0);
        scores.put("digital", 0.0);
        scores.put("pets", 0.0);
        scores.put("garden", 0.0);

        // Transportation
        Map<String, Integer> transportScores = Map.of(
                "car-gasoline", 120,
                "car-diesel", 110,
                "car-electric", 40,
                "public-transport", 30,
                "bicycle", 5,
                "walking", 0,
                "motorcycle", 80
        );

        Map<String, Double> frequencyMultiplier = Map.of(
                "daily", 1.0,
                "weekly", 0.7,
                "monthly", 0.3,
                "rarely", 0.1,
                "never", 0.0
        );

        Map<String, Double> distanceMultiplier = Map.of(
                "short", 0.5,
                "medium", 1.0,
                "long", 1.5,
                "very-long", 2.0
        );

        if (data.transportationType != null && data.transportationFrequency != null && data.transportationDistance != null) {
            double base = transportScores.getOrDefault(data.transportationType, 60);
            double freq = frequencyMultiplier.getOrDefault(data.transportationFrequency, 0.5);
            double dist = distanceMultiplier.getOrDefault(data.transportationDistance, 1.0);
            scores.put("transportation", base * freq * dist);
        }

        // Energy
        Map<String, Integer> electricityScore = Map.of(
                "very-low", 20,
                "low", 40,
                "medium", 80,
                "high", 120,
                "very-high", 160
        );

        if (data.electricityUnits != null) {
            double energy = electricityScore.getOrDefault(data.electricityUnits, 80);
            if (Boolean.TRUE.equals(data.renewableEnergy)) {
                energy *= 0.3; // 70% reduction
            }
            scores.put("energy", energy);
        }

        // Water
        Map<String, Integer> waterScores = Map.of(
                "very-low", 10,
                "low", 20,
                "medium", 40,
                "high", 60,
                "very-high", 80
        );
        if (data.waterUsage != null) {
            scores.put("water", (double) waterScores.getOrDefault(data.waterUsage, 40));
        }

        // Diet
        Map<String, Integer> dietScores = Map.of(
                "vegan", 20,
                "vegetarian", 35,
                "pescatarian", 50,
                "omnivore", 80,
                "high-meat", 120
        );
        if (data.dietType != null) {
            scores.put("diet", (double) dietScores.getOrDefault(data.dietType, 80));
        }

        // Food Waste
        Map<String, Integer> foodWasteScores = Map.of(
                "none", 0,
                "minimal", 10,
                "some", 25,
                "moderate", 40,
                "high", 60
        );
        if (data.foodWasteLevel != null) {
            scores.put("foodWaste", (double) foodWasteScores.getOrDefault(data.foodWasteLevel, 25));
        }

        // Shopping
        Map<String, Integer> shoppingScores = Map.of(
                "0", 0,
                "1-2", 15,
                "3-5", 30,
                "6-10", 50,
                "10+", 80
        );
        if (data.clothesPerMonth != null) {
            scores.put("shopping", (double) shoppingScores.getOrDefault(data.clothesPerMonth, 30));
        }

        // Waste/Recycling
        Map<String, Integer> recyclingScores = Map.of(
                "always", 0,
                "often", 10,
                "sometimes", 25,
                "rarely", 40,
                "never", 60
        );
        if (data.recyclingHabits != null) {
            scores.put("waste", (double) recyclingScores.getOrDefault(data.recyclingHabits, 25));
        }

        // Electronics
        Map<String, Integer> streamingScores = Map.of(
                "minimal", 5,
                "moderate", 15,
                "high", 30,
                "very-high", 50
        );
        if (data.streamingHabits != null) {
            scores.put("electronics", (double) streamingScores.getOrDefault(data.streamingHabits, 15));
        }

        // Air travel
        Map<String, Integer> travelScores = Map.of(
                "never", 0,
                "rarely", 50,
                "occasionally", 150,
                "frequently", 300,
                "very-frequently", 500
        );
        if (data.airTravelFreq != null) {
            scores.put("travel", (double) travelScores.getOrDefault(data.airTravelFreq, 50));
        }

        // Appliances
        Map<String, Integer> applianceScores = Map.of(
                "minimal", 20,
                "moderate", 40,
                "high", 60,
                "very-high", 80
        );
        if (data.applianceUsage != null) {
            scores.put("appliance", (double) applianceScores.getOrDefault(data.applianceUsage, 40));
        }

        // Home size
        Map<String, Integer> homeScores = Map.of(
                "studio", 20,
                "1-bedroom", 30,
                "2-bedroom", 45,
                "3-bedroom", 60,
                "4+", 80
        );
        if (data.homeSize != null) {
            scores.put("home", (double) homeScores.getOrDefault(data.homeSize, 45));
        }

        // Heating
        Map<String, Integer> heatingScores = Map.of(
                "electric", 80,
                "gas", 60,
                "oil", 70,
                "wood", 40,
                "solar", 20,
                "heat-pump", 30
        );
        if (data.heatingType != null) {
            scores.put("heating", (double) heatingScores.getOrDefault(data.heatingType, 60));
        }

        // Digital devices
        Map<String, Integer> digitalScores = Map.of(
                "1-2", 10,
                "3-5", 25,
                "6-10", 40,
                "10+", 60
        );
        if (data.digitalDevices != null) {
            scores.put("digital", (double) digitalScores.getOrDefault(data.digitalDevices, 25));
        }

        // Pets
        Map<String, Integer> petScores = Map.of(
                "none", 0,
                "small", 15,
                "medium", 25,
                "large", 35,
                "multiple", 50
        );
        if (data.petOwnership != null) {
            scores.put("pets", (double) petScores.getOrDefault(data.petOwnership, 0));
        }

        // Garden
        Map<String, Integer> gardenScores = Map.of(
                "none", 0,
                "basic", 5,
                "organic", -10,
                "composting", -15,
                "sustainable", -20
        );
        if (data.gardenPractices != null) {
            scores.put("garden", (double) gardenScores.getOrDefault(data.gardenPractices, 0));
        }

        // Total
        double totalScore = scores.values().stream().mapToDouble(Double::doubleValue).sum();
        String impactCategory = totalScore < 300 ? "Low" : totalScore < 600 ? "Medium" : "High";

        return new CarbonResult(scores, totalScore, impactCategory);
    }

    public static List<Question> getRandomQuestions() {
        List<Question> allQuestions = new ArrayList<>();

        allQuestions.add(new Question(
                "transportation",
                "Transportation",
                "What is your primary mode of transportation?",
                "select",
                Arrays.asList(
                        new QuestionOption("car-gasoline", "Gasoline Car"),
                        new QuestionOption("car-diesel", "Diesel Car"),
                        new QuestionOption("car-electric", "Electric Car"),
                        new QuestionOption("public-transport", "Public Transport"),
                        new QuestionOption("bicycle", "Bicycle"),
                        new QuestionOption("walking", "Walking"),
                        new QuestionOption("motorcycle", "Motorcycle")
                )
        ));

        // 👉 You would need to add the rest like in your TypeScript version (electricityUnits, waterUsage, etc.)

        Collections.shuffle(allQuestions);
        int size = 10 + new Random().nextInt(3); // 10–12
        return allQuestions.subList(0, Math.min(size, allQuestions.size()));
    }
}
