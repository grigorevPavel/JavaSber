package hw3.car;

public class Truck extends Vehicle {
    public Truck(
            long id,
            String brand,
            String modelName,
            int maxVelocity,
            int power,
            long ownerId,
            int wheelsNumber)
    {
        super(id, brand, modelName, maxVelocity, power, ownerId);
        this.wheelsNumber = wheelsNumber;
    }

    public int getWheelsNumber() {
        return wheelsNumber;
    }

    private final int wheelsNumber;
}
