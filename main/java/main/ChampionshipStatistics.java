package main;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class with static methods for championship-level statistics.
 */
public final class ChampionshipStatistics {

    /**
     * Utility class constructor hidden.
     */
    private ChampionshipStatistics() {
    }

    /**
     * Calculates average points per registered driver.
     *
     * @return average points, 0.0 when no drivers are present
     */
    public static double calculateAveragePointsPerDriver() {
        int totalDrivers = ChampionshipManager.getTotalDrivers();
        if (totalDrivers == 0) {
            return 0.0;
        }
        return (double) ChampionshipManager.calculateTotalChampionshipPoints() / totalDrivers;
    }

    /**
     * Finds the country with the highest total points from all drivers.
     *
     * @return most successful country or "N/A" when there are no drivers
     */
    public static String findMostSuccessfulCountry() {
        return ChampionshipManager.getInstance().getDrivers().stream()
            .collect(Collectors.groupingBy(Driver::getCountry, Collectors.summingInt(Driver::getTotalPoints)))
            .entrySet()
            .stream()
            .max(Comparator.comparingInt(Map.Entry::getValue))
            .map(Map.Entry::getKey)
            .orElse("N/A");
    }

    /**
     * Counts total races held in the championship.
     *
     * @return race count
     */
    public static int countTotalRacesHeld() {
        return ChampionshipManager.getTotalRaces();
    }
}
