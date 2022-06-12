package com.example.jpa.domain.repository;

import com.example.jpa.domain.vo.MemberVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberVO, Long> {

}
