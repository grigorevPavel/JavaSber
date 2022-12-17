package hw2.garage;

import java.util.*;

import hw2.car.Car;
import hw2.owner.Owner;

public interface Garage {

    Collection<Owner> allCarsUniqueOwners();

    // Complexity should be less than O(n)
    Collection<Car> topThreeCarsByMaxVelocity();

    // Complexity should be O(1)
    Collection<Car> allCarsOfBrand(String brand);

    // Complexity should be less than O(n)
    Collection<Car> carsWithPowerMoreThan(int power);

    /**
     * Complexity should be O(1)
     */
    Collection<Car> allCarsOfOwner(Owner owner);

    /**
     * @return mean value of owner age that has cars with given brand
     */
    double meanOwnersAgeOfCarBrand(String brand);

    /**
     * @return mean value of cars for all owners
     */
    double meanCarNumberForEachOwner();

    /**
     * Complexity should be less than O(n)
     * @return removed car
     */
    Car removeCar(long carId);

    /**
     * Complexity should be less than O(n)
     */
    void addCar(Car car, Owner owner);
}
