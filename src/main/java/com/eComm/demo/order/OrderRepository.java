package com.eComm.demo.order;


import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<_Order, Long> {
}
