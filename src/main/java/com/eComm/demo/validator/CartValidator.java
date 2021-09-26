package com.eComm.demo.validator;

import com.eComm.demo.item.Item;

import java.util.HashMap;
import java.util.List;

public class CartValidator implements IValidator<List<Item>>{
    @Override
    public boolean isValid(List<Item> items, List<String>remarks) {
        boolean isValid = true;

        for (Item item:items) {
            if(!item.isAvailability()){
                remarks.add("Item: "+ item.getName() +" is not available");
                isValid = false;
            }
        }
        return isValid;
    }
}
