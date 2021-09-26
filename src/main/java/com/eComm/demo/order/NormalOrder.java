package com.eComm.demo.order;

import com.eComm.demo.validator.CartValidator;
import com.eComm.demo.validator.PaymentValidator;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.HashMap;

@Entity
public class NormalOrder extends _Order {


    NormalOrder() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.items=new ArrayList<>();
        this.remarks=new ArrayList<>();
        this.validators = new HashMap<>();
        this.validators.put(new CartValidator(),"items");
        this.validators.put(new PaymentValidator(),"payment");
    }
    @Override
    public boolean checkOut() throws NoSuchFieldException, IllegalAccessException {
        validate();
        return validationStatus == ValidationStatus.valid;
    }
}
