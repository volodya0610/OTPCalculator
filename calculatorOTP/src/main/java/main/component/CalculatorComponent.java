package main.component;

import main.models.InputDataDTO;
import main.models.OutputDataDTO;
import main.models.PaymentInfoDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

@Component
public class CalculatorComponent {

        public OutputDataDTO calculate (InputDataDTO inputData){

        Date date = inputData.getDate();
        BigDecimal rate = inputData.getRate();
        int term = inputData.getTerm();
        BigDecimal amount = inputData.getAmount();

        if (term <= 0) {
            OutputDataDTO outputData = new OutputDataDTO(false, new PaymentInfoDTO[0]);
            return  outputData;
        }

        //процентная ставка по кредиту в месяц
        BigDecimal ratePerMonth = rate.divide(new BigDecimal(12));

        BigDecimal dividend = ratePerMonth.multiply(ratePerMonth.add(new BigDecimal(1)).pow(term));
        BigDecimal divisor = ratePerMonth.add(new BigDecimal(1)).pow(term).subtract(new BigDecimal(1));

        // расчет коэффициента аннуитета
        BigDecimal coefficient = dividend.divide(divisor,10, RoundingMode.HALF_UP);

        BigDecimal interest;
        BigDecimal debt;
        BigDecimal payment;
        BigDecimal debtRest;

        payment = coefficient.multiply(amount).setScale(2,RoundingMode.HALF_UP);;
        debtRest = amount;

        PaymentInfoDTO[] paymentInfo = new PaymentInfoDTO[term];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // расчет 1 -- i-1 платежа
        for (int num = 0;num < paymentInfo.length-1; num++) {
            interest = debtRest.multiply(ratePerMonth).setScale(2,RoundingMode.HALF_UP);;
            debt = payment.subtract(interest).setScale(2,RoundingMode.HALF_UP);;
            calendar.add(Calendar.MONTH, 1);
            date = calendar.getTime();
            debtRest = debtRest.subtract(debt).setScale(2,RoundingMode.HALF_UP);;
            paymentInfo[num] = new PaymentInfoDTO(num+1, date, interest,debt,payment,debtRest);
        }
        //расчет последнего платежа (с учетом погрешностей)
        int num = paymentInfo.length-1;
        interest = debtRest.multiply(ratePerMonth).setScale(2,RoundingMode.HALF_UP);;
        debt = payment.subtract(interest).setScale(2,RoundingMode.HALF_UP);;
        calendar.add(Calendar.MONTH, 1);
        date = calendar.getTime();
        debtRest = debtRest.subtract(debt).setScale(2,RoundingMode.HALF_UP);
        payment = payment.add(debtRest);
        debt = debt.add(debtRest);
        debtRest = debtRest.subtract(debtRest);
        paymentInfo[num] = new PaymentInfoDTO(num+1, date, interest,debt,payment,debtRest);


        OutputDataDTO outputData = new OutputDataDTO(true,paymentInfo);
        return  outputData;
    }

}
