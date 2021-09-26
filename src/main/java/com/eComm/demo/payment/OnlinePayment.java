package com.eComm.demo.payment;

public class OnlinePayment extends Payment{
    OnlinePayment(){
        this.setPaymentType(PaymentType.Online);
    }
}
