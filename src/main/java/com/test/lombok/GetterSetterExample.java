package com.test.lombok;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Ryan Miao on 1/18/18.
 */
public class GetterSetterExample {
    /**
     * Age of the person. Water is wet.
     *
     * @param age New value for this person's age. Sky is blue.
     * @return The current value of this person's age. Circles are round.
     */
    @Getter
    @Setter
    private int age = 10;

    @Getter
    @Setter
    private boolean active;

    @Getter
    @Setter
    private Boolean none;

    @Getter
    @Setter
    private Date date;

    /**
     * Name of the person.
     * -- SETTER --
     * Changes the name of this person.
     *
     * @param name The new value.
     */
    @Setter(AccessLevel.PROTECTED) private String name;

    @Override public String toString() {
        return String.format("%s (age: %d)", name, age);
    }

    public static void main(String[] args) {
        GetterSetterExample example = new GetterSetterExample();
        example.setActive(true);
        example.setAge(123);
        example.setDate(new Date());
        example.setName("abc");
        example.setNone(false);

        Date date = example.getDate();
        Boolean none = example.getNone();
        boolean active = example.isActive();

    }
}
