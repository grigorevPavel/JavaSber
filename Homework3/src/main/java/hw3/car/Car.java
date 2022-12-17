package hw3.car;

import lombok.Getter;

@Getter
public class Car extends Vehicle {
    public Car(long carId, String brand, String modelName, int maxVelocity, int power, long ownerId) {
        super(carId, brand, modelName, maxVelocity, power, ownerId);
    }
}
