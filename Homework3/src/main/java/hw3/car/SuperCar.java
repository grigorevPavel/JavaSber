package hw3.car;

public class SuperCar extends Vehicle {
    public SuperCar(
            long id,
            String brand,
            String modelName,
            int maxVelocity,
            int power,
            long ownerId,
            int weight)
    {
        super(id, brand, modelName, maxVelocity, power, ownerId);
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    private final int weight;
}
