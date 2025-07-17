package org.tsb.demouitest.apiData.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditInfo {
    @JsonProperty("DEP_ID")
    private String depId;

    @JsonProperty("ID")
    private String id;

    @JsonProperty("NAME")
    private String name;

    @JsonProperty("STAT")
    private String status;

    @JsonProperty("STATCODE")
    private String statusCode;

    @JsonProperty("CODE")
    private String code;

    @JsonProperty("PAY_NAME")
    private String payName;

    @JsonProperty("PLANSUMPRC")
    private double planSumPrc;

    @JsonProperty("PLANSUMOD")
    private double planSumOd;

    @JsonProperty("total_debt")
    private double totalDebt;

    @JsonProperty("overdue_amount")
    private double overdueAmount;

    @JsonProperty("payment_status")
    private String paymentStatus;

    @JsonProperty("next_payment_amount")
    private double nextPaymentAmount;

    public String getDepId() { return depId; }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getStatus() { return status; }
    public String getStatusCode() { return statusCode; }
    public String getCode() { return code; }
    public String getPayName() { return payName; }
    public double getPlanSumPrc() { return planSumPrc; }
    public double getPlanSumOd() { return planSumOd; }
    public double getTotalDebt() { return totalDebt; }
    public double getOverdueAmount() { return overdueAmount; }
    public String getPaymentStatus() { return paymentStatus; }
    public double getNextPaymentAmount() { return nextPaymentAmount; }

    @Override
    public String toString() {
        return "CreditInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", totalDebt=" + totalDebt +
                ", nextPaymentAmount=" + nextPaymentAmount +
                '}';
    }
}

