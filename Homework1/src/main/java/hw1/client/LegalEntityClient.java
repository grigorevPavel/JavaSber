package hw1.client;

class LegalEntityClient implements Client {
    final String name;
    final Integer inn;
    final String owner;

    @Override
    public Type getType() {
        return Type.LEGAL_ENTITY;
    }

    public LegalEntityClient(String name, Integer inn, String owner) {
        this.name = name;
        this.inn = inn;
        this.owner = owner;
    }

    @Override
    public void introduce() {
        System.out.printf("Legal Entity Client, name: <<%s>>, number %d, owner: <<%s>>\n", name, inn, owner);
    }
}