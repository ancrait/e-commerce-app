package com.sorokaandriy.order_service.repository;

import com.sorokaandriy.order_service.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {
}
