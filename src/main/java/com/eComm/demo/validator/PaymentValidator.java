package com.eComm.demo.validator;

import com.eComm.demo.payment.Payment;
import com.eComm.demo.payment.PaymentType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix="thresh")
public class PaymentValidator implements IValidator<Payment> {

    private double   minPayment=100;
    private double maxPayment=1500;

    @Override
    public  boolean isValid(Payment payment, List<String> remarks) {
        boolean isValid = true;
        if(payment.getPaymentValue() < getMinPayment()){
            remarks.add("Minimum Order value is not reached");
            isValid = false;
        }
        else if(payment.getPaymentValue()> getMaxPayment()) {
            remarks.add("Suspected Fraud");
            isValid = false;
        }

        if(payment.getPaymentType() == PaymentType.Online){
            //TODO
            // Integrate with Payment gateway and Validate Visa Logic here
            remarks.add("Online payment is unavailable");
            isValid = isValid;
        }

        //etc. per payment type

        return isValid;

    }


    public double getMinPayment() {
        return minPayment;
    }

    public void setMinPayment(final double minPayment) {
        this.minPayment = minPayment;
    }

    public double getMaxPayment() {
        return maxPayment;
    }

    public void setMaxPayment(final double maxPayment) {
        this.maxPayment = maxPayment;
    }


}
