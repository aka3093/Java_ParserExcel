package org.example;

public class Individual extends Employee{
    private final String firstName;
    private final String lastName;
    private final Boolean hasChildren;
    private final int age;

    public Individual(int id, String email, String phone, String address, String firstName, String lastName, Boolean hasChildren, int age, BankAccount bankAccount) {
        super(id, email, phone, address, bankAccount);
        this.firstName = firstName;
        this.lastName = lastName;
        this.hasChildren = hasChildren;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hasChildren=" + hasChildren +
                ", age=" + age +
                '}';
    }

    public int getAge() {
        return (int) age;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}
