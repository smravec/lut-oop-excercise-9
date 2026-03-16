package main;

/**
 * Represents a championship driver.
 * A car is injected into the driver to support dependency inversion and flexibility.
 */
public class Driver {
    private String name;
    private String country;
    private int totalPoints;
    private RallyCar car;

    /**
     * Creates a new driver with an injected rally car.
     *
     * @param name driver name
     * @param country country represented by the driver
     * @param car current assigned rally car
     */
    public Driver(String name, String country, RallyCar car) {
        this.name = name;
        this.country = country;
        this.car = car;
        this.totalPoints = 0;
    }

    /**
     * Adds points to the driver's championship total.
     *
     * @param points points to add
     */
    public void addPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
        this.totalPoints += points;
    }

    /**
     * @return driver name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name driver name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country country name
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return total championship points
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * @param totalPoints absolute points value
     */
    public void setTotalPoints(int totalPoints) {
        if (totalPoints < 0) {
            throw new IllegalArgumentException("Total points cannot be negative.");
        }
        this.totalPoints = totalPoints;
    }

    /**
     * @return currently assigned car
     */
    public RallyCar getCar() {
        return car;
    }

    /**
     * Switches the driver's car.
     *
     * @param car new car object
     */
    public void setCar(RallyCar car) {
        if (car == null) {
            throw new IllegalArgumentException("Car cannot be null.");
        }
        this.car = car;
    }
}
