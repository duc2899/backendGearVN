package com.example.demo.service.AccountUserServices;

import com.example.demo.DTO.AcccountDTO.*;
import com.example.demo.modal.UserModalPackage.OTPModal;
import com.example.demo.modal.UserModalPackage.UserModal;
import com.example.demo.repository.OTPRepository.OTPRepository;
import com.example.demo.repository.UsersRepository.UserRepository;
import com.example.demo.utilities.TransferUtilities;
import lombok.RequiredArgsConstructor;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AccountUserServices {

    private final UserRepository userRepository;
    private final OTPRepository otpRepository;

    @Value("${spring.mail.username}")
    private String formEmail;
    @Autowired
    private JavaMailSender javaMailSender;

    public List<AccountUserDTO> getAllUsers() {
        List<AccountUserDTO> result = new ArrayList<>();
        List<UserModal> listUsers = userRepository.findAll();
        for (UserModal userModal : listUsers) {
            result.add(TransferUtilities.toUserDTO(userModal));
        }
        return result;
    }

    public String findUserByEmail (String email){
        if(userRepository.findUserByEmailOptional(email).isPresent()){
            return "Exits user";
        }else {
            return "Not exits user";
        }
    }

    public ChangeInforUserResponseDTO getUserByID(int id) {
        UserModal userModal = userRepository.findUserByID(id);
        ChangeInforUserResponseDTO result = new ChangeInforUserResponseDTO();
        result.setEmail(userModal.getEmail());
        result.setName(userModal.getName());
        result.setPhoneNumber(userModal.getPhoneNumber());
        return result;
    }

    public String deleteUser(int id) {
        if (!isExitUser(id)) {
            userRepository.deleteById(id);
            return "success";
        } else {
            return "Not found user";
        }
    }

    public String editUser(ChangeInforUserRequestDTO changeInforUserRequestDTO) {
        if (changeInforUserRequestDTO.getId() == null) {
            return "Not found user";
        }
        if (isExitUser(changeInforUserRequestDTO.getId())) {
            return "Not found user";
        }

        UserModal getUser = userRepository.findUserByID(changeInforUserRequestDTO.getId());
        if (changeInforUserRequestDTO.getEmail() != null) {
            if (!isValidEmailAddress(changeInforUserRequestDTO.getEmail())) {
                return "Email invalid";
            }
            if (!Objects.equals(changeInforUserRequestDTO.getEmail(), getUser.getEmail())) {
                if (!isExitEmail(changeInforUserRequestDTO.getEmail(), getUser)) {
                    return "Email has been used";
                }
            }
            getUser.setEmail(changeInforUserRequestDTO.getEmail());
        }
        if (changeInforUserRequestDTO.getName() != null) {
            if (Objects.equals(changeInforUserRequestDTO.getName().trim(), "")) {
                return "Username cannot be blank";
            }
            if (checkSpace(changeInforUserRequestDTO.getName().trim())) {
                return "No spaces are allowed in the username";
            }
            if (changeInforUserRequestDTO.getName().length() < 3 || changeInforUserRequestDTO.getName().length() > 15) {
                return "Username must be 3 to 15 characters";
            }
            getUser.setName(changeInforUserRequestDTO.getName());
        }
        if (changeInforUserRequestDTO.getPhoneNumber() != null) {
            if (!isValidPhone(changeInforUserRequestDTO.getPhoneNumber().trim())) {
                return "Invalid phone number";
            }
            getUser.setPhoneNumber(changeInforUserRequestDTO.getPhoneNumber());
        }
        if (changeInforUserRequestDTO.getPassword() != null) {
            if (changeInforUserRequestDTO.getPassword().length() < 3 || changeInforUserRequestDTO.getPassword().length() > 15) {
                return "Password must be 3 to 15 characters";
            }
            getUser.setPassword(changeInforUserRequestDTO.getPassword());
        }
        if (changeInforUserRequestDTO.getIsActive() != null) {

            getUser.setIsActive(changeInforUserRequestDTO.getIsActive());
        }
        userRepository.save(getUser);
        return "success";
    }

    public String forGotPassWord(ForgotPasswordDTO forgotPasswordDTO, int expiredTime) {

        Optional<UserModal> userModal = userRepository.findUserByEmailOptional(forgotPasswordDTO.getEmail());
        if (userModal.isEmpty()) {
            return "User not found";
        }
        OTPModal findUserByEmail = otpRepository.findOTPByEmailUser(userModal.get().getId_user());
        if (otpRepository.findOTPByEmailUserOptional(userModal.get().getId_user()).isEmpty()) {
            OTPModal userOTP = new OTPModal();
            userOTP.setUserOTP(userRepository.findUserByEmail(forgotPasswordDTO.getEmail()));
            userOTP.setCode(generateRandomCode());
            userOTP.setExpirationTime(LocalDateTime.now().plus(expiredTime, ChronoUnit.MINUTES));
            otpRepository.save(userOTP);
            sendEmail(forgotPasswordDTO.getEmail(), userOTP.getCode(), expiredTime);
            return "success";
        } else {
            findUserByEmail.setCode(generateRandomCode());
            findUserByEmail.setExpirationTime(LocalDateTime.now().plus(expiredTime, ChronoUnit.MINUTES));
            sendEmail(forgotPasswordDTO.getEmail(), findUserByEmail.getCode(), expiredTime);
            otpRepository.save(findUserByEmail);
            return "success";
        }


    }

    public String checkOTP(CheckOTPDTO checkOTPDTO) {

        Optional<UserModal> userModal = userRepository.findUserByEmailOptional(checkOTPDTO.getEmail());
        if (userModal.isEmpty()) {
            return "User not found";
        }
        OTPModal otpModal = otpRepository.findOTPByUserAndCode(userModal.get().getId_user(), checkOTPDTO.getCode());
        if (otpModal == null) {
            return "Invalid OTP code";
        }
        if (!Objects.equals(otpModal.getCode(), checkOTPDTO.getCode())) {
            return "Invalid OTP code";
        }
        if (!otpModal.getExpirationTime().isAfter(LocalDateTime.now())) {
            return "OTP code has expired";
        }
        otpRepository.deleteOTPById(otpModal.getId_otp()); // delete khi user đã check mã otp thành công
        return "success";
    }

    public String changPassword(ChangePasswordRequestDTO changePasswordRequestDTO) {
        Optional<UserModal> userModal = userRepository.findUserByEmailOptional(changePasswordRequestDTO.getEmail());
        if (userModal.isEmpty()) {
            return "User not found";
        }
        if (changePasswordRequestDTO.getPassword().length() < 3 || changePasswordRequestDTO.getPassword().length() > 15) {
            return "Password must be 3 to 15 characters";
        }
        UserModal user = userRepository.findUserByEmail(changePasswordRequestDTO.getEmail());
        user.setPassword(changePasswordRequestDTO.getPassword());
        userRepository.save(user);
        return "success";
    }


    public String sendEmail(String email, String otp, int expiration) {
        try {
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, false);
            mimeMessageHelper.setFrom(formEmail);
            String body = "<html><body>Mã OTP của bạn là: <b>" + otp + "</b> <p>Lưu ý: Mã chỉ có tác dụng trong " + expiration + " phút" + "</p></body></html>";
            mimeMailMessage.setContent(body, "text/html; charset=utf-8");
            mimeMailMessage.setSubject("Xác nhận OTP");
            mimeMessageHelper.setTo(email);
            javaMailSender.send(mimeMailMessage);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return "send mail";
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

    private boolean isExitUser(int id) {
        return userRepository.findById(id).isEmpty();
    }

    private boolean isExitEmail(String email, UserModal userModal) {
        if (!Objects.equals(userModal.getEmail(), email)) {
            if (userRepository.findUserByEmailOptional(email).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private String generateRandomCode() {
        // Độ dài của mã
        int codeLength = 4;

        // Dãy ký tự có thể xuất hiện trong mã
        String characters = "0123456789ABCDEFGHJKLMNOW";

        // Tạo đối tượng Random
        Random random = new Random();

        // StringBuilder để xây dựng mã ngẫu nhiên
        StringBuilder codeBuilder = new StringBuilder();

        // Tạo mã ngẫu nhiên
        for (int i = 0; i < codeLength; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            codeBuilder.append(randomChar);
        }

        return codeBuilder.toString();
    }

}
