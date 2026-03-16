package main;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Concrete implementation of race result storage for one rally event.
 */
public class RallyRaceResult implements RaceResult {
    private final String raceName;
    private final String surface;
    private final Map<Driver, Integer> results;

    /**
     * Creates an empty result set for one race.
     *
     * @param raceName race name
     * @param surface race surface, e.g., Gravel or Asphalt
     */
    public RallyRaceResult(String raceName, String surface) {
        this.raceName = raceName;
        this.surface = surface;
        this.results = new LinkedHashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void recordResult(Driver driver, int points) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null.");
        }
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
        results.put(driver, points);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Driver, Integer> getResults() {
        return Collections.unmodifiableMap(results);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRaceName() {
        return raceName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSurface() {
        return surface;
    }
}
