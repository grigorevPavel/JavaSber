package hw1.client;

class IndividualClient implements Client {
    final String name;
    final Integer inn;

    @Override
    public Type getType() {
        return Type.INDIVIDUAL;
    }

    public IndividualClient(String name, Integer inn) {
        this.name = name;
        this.inn = inn;
    }

    @Override
    public void introduce() {
        System.out.printf("Individual Client, name: <<%s>>, inn: %d\n", name, inn);
    }
}