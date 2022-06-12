package com.example.jpa.domain.repository;

import com.example.jpa.domain.vo.OrderVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderVO, Long> {
//    void deleteByProductNumber(Long productNumber);
}
