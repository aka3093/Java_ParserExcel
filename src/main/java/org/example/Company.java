package org.example;

public class Company extends Employee {
    private final String companyName;
    private final CompanyType companyType;

    public Company (int id, String email, String phone, String address, String companyName, CompanyType companyType, BankAccount bankAccount) {
        super(id, email, phone, address, bankAccount);
        this.companyName = companyName;
        this.companyType = companyType;
    }
}

