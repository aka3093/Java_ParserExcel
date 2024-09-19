package org.example;

public abstract class Employee {
    private final int id;
    private final String email;
    private final String phone;
    private final String address;
    private final BankAccount bankAccount;

    public Employee(int id, String email, String phone, String address, BankAccount bankAccount) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
