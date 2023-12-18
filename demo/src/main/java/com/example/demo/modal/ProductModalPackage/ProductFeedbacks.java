package com.example.demo.modal.ProductModalPackage;

import com.example.demo.modal.UserModalPackage.UserModal;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "productFeedback", schema = "gearvn")
public class ProductFeedbacks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_feedback;
    @Column(name = "createdAt")
    private String createdDate;
    @Column(name = "star")
    private int star;
    @Column(name = "message")
    private String message;
    @PrePersist
    public void calculateDate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
    }
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_product")
    private ProductModal productModal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_user")
    private UserModal userModal;


}
