package Models;
public class Account {
    private int accountId;
    private int userId;
    private long accountNumber;
    private int balance;
    private String pin;
    private String status;
    private String accountType;

    public Account() {}

    public Account(int accountId, int userId, Long accountNumber, int balance, String pin, String status, String accountType) {
        this.accountId = accountId;
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.pin = pin;
        this.status = status;
        this.accountType = accountType;
    }

    public Account(int userId, long accountNumber,String pin,String accountType) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.accountType = accountType;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}