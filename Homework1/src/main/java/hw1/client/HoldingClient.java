package hw1.client;

import java.util.ArrayList;

class HoldingClient implements Client {
    final String name;
    final Integer inn;
    final ArrayList<String> parts;

    @Override
    public Type getType() {
        return Type.HOLDING;
    }

    public HoldingClient(String name, Integer inn, ArrayList<String> parts) {
        this.name = name;
        this.inn = inn;
        this.parts = parts;
    }

    @Override
    public void introduce() {
        System.out.printf("Holding Client, name: <<%s>>, inn: %d\n Includes: \n", name, inn);

        for (String part : parts ) {
            System.out.printf("\t%s\n", part);
        }
    }
}