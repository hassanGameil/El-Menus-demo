package com.eComm.demo.validator;

import java.util.List;

public interface IValidator<T> {
     boolean isValid(T entity, List<String> remarks);
}
