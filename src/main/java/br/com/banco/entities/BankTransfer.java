package br.com.banco.entities;

public class BankTransfer {
    private int id;
    private String createdAt;
    private double value;
    private String type;
    private String transactionOperatorName;
    private int accountId;

    public int getId() {
        return id;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public double getValue() {
        return value;
    }
    public String getType() {
        return type;
    }
    public String getTransactionOperatorName() {
        return transactionOperatorName;
    }
    public int getAccountId() {
        return accountId;
    }
}
