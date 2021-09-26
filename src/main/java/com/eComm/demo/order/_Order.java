package com.eComm.demo.order;

import com.eComm.demo.item.Item;
import com.eComm.demo.payment.Payment;
import com.eComm.demo.validator.IValidator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Entity
public abstract class _Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany (fetch = FetchType.LAZY)
    List<Item> items;
    @OneToOne(cascade = CascadeType.ALL)
    Payment payment;
    @Transient
    List<String> remarks;
    @Transient
    HashMap<IValidator,String> validators;
    @Column
    ValidationStatus validationStatus;

    void addItem(Item item, int quantity){
//        items.computeIfPresent(item,(key, val) -> val + quantity);
//        items.putIfAbsent(item,quantity);
        for (int idx=0 ; idx<quantity ; idx++)
            items.add(item);
        payment.setPaymentValue(payment.getPaymentValue() + item.getPrice() * quantity);

    }

    void  validate() throws NoSuchFieldException, IllegalAccessException {
        boolean isValid=true;
        for (IValidator validator:validators.keySet()) {
            Object target = new Object();
//             this.getClass().getSuperclass().getDeclaredField(validators.get(validator)).setAccessible(true);
            target = this.getClass().getSuperclass().getDeclaredField(validators.get(validator)).get(this);
            isValid =  validator.isValid(target, remarks) && isValid;
        }
        validationStatus = isValid ? ValidationStatus.valid : ValidationStatus.invalid;
    }

    public abstract boolean checkOut() throws NoSuchFieldException, IllegalAccessException;

}
