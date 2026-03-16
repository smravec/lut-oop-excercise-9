package main;

/**
 * Rally car optimized for asphalt surfaces.
 */
public class AsphaltCar extends RallyCar {

    /**
     * Creates an asphalt rally car.
     *
     * @param make manufacturer name
     * @param model model name
     * @param horsepower engine power in HP
     */
    public AsphaltCar(String make, String model, int horsepower) {
        super(make, model, horsepower);
    }

    /**
     * Asphalt algorithm gives stronger weight to top speed and precision handling.
     *
     * @return asphalt performance score
     */
    @Override
    public double calculatePerformance() {
        double speedFactor = 1.27;
        double handlingBonus = 22.0;
        return (getHorsepower() * speedFactor) + handlingBonus;
    }
}
