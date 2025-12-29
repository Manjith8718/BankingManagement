package Models;
import java.sql.Timestamp;
public class FraudAlert {

    private int fraudId;
    private int accountId;
    private String fraudType;
    private int attempts;
    private Timestamp firstDetectedAt;
    private Timestamp lastDetectedAt;
    private String status;


    public FraudAlert() {
    }


    public FraudAlert(int accountId, String fraudType) {
        this.accountId = accountId;
        this.fraudType = fraudType;
        this.attempts = 1;
        this.status = "PENDING";
    }

    public FraudAlert(int fraudId, int accountId, String fraudType,
                      int attempts, Timestamp firstDetectedAt,
                      Timestamp lastDetectedAt, String status) {
        this.fraudId = fraudId;
        this.accountId = accountId;
        this.fraudType = fraudType;
        this.attempts = attempts;
        this.firstDetectedAt = firstDetectedAt;
        this.lastDetectedAt = lastDetectedAt;
        this.status = status;
    }


    public int getFraudId() {
        return fraudId;
    }

    public void setFraudId(int fraudId) {
        this.fraudId = fraudId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFraudType() {
        return fraudType;
    }

    public void setFraudType(String fraudType) {
        this.fraudType = fraudType;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public Timestamp getFirstDetectedAt() {
        return firstDetectedAt;
    }

    public void setFirstDetectedAt(Timestamp firstDetectedAt) {
        this.firstDetectedAt = firstDetectedAt;
    }

    public Timestamp getLastDetectedAt() {
        return lastDetectedAt;
    }

    public void setLastDetectedAt(Timestamp lastDetectedAt) {
        this.lastDetectedAt = lastDetectedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
