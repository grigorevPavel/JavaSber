package hw2.car;

import lombok.Getter;

@Getter
public class Car {
    public Car(long car_id_, String brand_, String model_name_, int max_velocity_, int power_, long owner_id_) {
        this.carId = car_id_;
        this.brand = brand_;
        this.modelName = model_name_;
        this.maxVelocity = max_velocity_;
        this.power = power_;
        this.ownerId = owner_id_;
    }

    public Car(int power) {
        this.carId = 0;
        this.brand = "";
        this.modelName = "";
        this.maxVelocity = 0;
        this.power = power;
        this.ownerId = 0;
    }

    private final long carId;
    private final String brand;
    private final String modelName;
    private final int maxVelocity;
    private final int power;
    private final long ownerId;
}
