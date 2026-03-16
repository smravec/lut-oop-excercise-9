package main;

/**
 * Rally car optimized for gravel surfaces.
 */
public class GravelCar extends RallyCar {

    /**
     * Creates a gravel rally car.
     *
     * @param make manufacturer name
     * @param model model name
     * @param horsepower engine power in HP
     */
    public GravelCar(String make, String model, int horsepower) {
        super(make, model, horsepower);
    }

    /**
     * Gravel algorithm gives stronger weight to traction and durability behavior.
     *
     * @return gravel performance score
     */
    @Override
    public double calculatePerformance() {
        double tractionFactor = 1.18;
        double durabilityFactor = 28.0;
        return (getHorsepower() * tractionFactor) + durabilityFactor;
    }
}
