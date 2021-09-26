package com.eComm.demo.order;

import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
    public _Order createOrder(OrderType orderType) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (_Order) ClassLoader.getSystemClassLoader().loadClass("com.eComm.demo.order."+orderType.name()+"Order").newInstance();
    }
}
