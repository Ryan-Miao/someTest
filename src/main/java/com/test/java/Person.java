package com.test.java;

/**
 * Created by Administrator on 2015/9/16.
 */
public class Person {
    int id;
    String name;
    int age;
    Country Country;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", Country=" + Country +
                ", hashcode=" + hashCode() +
                '}';
    }

    public Person() {
    }

    public Person(Country country, int id, String name) {
        Country = country;
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public com.test.java.Country getCountry() {
        return Country;
    }

    public void setCountry(com.test.java.Country Country) {
        this.Country = Country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (age != person.age) return false;
        return !(name != null ? !name.equals(person.name) : person.name != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    public Person(String name,int age){

        this.name=name;
        this.age=age;
    }
}

class Country{
    int code;
    String name;

    public Country() {
    }

    public Country(int code) {
        this.code = code;
    }

    public Country(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", hashcode='" + hashCode() + '\'' +
                '}';
    }
}
