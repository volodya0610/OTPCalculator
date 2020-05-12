package main.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OutputDataDTO {
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("message")
    private String message = " ";
    @JsonProperty("data")
    private PaymentInfoDTO[] data;

    public OutputDataDTO(Boolean success, PaymentInfoDTO[] paymentInfo) {
        this.success = success;
        this.data = paymentInfo;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        if (!success)
        {
            return "Срок кредита должен быть больше 0";
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PaymentInfoDTO[] getData() {
        return data;
    }

    public void setData(PaymentInfoDTO[] data) {
        this.data = data;
    }
}
