import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import hw2.garage.RealGarage;
import hw2.car.Car;
import hw2.owner.Owner;

public class RealGarageTest {
    @Test
    public void allCarsOfBrandTest() {
        var garage = new RealGarage();

        var pavel = new Owner(0, "Pavel", "Grigorev", 21);

        var cars = Arrays.asList(new Car[] {
                new Car(1, "LADA", "2105", 180, 73, 0),
                new Car(2, "BMW", "5 series", 250, 192, 0)
        });

        cars.forEach((Car car) -> garage.addCar(car, pavel));
        var brand_cars = garage.allCarsOfBrand("BMW");

        Assertions.assertTrue(brand_cars.stream().anyMatch(car -> car.getBrand().equals("BMW")));
    }

    @Test
    public void allCarsOfOwnerTest() {
        var garage = new RealGarage();

        var pavel = new Owner(0, "Pavel", "Grigorev", 21);

        var cars = Arrays.asList(new Car[] {
                new Car(0, "LADA", "2105", 180, 73, 0),
                new Car(1, "BMW", "5 series", 250, 192, 0)
        });

        cars.forEach((Car car) -> garage.addCar(car, pavel));
        var owner_cars = garage.allCarsOfOwner(pavel);

        Assertions.assertTrue(cars.equals(owner_cars));
    }

    @Test
    public void allCarsUniqueOwners() {
        var owners = Arrays.asList(new Owner[] {
                new Owner(0, "Pavel", "Grigorev", 21),
                new Owner(1, "Kirill", "Musin", 19),
                new Owner(2, "Elisaveta", "Maksimova", 22)
        });

        var garage = new RealGarage();

        var car0 = new Car(0, "Porshe", "911", 330, 480, 0);
        garage.addCar(car0, owners.get(0));

        var car1 = new Car(1, "Porshe", "Macan", 250, 252, 2);
        garage.addCar(car1, owners.get(2));

        var car2 = new Car(2, "Porshe", "Panamera", 300, 340, 1);
        garage.addCar(car2, owners.get(1));

        var car3 = new Car(3, "Porshe", "Cayene", 250, 400, 0);
        garage.addCar(car3, owners.get(0));

        var owner_id_comparator = Comparator.comparingLong(Owner::getOwnerId);
        var unique_owners = garage.allCarsUniqueOwners().stream().sorted(owner_id_comparator).collect(Collectors.toList());

        Assertions.assertEquals(unique_owners, owners);
    }

    @Test
    public void carsWithPowerMoreThanTest() {
        var garage = new RealGarage();

        var pavel = new Owner(0, "Pavel", "Grigorev", 21);

        var cars = Arrays.asList(new Car[] {
                new Car(0, "LADA", "2105", 180, 73, 0),
                new Car(1, "BMW", "5 series", 250, 192, 0),
                new Car(2, "BMW", "3 series", 250, 192, 0)
        });


        cars.forEach((Car car) -> garage.addCar(car, pavel));

        var power_cars = garage.carsWithPowerMoreThan(190);
        Assertions.assertTrue(power_cars.stream().anyMatch(car -> car.getPower() > 190));
    }

    @Test
    public void topThreeCarsByMaxVelocityTest() {
        var garage = new RealGarage();

        var pavel = new Owner(0, "Pavel", "Grigorev", 21);

        var cars = Arrays.asList(new Car[] {
                new Car(0, "LADA", "2105", 180, 73, 0),
                new Car(1, "BMW", "5 series", 250, 192, 0),
                new Car(2, "Ferrari", "SF90", 350, 650, 0),
                new Car(3, "Mercedes-Benz", "E-classe", 230, 200, 0),
                new Car(4, "Volvo", "S40", 200, 250, 0)
        });

        cars.forEach((Car car) -> garage.addCar(car, pavel));

        var speed_cars = garage.topThreeCarsByMaxVelocity().stream().collect(Collectors.toList());

        Assertions.assertTrue((speed_cars.get(0)).equals(cars.get(2)));
        Assertions.assertTrue((speed_cars.get(1)).equals(cars.get(1)));
        Assertions.assertTrue((speed_cars.get(2)).equals(cars.get(3)));
    }

    @Test
    public void meanCarNumberForEachOwnerTest() {
        var owners = Arrays.asList(new Owner[] {
                new Owner(0, "Pavel", "Grigorev", 21),
                new Owner(1, "Alexander", "Grigorev", 48),
                new Owner(2, "Lisa", "Maksimova", 22),
                new Owner(3, "Kirill", "Musin", 19)
        });

        var garage = new RealGarage();

        var car_id = 0;
        var owner_id = 0;

        // Pavel
        for (var i = 0; i < 2; ++i) {
            Car car = new Car(car_id, "LADA", "2105", 180, 73, owner_id);
            garage.addCar(car, owners.get(owner_id));
            ++car_id;
        }
        ++owner_id;

        // Alexander
        for (var i = 0; i < 4; ++i) {
            Car car = new Car(car_id, "LADA", "2105", 180, 73, owner_id);
            garage.addCar(car, owners.get(owner_id));
            ++car_id;
        }
        ++owner_id;

        // Lisa
        for (var i = 0; i < 1; ++i) {
            Car car = new Car(car_id, "LADA", "2105", 180, 73, owner_id);
            garage.addCar(car, owners.get(owner_id));
            ++car_id;
        }
        ++owner_id;

        // Kirill
        for (var i = 0; i < 1; ++i) {
            Car car = new Car(car_id, "LADA", "2105", 180, 73, owner_id);
            garage.addCar(car, owners.get(owner_id));
            ++car_id;
        }
        ++owner_id;

        // total 8 cars and 4 owners => mean = 8 / 4 = 2
        Assertions.assertEquals(garage.meanCarNumberForEachOwner(), 2);
    }

    @Test
    public void meanOwnersAgeOfCarBrandTest() {
        var owners = Arrays.asList(new Owner[] {
                new Owner(0, "Pavel", "Grigorev", 21),
                new Owner(1, "Alexander", "Grigorev", 48),
                new Owner(2, "Lisa", "Maksimova", 22),
                new Owner(3, "Kirill", "Musin", 19)
        });

        var garage = new RealGarage();

        for (var i = 0; i < owners.size(); i++) {
            Car car = new Car(i, "LADA", "2105", 180, 73, i);
            garage.addCar(car, owners.get(i));
        }

        // (21 + 48 + 22 + 19) / 4 = 27.5
        Assertions.assertEquals(garage.meanOwnersAgeOfCarBrand("LADA"), 27.5, 0.001);
    }

    @Test
    public void addCarTest() {
        var garage = new RealGarage();

        var pavel = new Owner(0, "Pavel", "Grigorev", 21);

        Car car = new Car(0, "LADA", "2105", 180, 73, 0);

        garage.addCar(car, pavel);

        Assertions.assertTrue(garage.allCarsOfOwner(pavel).equals(Arrays.asList(car)));
    }

    @Test
    public void removeCarFromGarageTest() {
        var garage = new RealGarage();

        var pavel = new Owner(0, "Pavel", "Grigorev", 21);

        Car car = new Car(0, "LADA", "2105", 180, 73, 0);

        garage.addCar(car, pavel);

        Assertions.assertTrue(garage.allCarsOfOwner(pavel).equals(Arrays.asList(car)));

        garage.removeCar(0);

        Assertions.assertTrue((garage.allCarsOfOwner(pavel).size()) == 0);
    }
}
