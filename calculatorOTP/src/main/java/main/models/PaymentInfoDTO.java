package main.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentInfoDTO {
    @JsonProperty("num")
    private int num;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("interest")
    private BigDecimal interest;
    @JsonProperty("debt")
    private BigDecimal debt;
    @JsonProperty("payment")
    private BigDecimal payment;
    @JsonProperty("debtRest")
    private BigDecimal debtRest;

    public PaymentInfoDTO(int num, Date date, BigDecimal interest, BigDecimal debt, BigDecimal payment, BigDecimal debtRest) {
        this.num = num;
        this.date = date;
        this.interest = interest;
        this.debt = debt;
        this.payment = payment;
        this.debtRest = debtRest;
    }

    public BigDecimal getDebt() {
        return debt;
    }

    public void setDebt(BigDecimal debt) {
        this.debt = debt;
    }


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDate() {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getDebtRest() {
        return debtRest;
    }

    public void setDebtRest(BigDecimal debtRest) {
        this.debtRest = debtRest;
    }

}
