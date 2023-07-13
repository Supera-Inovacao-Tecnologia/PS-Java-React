package br.com.banco.entities;

public class Account {
    private int accountId;
    private String clientName;

    public int getAccountId() {
        return accountId;
    }
    public String getClientName() {
        return clientName;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
