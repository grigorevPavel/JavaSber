package hw3.garage;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import hw3.car.Car;
import hw3.car.Vehicle;
import hw3.owner.Owner;
import hw3.utils.CarComparator;
import hw3.utils.VehicleUpgrader;
import lombok.var;

public class LemonGarage<T extends Vehicle> implements Garage<T> {
    final HashMap<Long, Owner> ownerById;
    final HashMap<Long, T> carById;

    final HashMap<String, List<T>> carsByBrand;
    final HashMap<Owner,  List<T>> carsByOwner;

    final TreeSet<T> carsByVelocity;
    final TreeSet<T> carsByPower;

    private void registerOwner(Owner owner) {
        ownerById.put(owner.getOwnerId(), owner);
    }

    private void registerCar(T car) {
        carById.put(car.getCarId(), car);
    }

    private Owner findOwner(long id) {
        return ownerById.get(id);
    }

    private T findCar(long id) {
        return carById.get(id);
    }

    public LemonGarage() {
        carById = new HashMap<Long, T>();
        ownerById = new HashMap<Long, Owner>();

        carsByBrand = new HashMap<String, List<T>>();
        carsByOwner = new HashMap<Owner,  List<T>>();

        carsByPower = new TreeSet<T>((Comparator<? super T>) CarComparator.BY_POWER.create());
        carsByVelocity = new TreeSet<T>((Comparator<? super T>) CarComparator.BY_VELOCITY.create());
    }

    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        return carsByOwner.keySet();
    }

    @Override
    public Collection<T> topThreeCarsByMaxVelocity() {
        return carsByVelocity.stream().limit(3).collect(Collectors.toList());
    }

    @Override
    public Collection<T> allCarsOfBrand(String brand) {
        return carsByBrand.get(brand);
    }

    @Override
    public Collection<T> carsWithPowerMoreThan(int power) {
        var comparable = new Vehicle(0, "", "", 0, power, 0){};
        return carsByPower.tailSet((T) comparable);
    }

    @Override
    public Collection<T> allCarsOfOwner(Owner owner) {
        return carsByOwner.get(owner);
    }

    @Override
    public double meanOwnersAgeOfCarBrand(String brand) {
        return allCarsOfBrand(brand).stream().mapToInt( ( T car) -> {
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
    public T removeCar(long carId) {
        var car = findCar(carId);
        carById.remove(carId);

        carsByBrand.get(car.getBrand()).remove(car);
        carsByOwner.get(findOwner(car.getOwnerId())).remove(car);

        carsByVelocity.remove(car);
        carsByPower.remove(car);

        return car;
    }

    public T removeCar(T car) {
        return removeCar(car.getCarId());
    }

    @Override
    public void addCar(T car, Owner owner) {
        registerOwner(owner);
        registerCar(car);

        var brand = car.getBrand();
        var power = car.getPower();

        if (carsByBrand.get(brand) == null) {
            carsByBrand.put(brand, new ArrayList<T>());
        }

        if (carsByOwner.get(owner) == null) {
            carsByOwner.put(owner, new ArrayList<T>());
        }

        carsByBrand.get(brand).add(car);
        carsByOwner.get(owner).add(car);

        carsByVelocity.add(car);
        carsByPower.add(car);
    }

    @Override
    public void addCars(List<T> cars, Owner owner) {
        for (T car: cars) {
            addCar(car, owner);
        }
    }

    @Override
    public void removeListed(List<T> cars) {
        for (T car: cars) {
            removeCar(car);
        }
    }

    @Override
    public List<Object> upgradeWith(VehicleUpgrader upgrader) {
        return (carById.values().stream()).map(upgrader::upgrade).collect(Collectors.toList());
    }

    public List<T> filterCars(Predicate<T> predicate) {
        return (carById.values().stream()).filter(predicate).collect(Collectors.toList());
    }
}
