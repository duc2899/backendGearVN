package com.example.demo.modal.BillModalPackage;

import com.example.demo.modal.UserModalPackage.UserModal;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bills", schema = "gearvn")
public class BillModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBill;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "createdAt")
    private String createdDate;

    @PrePersist
    public void calculateDate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
    }

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private SexType sexType;

    @Column(name = "isPay")
    private Boolean isPay;

    @Column(name = "priceDelivery")
    private int priceDelivery;

    @Column(name = "statusOrder")
    private int statusOrder;

    @Column(name = "priceTemporary")
    private double priceTemporary;

    @Column(name = "priceDiscount")
    private double priceDiscount;

    @Column(name = "isCancelOrder")
    private Boolean isCancelOrder;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private UserModal userBill;

    @OneToMany(mappedBy = "orderBill", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<OrderModal> orderProduct = new HashSet<>();

}
