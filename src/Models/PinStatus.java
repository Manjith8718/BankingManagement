package Models;
public class PinStatus {
    private int accountId;
    private int wrongAttempts;

    public PinStatus() {}

    public PinStatus(int accountId, int wrongAttempts) {
        this.accountId = accountId;
        this.wrongAttempts = wrongAttempts;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getWrongAttempts() {
        return wrongAttempts;
    }

    public void setWrongAttempts(int wrongAttempts) {
        this.wrongAttempts = wrongAttempts;
    }
}
