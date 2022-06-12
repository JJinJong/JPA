package com.example.jpa.domain.repository;

import com.example.jpa.domain.vo.MemberVO;
import com.example.jpa.domain.vo.OrderVO;
import com.example.jpa.domain.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
@Slf4j
public class RepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

//    @Test
//    public void saveMemberTest(){
//        String id ="hgd9999";
//        String name = "홍길동";
//        String birth = "2200-08-14";
//
//        memberRepository.save(MemberVO.builder().memberId(id).memberName(name).memberBirth(birth).build());
//    }

//    상품 한 개 추가
//    @Test
//    public void saveProductTest(){
//        String name = "자동차";
//        Long price = 8000L;
//        Long stock = 10L;
//
//        productRepository.save(ProductVO.builder().productName(name).productPrice(price).productStock(stock).build());
//    }

    @Test
    public void saveOrderTest(){
        Long orderCount = 2L, orderPrice = 0L, updatedStock = 0L;

//        getById(): 결과에서 id를 조회할 때에는 SELECT문이 실행되지 않고, 다른 필드에 접근할 때에만 SELECT문이 실행된다.
//        findById(): 조회하는 필드에 상관없이 무조건 SELECT문이 실행된다.

//        getById()는 객체가 바로 리턴되며, 객체를 반드시 가져와야 하기 때문에 null일 경우 Exception이 발생한다.
//        하지만 findById()는 Optional로 return하기 때문에 null일 경우 다양한 처리가 가능하다.
//        따라서 전달한 id가 확실히 있는 id라면, getById()를 사용하고, id가 있는 지 불확실할 때에는 findById()를 사용한다.

//        ProductVO productVO = productRepository.findById(1L).get();
        ProductVO productVO = productRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        MemberVO memberVO = memberRepository.getById(1L);
        orderPrice = productVO.getProductPrice() * orderCount;

//        Tip. Long을 int로 변경할 때에는 Math클래스의 toIntExact()를 사용한다.
//        주문한 상품의 개수만큼 해당 상품의 재고를 차감해야 한다.
        updatedStock = productVO.getProductStock() - orderCount;
//        차감된 상품의 재고로 UPDATE해준다.
        productVO.updateProductStock(updatedStock);
        productRepository.save(productVO);
        orderRepository.save(OrderVO.builder().orderCount(orderCount).orderPrice(orderPrice).memberVO(memberVO).productVO(productVO).build());
    }

//    @Test
//    public void getOrdersTest(){
//        List<OrderVO> orders = orderRepository.findAll();
////        toString()사용 시 연관관계에서 무한루프가 발생하기 때문에 연관관계 객체는 toString()에서 제외해주어야 한다.
//        orders.stream().map(OrderVO::toString).forEach(log::info);
////        조회한 회원 객체와 상품 객체도 toString()을 사용해서 출력
//        orders.forEach(orderVO -> {
//            log.info(orderVO.getProductVO().toString());
//            log.info(orderVO.getMemberVO().toString());
//        });
//    }

//    @Test
//    @Transactional
//    public void getOrdersByProductTest(){
////        JPA를 사용해서 조회를 할 때에는 기본적으로 연관객체에 대해 자동으로 매핑하지 않는다.
////        getById()에서 productVO를 조회 완료했다면 persistance context가 닫혀버리고
////        필드 내의 연관관계 필드(orders) 조회가 불가능해진다.
////        이 때 메소드에서 새로운 persistance context를 생성하고 메소드 종료 시 context를 닫게 한다면
////        메소드 내에서 모든 필드의 조회가 가능해진다.
//        productRepository.getById(1L).getOrders().stream().map(OrderVO::toString).forEach(log::info);
//    }

//    @Test
//    public void updateMemberTest(){
////        findById()는 로우 주소를 그대로 적용하고, getById()는 단순 객체만 조회된다.
////        만약 수정 또는 변경 시 해당 로우 주소로 접근하기 위해서는 getById(r)가 아니라 findById(r/w)를 사용해야한다.
//        MemberVO memberVO = memberRepository.findById(2L).get();
////        memberVO.updateMemberId("love5555");
////        생년월일 수정
//        memberVO.updateMemberBirth("2020-08-04");
//        memberRepository.save(memberVO);
//    }

//    @Test
//    public void deleteOrderTest(){
//        orderRepository.deleteByProductVO(productRepository.findById(1L).get());
//    }
}













