package com.eComm.demo.order;

import com.eComm.demo.item.ItemRepository;
import com.eComm.demo.payment.PaymentFactory;
import com.eComm.demo.payment.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class OrderController {

    @Autowired
    OrderFactory orderFactory;
    @Autowired
    PaymentFactory paymentFactory;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;

    @PostMapping("/order")
    public ResponseEntity<Object> addOrder(@RequestBody Iterable<Long> itemIds) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        _Order order = orderFactory.createOrder(OrderType.Normal);
        order.payment=paymentFactory.createPayment(PaymentType.Cash);
        for (Long itemId:itemIds) {
            order.addItem(itemRepository.findById(itemId).get(),1);
        }
        if(order.checkOut()) {
            orderRepository.save(order);
            return new ResponseEntity<Object>(order.items,HttpStatus.ACCEPTED);
        }
        else
        return new ResponseEntity<Object>(order.remarks, HttpStatus.NOT_ACCEPTABLE);


    }

}
