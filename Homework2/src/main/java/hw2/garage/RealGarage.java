package hw2.garage;

import java.util.*;
import java.util.stream.Collectors;

import hw2.car.Car;
import hw2.owner.Owner;
import hw2.utils.CarComparator;

public class RealGarage implements Garage {
    final HashMap<Long, Owner> ownerById;
    final HashMap<Long, Car> carById;

    final HashMap<String, List<Car>> carsByBrand;
    final HashMap<Owner,  List<Car>> carsByOwner;

    final TreeSet<Car> carsByVelocity;
    final TreeSet<Car> carsByPower;

    private void registerOwner(Owner owner) {
        ownerById.put(owner.getOwnerId(), owner);
    }

    private void registerCar(Car car) {
        carById.put(car.getCarId(), car);
    }

    private Owner findOwner(long id) {
        return ownerById.get(id);
    }

    private Car findCar(long id) {
        return carById.get(id);
    }

    public RealGarage() {
        carById = new HashMap<Long, Car>();
        ownerById = new HashMap<Long, Owner>();

        carsByBrand = new HashMap<String, List<Car>>();
        carsByOwner = new HashMap<Owner,  List<Car>>();

        carsByPower    = new TreeSet<Car>(CarComparator.BY_POWER.create());
        carsByVelocity = new TreeSet<Car>(CarComparator.BY_VELOCITY.create());
    }

    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        return carsByOwner.keySet();
    }

    @Override
    public Collection<Car>topThreeCarsByMaxVelocity() {
        return (carsByVelocity.stream().limit(3)).collect(Collectors.toList());
    }

    @Override
    public Collection<Car> allCarsOfBrand(String brand) {
        return carsByBrand.get(brand);
    }

    @Override
    public Collection<Car> carsWithPowerMoreThan(int power) {
        return carsByPower.tailSet(new Car(power));
    }

    @Override
    public Collection<Car> allCarsOfOwner(Owner owner) {
        return carsByOwner.get(owner);
    }

    @Override
    public double meanOwnersAgeOfCarBrand(String brand) {
        return allCarsOfBrand(brand).stream().mapToInt(( Car car) -> {
            return findOwner(car.getOwnerId()).getAge();
        }).average().orElse(0);
    }

    @Override
    public double meanCarNumberForEachOwner() {
        return allCarsUniqueOwners().stream().mapToInt( (Owner owner) -> {
            return carsByOwner.get(owner).size();
        }).average().orElse(0);
    }

    @Override
    public Car removeCar(long carId) {
        var car_to_remove = findCar(carId);
        carById.remove(carId);

        carsByBrand.get(car_to_remove.getBrand()).remove(car_to_remove);
        carsByOwner.get(findOwner(car_to_remove.getOwnerId())).remove(car_to_remove);

        carsByVelocity.remove(car_to_remove);
        carsByPower.remove(car_to_remove);

        return car_to_remove;
    }

    @Override
    public void addCar(Car car, Owner owner) {
        registerOwner(owner);
        registerCar(car);

        var power = car.getPower();
        var brand = car.getBrand();

        if (carsByOwner.get(owner) == null) {
            carsByOwner.put(owner, new ArrayList<Car>());
        }

        if (carsByBrand.get(brand) == null) {
            carsByBrand.put(brand, new ArrayList<Car>());
        }

        carsByBrand.get(brand).add(car);
        carsByOwner.get(owner).add(car);

        carsByVelocity.add(car);
        carsByPower.add(car);
    }
}
