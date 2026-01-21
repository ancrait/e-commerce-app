package com.sorokaandriy.order_service.repository;

import com.sorokaandriy.order_service.dto.OrderLineResponse;
import com.sorokaandriy.order_service.model.OrderLine;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {
    @Nullable List<OrderLine> findAllByOrderId(Long orderId);
}
