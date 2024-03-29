package com.example.demo.service.AuthenticateService;

import com.example.demo.DTO.AcccountDTO.*;
import com.example.demo.config.JwtService;
import com.example.demo.modal.AddressNotePackage.LoginType;
import com.example.demo.modal.UserModalPackage.RefreshTokenModal;
import com.example.demo.modal.UserModalPackage.Role;
import com.example.demo.modal.UserModalPackage.UserModal;
import com.example.demo.repository.UsersRepository.ReFreshTokenRepository;
import com.example.demo.repository.UsersRepository.UserRepository;
import com.example.demo.service.CartServices.CartServices;
import com.example.demo.utilities.GenerateRandomCode;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class AuthenticateServices {

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final CartServices cartServices;

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final ReFreshTokenRepository reFreshTokenRepository;

    @Value("${spring.mail.username}")
    private String formEmail;
    @Autowired
    private JavaMailSender javaMailSender;


    public RegisterResponseDTO register(RegisterRequestDTO request) {
        var user = UserModal.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .isActive(true)
                .isEnable(request.getType() != LoginType.NORMAL)
                .verifyCode(GenerateRandomCode.generateRandomCode(20))
                .build();
        userRepository.save(user);

        UserModal getUser = userRepository.findUserByEmail(request.getEmail());
        if (request.getType() == LoginType.NORMAL) {
            sendEmail(getUser);
        }
        return RegisterResponseDTO.builder()
                .id(getUser.getId_user())
                .name(getUser.getName())
                .createdAt(getUser.getCreatedDate())
                .email(getUser.getEmail())
                .role(getUser.getRole())
                .phoneNumber(getUser.getPhoneNumber())
                .build();
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userRepository.findUserByEmailOptional(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return LoginResponseDTO.builder()
                .accessToken(jwtToken)
                .id(user.getId_user())
                .userName(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .cart(cartServices.getCartUser(user.getId_user()))
                .type(request.getType())
                .build();
    }

    public LoginAdminResponse loginAdmin(LoginRequestDTO loginRequestDTO, boolean isCreateRefreshToken) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
        );

        var user = userRepository.findUserByEmailOptional(loginRequestDTO.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        RefreshTokenModal refreshTokenModal = new RefreshTokenModal();
        if (isCreateRefreshToken) {
            refreshTokenModal = crateReFreshToken(user.getEmail());
        } else {
            refreshTokenModal = reFreshTokenRepository.findTokenByIdUser(user.getId_user());
        }
        return LoginAdminResponse.builder()
                .id(user.getId_user())
                .name(user.getName())
                .email(user.getEmail())
                .accessToken(jwtToken)
                .refreshToken(refreshTokenModal.getToken())
                .build();
    }

    public LoginResponseDTO loginWithGoogle(LoginWithGoogleRequestDTO login) {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setType(LoginType.GOOGLE);
        if (!userRepository.existsByEmail(login.getEmail())) {
            RegisterRequestDTO registerRequestDTO = new RegisterRequestDTO();
            registerRequestDTO.setEmail(login.getEmail());
            registerRequestDTO.setName(login.getName());
            registerRequestDTO.setPassword("");
            registerRequestDTO.setPhoneNumber("");
            registerRequestDTO.setType(LoginType.GOOGLE);
            UserModal userModal = userRepository.findUserByEmail(register(registerRequestDTO).getEmail());
            loginRequestDTO.setEmail(userModal.getEmail());
            loginRequestDTO.setPassword(userModal.getPassword());
        } else {
            UserModal userModal = userRepository.findUserByEmail(login.getEmail());
            loginRequestDTO.setPassword(userModal.getPassword());
            loginRequestDTO.setEmail(userModal.getEmail());
        }
        return login(loginRequestDTO);
    }

    private RefreshTokenModal crateReFreshToken(String email) {
        RefreshTokenModal refreshTokenModal = RefreshTokenModal.builder()
                .userRefreshToken(userRepository.findUserByEmail(email))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(10800000))
                .build();
        reFreshTokenRepository.save(refreshTokenModal);
        return refreshTokenModal;
    }

    public RefreshTokenModal findByToken(String token) {
        return reFreshTokenRepository.findByToken(token);
    }

    public RefreshTokenModal verifyExpiration(RefreshTokenModal refreshTokenModal) {
        if (refreshTokenModal.getExpiryDate().compareTo(Instant.now()) < 0) {
            reFreshTokenRepository.delete(refreshTokenModal);
            return null;
        }
        return refreshTokenModal;
    }

    public String checkLogin(LoginRequestDTO request) {

        if (userRepository.findUserByEmailAndPass(request.getEmail(), request.getPassword()).isEmpty()) {
            return "Username or password are incorrect";
        }
        if (!userRepository.findUserByEmailOptional(request.getEmail()).get().getIsEnable()) {
            return "Your account has not been activated";
        }
        if (!userRepository.findUserByEmailOptional(request.getEmail()).get().getIsActive()) {
            return "Your account has been locked";
        }
        return "success";
    }

    public boolean isExitEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public GetInformationUserResponseDTO getInformationUser(String email) {
        UserModal user = userRepository.findUserByEmail(email);
        return GetInformationUserResponseDTO.builder()
                .id(user.getId_user())
                .userName(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .cart(cartServices.getCartUser(user.getId_user()))
                .build();

    }

    public String checkLoginAdmin(LoginRequestDTO requestDTO) {
        Optional<UserModal> userModal = userRepository.findUserByEmailAndPass(requestDTO.getEmail(), requestDTO.getPassword());
        if (userModal.isPresent()) {
            if (userModal.get().getRole() == Role.ADMIN) {
                return "success";
            }
            return "Invalid Account";
        }
        return "Username or Password are incorrect";
    }

    public String checkRegister(RegisterRequestDTO request) {
//        check username and password, email, phonenumber
        if (Objects.equals(request.getName().trim(), "") || Objects.equals(request.getPassword().trim(), "")) {
            return "Username and Password cannot be blank";
        }
        if (!isValidEmailAddress(request.getEmail())) {
            return "Email format is wrong";
        }
        if (!isValidPhone(request.getPhoneNumber().trim())) {
            return "Phone number format is wrong";
        }
        if (checkSpace(request.getName().trim())) {
            return "No spaces are allowed in the username";
        }
        if (request.getName().length() < 3 || request.getName().length() > 15) {
            return "Username must be 3 to 15 characters";
        }
        if (request.getPassword().length() < 3 || request.getPassword().length() > 15) {
            return "Password must be 3 to 15 characters";
        }
        if (!userRepository.findUserByEmailOptional(request.getEmail()).isEmpty()) {
            return "Email has been used";
        }
        return "success";
    }

    public String logOutAccount(LogOutAccountRequestDTO logOutAccountRequestDTO) {
        RefreshTokenModal refreshTokenModal = reFreshTokenRepository.findTokenByIdUser(logOutAccountRequestDTO.getId());
        if (refreshTokenModal == null) {
            return "User not fount to logout";
        }
        reFreshTokenRepository.delete(refreshTokenModal);
        return "success";
    }

    public String checkVerifyCode(VerifyCodeRequestDTO verifyCodeRequestDTO) {
        UserModal userModal = userRepository.findByVerifyCode(verifyCodeRequestDTO.getCode());
        if (userModal != null) {
            userModal.setIsEnable(true);
            userRepository.save(userModal);
            return "success";
        } else {
            return "Not found verifyCode";
        }
    }

    private boolean checkSpace(String userName) {
        for (int i = 0; i < userName.length(); i++) {
            char kyTu = userName.charAt(i);
            if (Character.isSpace(kyTu)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    private boolean isValidPhone(String str) {
        // regex for phonenumber vietnam
        String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        return str.matches(reg);
    }

    private void sendEmail(UserModal userModal) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false);
            mimeMessageHelper.setFrom(formEmail);
            String body = "<html><body>" +
                    "<div>" +
                    "<img src='https://dxwd4tssreb4w.cloudfront.net/image/2725bf58c911e9180b2fbe8126615df5' style='width: 200px'/>" +
                    "<p>Xin chào " + userModal.getUsername() + " Bạn đã tạo tài khoản thành công vui lòng chọn click vào link để có thể kích hoạt tài khoản</p>" +
                    "<a href='http://localhost:3000/activateAccount/" + userModal.getVerifyCode() + "'>Click To Link</a>" +
                    "</div>" + "</body></html>";
            mimeMessage.setContent(body, "text/html; charset=utf-8");
            mimeMessage.setSubject("Kích hoạt tài khoản");
            mimeMessageHelper.setTo(userModal.getEmail());
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

