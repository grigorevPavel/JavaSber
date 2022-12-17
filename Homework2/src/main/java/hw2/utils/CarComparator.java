package hw2.utils;

import java.util.*;

import hw2.car.Car;

public enum CarComparator {
    BY_POWER {
        @Override
        public Comparator<Car> create() {
            return new PowerComparator();
        }

    },
    BY_VELOCITY {
        @Override
        public Comparator<Car> create() {
            return new VelocityComparator();
        }

    };

    public abstract Comparator<Car> create();
}

class PowerComparator implements Comparator<Car> {
    public int compare(Car a, Car b) {
        if (a.getCarId() == b.getCarId()) {
            return 0;
        }

        var power_delta = Integer.compare(a.getPower(), b.getPower());
        return (power_delta == 0)?(1):(power_delta);
    }
}

class VelocityComparator implements Comparator<Car> {
    public int compare(Car a, Car b) {
        if (a.getCarId() == b.getCarId()) {
            return 0;
        }

        var velocity_delta = Integer.compare(b.getMaxVelocity(), a.getMaxVelocity());
        return (velocity_delta == 0)?(-1):(velocity_delta);
    }
}
