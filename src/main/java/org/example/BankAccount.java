package org.example;

public class BankAccount {
    private final String iban;
    private final String bic;
    private final String accountHolder;

    public BankAccount(String iban, String bic, String accountHolder) {
        this.iban = iban;
        this.bic = bic;
        this.accountHolder = accountHolder;
    }
}
