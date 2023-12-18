package com.example.demo.service.AccountUserServices;

import com.example.demo.DTO.AcccountDTO.AccountUserDTO;
import com.example.demo.DTO.AcccountDTO.ChangeInforUserRequestDTO;
import com.example.demo.DTO.AcccountDTO.ChangeInforUserResponseDTO;
import com.example.demo.modal.UserModalPackage.UserModal;
import com.example.demo.repository.UsersRepository.UserRepository;
import com.example.demo.utilities.TransferUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AccountUserServices {

    private final UserRepository userRepository;

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
        if(changeInforUserRequestDTO.getPassword() != null){
            if (changeInforUserRequestDTO.getPassword().length() < 3 || changeInforUserRequestDTO.getPassword().length() > 15) {
                return "Password must be 3 to 15 characters";
            }
            getUser.setPassword(changeInforUserRequestDTO.getPassword());
        }
        if(changeInforUserRequestDTO.getIsActive() != null){

            getUser.setIsActive(changeInforUserRequestDTO.getIsActive());
        }
        userRepository.save(getUser);
        return "success";
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


}
