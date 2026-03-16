package main;

/**
 * Abstract base type for all rally cars.
 * Concrete subclasses provide a surface-specific performance algorithm.
 */
public abstract class RallyCar {
    private String make;
    private String model;
    private int horsepower;

    /**
     * Creates a rally car with basic technical properties.
     *
     * @param make manufacturer name
     * @param model model name
     * @param horsepower engine power in HP
     */
    protected RallyCar(String make, String model, int horsepower) {
        this.make = make;
        this.model = model;
        this.horsepower = horsepower;
    }

    /**
     * Calculates a performance rating for the car.
     *
     * @return performance score
     */
    public abstract double calculatePerformance();

    /**
     * @return manufacturer name
     */
    public String getMake() {
        return make;
    }

    /**
     * @param make manufacturer name
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * @return model name
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model model name
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return horsepower in HP
     */
    public int getHorsepower() {
        return horsepower;
    }

    /**
     * @param horsepower horsepower in HP
     */
    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    /**
     * @return human-readable car description
     */
    public String getDisplayName() {
        return make + " " + model + " (" + horsepower + " HP)";
    }
}
