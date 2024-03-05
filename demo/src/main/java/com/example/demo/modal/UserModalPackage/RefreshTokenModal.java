package com.example.demo.modal.UserModalPackage;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Configuration
@Table(name = "refreshToken", schema = "gearvn")
public class RefreshTokenModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String token;
    private Instant expiryDate;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user")
    private UserModal userRefreshToken;

}
