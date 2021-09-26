package com.eComm.demo.order;

public class ReplacementOrder extends _Order {
    @Override
    public boolean checkOut() {
        return true;
    }
}
