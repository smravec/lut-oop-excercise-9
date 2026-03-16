package main;

import java.util.Map;

/**
 * Defines operations for recording and reading rally race result data.
 */
public interface RaceResult {

    /**
     * Records points for a driver in the race result object.
     *
     * @param driver driver to record
     * @param points points scored in this race
     */
    void recordResult(Driver driver, int points);

    /**
     * Returns immutable-like read access to race points map.
     *
     * @return map of driver to points in this race
     */
    Map<Driver, Integer> getResults();

    /**
     * @return display name of the race
     */
    String getRaceName();

    /**
     * @return race surface type
     */
    String getSurface();
}
