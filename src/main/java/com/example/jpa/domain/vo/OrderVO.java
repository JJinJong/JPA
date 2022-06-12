package com.example.jpa.domain.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ORDER")
@SequenceGenerator(name = "SEQ_ORDER", allocationSize = 1)
@Getter
@ToString(of = {"orderNumber", "orderCount", "orderPrice"})
@NoArgsConstructor
public class OrderVO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER")
    @Column(name = "ORDER_NUMBER")
    private Long orderNumber;
    @Column(name = "ORDER_COUNT")
    private Long orderCount;
    @Column(name = "ORDER_PRICE")
    private Long orderPrice;

//    1. 방향
//     RDB에서는 FK 한 개로 양쪽 테이블 조인이 가능하다.
//     하지만 JAVA에서는 해당 필드에 있는 객체를 통해서만 참조할 수 있다.
//     예를 들어, A와 B객체 중 A필드에 B가 있다면 A에서만 B를 참조할 수 있다.
//     따라서, 두 객체 사이에 하나의 객체만 필드에 있다면 단방향 관계,
//     두 객체 모두 각각의 객체가 필드에 있다면 양방향 관계라고한다.
//     사실상 양방향 관계보다는 서로 단방향 관계를 맺고 있기 때문에 각각 단방향이지만, 편의상 양방향이라고 부른다.

//     무조건 양방향으로 설계하면 쉽게 개발할 수는 있으나,
//     쓸데 없이 양방향으로 연결되면 엄청 많은 엔티티와 연관관계를 갖게되므로 굉장히 복잡해진다.
//     따라서 단방향 매핑으로 먼저 설계해 놓고, 역방향이 필요할 시 양방향으로 변경하면 된다.

//     양방향으로 관계를 설정하면, 양방향에서의 연관관계 주인이 필요하다.
//     연관관계 주인은 객체 서로 간의 CRUD가 모두 가능하지만, 주인이 아니라면 R(조회)만 가능하다.
//     만약 연관관계의 주인이 아니라면 해당 객체에서는 mappedBy 속성을 사용하여 본인의 주인을 지정해주어야 한다.

//    2. 다중성
//     다대일(N:1)
//     한 개의 상품(1)은 여러 번 주문(N)할 수 있다.
//     한 번의 주문 당 한 개의 상품만 가질 수 있다.
//     상품 1 : 주문 N으로 표현할 수 있다.
//     이와 같은 경우에는 FK를 주문이 관리하는 형태이며, RDB는 N쪽이 무조건 FK를 갖는다.

    @ManyToOne // 다대일
    @JoinColumn(name = "PRODUCT_NUMBER")
    private ProductVO productVO;

    @ManyToOne // 다대일
    @JoinColumn(name = "MEMBER_NUMBER")
    private MemberVO memberVO;

    @Builder
    public OrderVO(Long orderNumber, Long orderCount, Long orderPrice, ProductVO productVO, MemberVO memberVO) {
        this.orderNumber = orderNumber;
        this.orderCount = orderCount;
        this.orderPrice = orderPrice;
        this.productVO = productVO;
        this.memberVO = memberVO;
    }
}



















