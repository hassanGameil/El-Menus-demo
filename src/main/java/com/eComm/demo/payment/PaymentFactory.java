package com.eComm.demo.payment;

import org.springframework.stereotype.Component;

@Component
public class PaymentFactory {
    public Payment createPayment(PaymentType paymentType) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (Payment) ClassLoader.getSystemClassLoader().loadClass("com.eComm.demo.payment."+paymentType.name()+"Payment").newInstance();
    }
}
