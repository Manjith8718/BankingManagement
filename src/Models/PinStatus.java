package Models;
import java.sql.Timestamp;

public class PinStatus {

    private int pinLockId;
    private int accountId;
    private int attempts;
    private Timestamp lockedAt;


    public PinStatus() {
    }


    public PinStatus(int accountId, int attempts, Timestamp lockedAt) {
        this.accountId = accountId;
        this.attempts = attempts;
        this.lockedAt = lockedAt;
    }

    public PinStatus(int pinLockId, int accountId, int attempts, Timestamp lockedAt) {
        this.pinLockId = pinLockId;
        this.accountId = accountId;
        this.attempts = attempts;
        this.lockedAt = lockedAt;
    }

    public int getPinLockId() {
        return pinLockId;
    }

    public void setPinLockId(int pinLockId) {
        this.pinLockId = pinLockId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public Timestamp getLockedAt() {
        return lockedAt;
    }

    public void setLockedAt(Timestamp lockedAt) {
        this.lockedAt = lockedAt;
    }

}
