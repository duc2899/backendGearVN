package com.example.demo.modal.UserModalPackage;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "otp", schema = "gearvn")
public class OTPModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_otp;
    @Column(name = "code")
    private String code;
    @Column(name = "expirationTime")
    private LocalDateTime expirationTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserModal userOTP;
}
