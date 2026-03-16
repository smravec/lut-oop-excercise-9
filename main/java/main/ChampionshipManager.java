package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Singleton manager that owns drivers, race results, and championship logic.
 */
public class ChampionshipManager {
    private static ChampionshipManager instance;

    private static int totalDrivers;
    private static int totalRaces;

    private final List<Driver> drivers;
    private final List<RaceResult> raceResults;

    /**
     * Private constructor for Singleton.
     */
    private ChampionshipManager() {
        this.drivers = new ArrayList<>();
        this.raceResults = new ArrayList<>();
    }

    /**
     * Gets the single manager instance.
     *
     * @return singleton manager
     */
    public static ChampionshipManager getInstance() {
        if (instance == null) {
            instance = new ChampionshipManager();
        }
        return instance;
    }

    /**
     * Registers a driver into the championship.
     *
     * @param driver driver to add
     */
    public void registerDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null.");
        }
        drivers.add(driver);
        totalDrivers = drivers.size();
    }

    /**
     * Records one full race result and updates cumulative driver points.
     *
     * @param raceResult race result to add
     */
    public void recordRace(RaceResult raceResult) {
        if (raceResult == null) {
            throw new IllegalArgumentException("Race result cannot be null.");
        }

        raceResults.add(raceResult);
        totalRaces = raceResults.size();

        for (Map.Entry<Driver, Integer> entry : raceResult.getResults().entrySet()) {
            entry.getKey().addPoints(entry.getValue());
        }
    }

    /**
     * @return read-only list of registered drivers
     */
    public List<Driver> getDrivers() {
        return List.copyOf(drivers);
    }

    /**
     * @return read-only list of all race results
     */
    public List<RaceResult> getRaceResults() {
        return List.copyOf(raceResults);
    }

    /**
     * Static championship standings sorted by descending points.
     *
     * @return sorted list of drivers
     */
    public static List<Driver> getChampionshipStandings() {
        return getInstance().drivers.stream()
            .sorted(Comparator.comparingInt(Driver::getTotalPoints).reversed())
            .collect(Collectors.toList());
    }

    /**
     * Static helper to get current leading driver.
     *
     * @return leader or null when no drivers exist
     */
    public static Driver findLeadingDriver() {
        return getChampionshipStandings().stream().findFirst().orElse(null);
    }

    /**
     * Static helper to compute total points across all drivers.
     *
     * @return total championship points
     */
    public static int calculateTotalChampionshipPoints() {
        return getInstance().drivers.stream().mapToInt(Driver::getTotalPoints).sum();
    }

    /**
     * @return total registered drivers (static counter)
     */
    public static int getTotalDrivers() {
        return totalDrivers;
    }

    /**
     * @return total races held (static counter)
     */
    public static int getTotalRaces() {
        return totalRaces;
    }
}
