package com.eComm.demo.payment;

public class CashPayment extends Payment{
    CashPayment(){
        this.setPaymentType(PaymentType.Cash);
    }
}
