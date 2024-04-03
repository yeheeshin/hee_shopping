package com.shopping.hee.domain;

import com.shopping.hee.domain.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.IOException;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oseq;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate odate;
    private int total;
    private String pName;
    private String pImg;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mseq")
    private Member member;

    public static Orders createOrder(Address address, int total, Item item, Member member) throws IOException {
        Orders orders = new Orders();

        orders.setOdate(LocalDate.now());
        orders.setTotal(total);
        orders.setPName(item.getName());
        orders.setPImg(item.getImg());
        orders.setAddress(address);
        orders.setMember(member);
        orders.setStatus(OrderStatus.PAYMENT_COMPLETED);

        return orders;
    }
}
