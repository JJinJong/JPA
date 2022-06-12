package com.example.jpa.domain.repository;

import com.example.jpa.domain.vo.MemberVO;
import com.example.jpa.domain.vo.ProductVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductVO, Long> {

}
