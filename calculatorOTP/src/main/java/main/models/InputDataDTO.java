package main.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputDataDTO {
    @JsonProperty("date")
    private Date date;
    @JsonProperty("rate")
    private BigDecimal rate;
    @JsonProperty("term")
    private int term;
    @JsonProperty("amount")
    private BigDecimal amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date data) {
        this.date = date;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public InputDataDTO(String date, String rate, int term, String amount) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.date = dateFormat.parse(date);
        this.rate = new BigDecimal(rate);
        this.term = term;
        this.amount = new BigDecimal(amount);
    }
}
