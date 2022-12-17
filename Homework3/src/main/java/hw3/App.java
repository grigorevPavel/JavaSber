package hw3;

import java.util.*;
import java.util.stream.Collectors;

import hw3.car.Car;
import hw3.owner.Owner;
import hw3.garage.LemonGarage;
import lombok.var;


public class App {
    public static void main(String[] args) {
        System.out.println("Grigorev Pavel 912");

        var garage = new LemonGarage<Car>();

        var pavel = new Owner(0, "Pavel", "Grigorev", 21);

        var cars = Arrays.asList(new Car[] {
                new Car(0, "BMW", "5 series", 250, 192, 0),
                new Car(1, "LADA", "2105", 180, 73, 0),
        });

        garage.addCars(cars, pavel);

        List<Car> added_cars = garage.allCarsOfOwner(pavel).stream().collect(Collectors.toList());
        for (int i = 0; i < added_cars.size(); ++i) {
            assert(added_cars.get(i) == cars.get(i));
        }
    }
}
