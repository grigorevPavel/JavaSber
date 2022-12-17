package hw2.owner;

import lombok.Getter;

@Getter
public class Owner {
    public Owner(long owner_id_, String name_, String last_name_, int age_) {
        this.ownerId = owner_id_;
        this.name = name_;
        this.lastName = last_name_;
        this.age = age_;
    }

    private final long ownerId;
    private final String name;
    private final String lastName;
    private final int age;
}
