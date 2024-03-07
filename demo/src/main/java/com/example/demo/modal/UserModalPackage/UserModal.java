package com.example.demo.modal.UserModalPackage;

import com.example.demo.modal.AddressNotePackage.AddressNoteModal;
import com.example.demo.modal.BillModalPackage.BillModal;
import com.example.demo.modal.CartPackage.CartModal;
import com.example.demo.modal.FeedbackPackage.FeedbackModal;
import com.example.demo.modal.ProductModalPackage.FavoriteProductModal;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Configuration
@Table(name = "users", schema = "gearvn")
public class UserModal implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_user;
    @Column(name = "name", length = 15)
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "isActive")
    private Boolean isActive = Boolean.TRUE;

    @Column(name = "isEnable")
    private Boolean isEnable = Boolean.FALSE;

    @Column(name = "verifyCode")
    private String verifyCode;

    @Column(name = "createdAt")
    private String createdDate;

    @PrePersist
    public void calculateDate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "userModal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<FeedbackModal> productFeedbacks = new HashSet<>();

    @OneToMany(mappedBy = "userCartModal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CartModal> cartModal;

    @OneToMany(mappedBy = "userAddressNoteModal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AddressNoteModal> addressNoteModals;

    @OneToMany(mappedBy = "userBill", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<BillModal> billModals;

    @OneToOne(mappedBy = "userOTP")
    private OTPModal otpModal;

    @OneToOne(mappedBy = "userRefreshToken")
    private RefreshTokenModal refreshTokenModal;

    @OneToMany(mappedBy = "userModal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FavoriteProductModal> favoriteProductModals = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
