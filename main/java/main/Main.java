package main;

import java.util.Comparator;
import java.util.Locale;
import java.util.Map;

/**
 * Entry point that demonstrates a full Rally Championship Management System run.
 */
public class Main {

    /**
     * Runs a complete championship scenario with two races and reporting output.
     *
     * @param args CLI arguments (unused)
     */
    public static void main(String[] args) {
        ChampionshipManager manager = ChampionshipManager.getInstance();

        RallyCar gravelSpec = new GravelCar("Toyota", "GR Yaris Rally2", 295);
        RallyCar asphaltSpec = new AsphaltCar("Hyundai", "i20 N Rally2", 300);
        RallyCar mixedSetup = new AsphaltCar("Skoda", "Fabia RS Rally2", 297);

        Driver driver1 = new Driver("Kalle Rovanpera", "Finland", gravelSpec);
        Driver driver2 = new Driver("Thierry Neuville", "Belgium", asphaltSpec);
        Driver driver3 = new Driver("Elfyn Evans", "United Kingdom", mixedSetup);

        manager.registerDriver(driver1);
        manager.registerDriver(driver2);
        manager.registerDriver(driver3);

        RallyRaceResult race1 = new RallyRaceResult("Arctic Rally", "Gravel");
        race1.recordResult(driver1, 25);
        race1.recordResult(driver3, 18);
        race1.recordResult(driver2, 15);
        manager.recordRace(race1);

        // Drivers switch cars before the asphalt event.
        driver1.setCar(new AsphaltCar("Toyota", "GR Yaris Rally2", 295));
        driver2.setCar(new GravelCar("Hyundai", "i20 N Rally2", 300));

        RallyRaceResult race2 = new RallyRaceResult("Mediterranean Rally", "Asphalt");
        race2.recordResult(driver2, 25);
        race2.recordResult(driver1, 18);
        race2.recordResult(driver3, 15);
        manager.recordRace(race2);

        printChampionshipStandings();
        printLeader();
        printStatistics();
        printDetailedRaceResults(manager);
        printPerformanceRatings(manager);
    }

    private static void printChampionshipStandings() {
        System.out.println("=== CHAMPIONSHIP STANDINGS ===");
        ChampionshipManager.getChampionshipStandings().forEach(driver ->
            System.out.printf("%s (%s) - %d pts%n",
                driver.getName(),
                driver.getCountry(),
                driver.getTotalPoints())
        );
        System.out.println();
    }

    private static void printLeader() {
        Driver leader = ChampionshipManager.findLeadingDriver();
        System.out.println("=== CURRENT LEADER ===");
        if (leader == null) {
            System.out.println("No leader yet.");
        } else {
            System.out.printf("%s with %d pts%n", leader.getName(), leader.getTotalPoints());
        }
        System.out.println();
    }

    private static void printStatistics() {
        System.out.println("=== CHAMPIONSHIP STATISTICS ===");
        System.out.printf("Total drivers registered: %d%n", ChampionshipManager.getTotalDrivers());
        System.out.printf("Total races held: %d%n", ChampionshipStatistics.countTotalRacesHeld());
        System.out.printf(Locale.US, "Average points per driver: %.2f%n", ChampionshipStatistics.calculateAveragePointsPerDriver());
        System.out.printf("Most successful country: %s%n", ChampionshipStatistics.findMostSuccessfulCountry());
        System.out.printf("Total championship points: %d%n", ChampionshipManager.calculateTotalChampionshipPoints());
        System.out.println();
    }

    private static void printDetailedRaceResults(ChampionshipManager manager) {
        System.out.println("=== DETAILED RACE RESULTS ===");
        for (RaceResult result : manager.getRaceResults()) {
            System.out.printf("%s [%s]%n", result.getRaceName(), result.getSurface());
            result.getResults().entrySet().stream()
                .sorted(Map.Entry.<Driver, Integer>comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.printf("- %s: %d pts%n", entry.getKey().getName(), entry.getValue()));
        }
        System.out.println();
    }

    private static void printPerformanceRatings(ChampionshipManager manager) {
        System.out.println("=== CAR PERFORMANCE RATINGS ===");
        for (Driver driver : manager.getDrivers()) {
            System.out.printf(Locale.US,
                "%s -> %s | Rating: %.2f%n",
                driver.getName(),
                driver.getCar().getDisplayName(),
                driver.getCar().calculatePerformance());
        }
    }
}
