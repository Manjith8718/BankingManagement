package Models;

import java.time.LocalDateTime;

public class FraudTransaction {

    private int fraudId;
    private int accountId;
    private int userId;
    private String alertType;
    private String fraudDescription;
    private String severity;
    private String fraudsStatus;
    private boolean emailed;
    private LocalDateTime createdAt;


    public FraudTransaction() {}


    public FraudTransaction(int fraudId, int accountId, int userId, String alertType,
                            String fraudDescription, String severity, String fraudsStatus,
                            boolean emailed, LocalDateTime createdAt) {
        this.fraudId = fraudId;
        this.accountId = accountId;
        this.userId = userId;
        this.alertType = alertType;
        this.fraudDescription = fraudDescription;
        this.severity = severity;
        this.fraudsStatus = fraudsStatus;
        this.emailed = emailed;
        this.createdAt = createdAt;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getFraudDescription() {
        return fraudDescription;
    }

    public void setFraudDescription(String fraudDescription) {
        this.fraudDescription = fraudDescription;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getFraudsStatus() {
        return fraudsStatus;
    }

    public void setFraudsStatus(String fraudsStatus) {
        this.fraudsStatus = fraudsStatus;
    }

    public boolean getEmailed() {
        return emailed;
    }

    public void setEmailed(boolean emailed) {
        this.emailed = emailed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

