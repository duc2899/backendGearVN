package com.example.demo.utilities;


import com.example.demo.DTO.AcccountDTO.AddressNoteDTO.*;
import com.example.demo.modal.AddressNotePackage.AddressNoteModal;
import com.example.demo.modal.AddressNotePackage.CityModal;
import com.example.demo.modal.AddressNotePackage.DistrictModal;
import com.example.demo.modal.AddressNotePackage.WardModal;
import com.example.demo.repository.AddressNoteRepository.AddressNoteRepository;
import com.example.demo.repository.AddressNoteRepository.CityRepository;
import com.example.demo.repository.AddressNoteRepository.DistrictRepository;
import com.example.demo.repository.AddressNoteRepository.WardRepository;
import com.example.demo.repository.UsersRepository.UserRepository;
import org.springframework.beans.BeanUtils;

public class TransferAddressNote {
    public TransferAddressNote() {

    }

    public static AddressNoteResponseDTO toAddressResponseDTO(AddressNoteModal addressNoteModal) {
        AddressNoteResponseDTO addressNoteResponseDTO = new AddressNoteResponseDTO();
        addressNoteResponseDTO.setHomeAddress(addressNoteModal.getHomeAddress());
        addressNoteResponseDTO.setNameCustomer(addressNoteModal.getNameCustomer());
        addressNoteResponseDTO.setPhoneNumber(addressNoteModal.getPhoneNumber());

        CityResponseDTO cityResponseDTO = new CityResponseDTO();
        cityResponseDTO.setCode(addressNoteModal.getCityModal().getCode());
        cityResponseDTO.setName(addressNoteModal.getCityModal().getName());
        cityResponseDTO.setId(addressNoteModal.getCityModal().getId_city());

        DistrictResponseDTO districtResponseDTO = new DistrictResponseDTO();
        districtResponseDTO.setCode(addressNoteModal.getDistrictModal().getCode());
        districtResponseDTO.setName(addressNoteModal.getDistrictModal().getName());
        districtResponseDTO.setId(addressNoteModal.getDistrictModal().getId_district());

        WardResponseDTO wardResponseDTO = new WardResponseDTO();
        wardResponseDTO.setCode(addressNoteModal.getWardModal().getCode());
        wardResponseDTO.setName(addressNoteModal.getWardModal().getName());
        wardResponseDTO.setId(addressNoteModal.getWardModal().getId_ward());

        addressNoteResponseDTO.setId(addressNoteModal.getId_addressNote());
        addressNoteResponseDTO.setCity(cityResponseDTO);
        addressNoteResponseDTO.setDistrict(districtResponseDTO);
        addressNoteResponseDTO.setWard(wardResponseDTO);
        return addressNoteResponseDTO;
    }

    public static AddressNoteModal toAddressNoteModal(AddressNoteRequestDTO addressNoteRequestDTO, UserRepository userRepository) {
        AddressNoteModal addressNoteModal = new AddressNoteModal();
        CityModal cityModal = new CityModal();
        cityModal.setCode(addressNoteRequestDTO.getCity().getCode());
        cityModal.setName(addressNoteRequestDTO.getCity().getName());

        DistrictModal districtModal = new DistrictModal();
        districtModal.setCode(addressNoteRequestDTO.getDistrict().getCode());
        districtModal.setName(addressNoteRequestDTO.getDistrict().getName());

        WardModal wardModal = new WardModal();
        wardModal.setCode(addressNoteRequestDTO.getWard().getCode());
        wardModal.setName(addressNoteRequestDTO.getWard().getName());

        addressNoteModal.setCityModal(cityModal);
        addressNoteModal.setDistrictModal(districtModal);
        addressNoteModal.setWardModal(wardModal);
        addressNoteModal.setUserAddressNoteModal(userRepository.findUserByID(addressNoteRequestDTO.getIdUser()));

        addressNoteModal.setHomeAddress(addressNoteRequestDTO.getHomeAddress());
        addressNoteModal.setNameCustomer(addressNoteRequestDTO.getNameCustomer());
        addressNoteModal.setPhoneNumber(addressNoteRequestDTO.getPhoneNumber());

        return addressNoteModal;
    }

    public static AddressNoteModal toAddressNoteModal(EditAddressNoteRequestDTO editAddressNoteRequestDTO, AddressNoteRepository addressNoteRepository, CityRepository cityRepository, DistrictRepository districtRepository, WardRepository wardRepository, UserRepository userRepository) {
        AddressNoteModal addressNoteModal = addressNoteRepository.findAddressNoteById(editAddressNoteRequestDTO.getIdAddressNote());

        CityModal cityModal = cityRepository.findCityById(editAddressNoteRequestDTO.getCity().getId());
        cityModal.setId_city(editAddressNoteRequestDTO.getCity().getId());
        cityModal.setCode(editAddressNoteRequestDTO.getCity().getCode());
        cityModal.setName(editAddressNoteRequestDTO.getCity().getName());

        DistrictModal districtModal = districtRepository.findDistrictById(editAddressNoteRequestDTO.getDistrict().getId());
        districtModal.setId_district(editAddressNoteRequestDTO.getDistrict().getId());
        districtModal.setCode(editAddressNoteRequestDTO.getDistrict().getCode());
        districtModal.setName(editAddressNoteRequestDTO.getDistrict().getName());

        WardModal wardModal = wardRepository.findWardById(editAddressNoteRequestDTO.getWard().getId());
        wardModal.setId_ward(editAddressNoteRequestDTO.getWard().getId());
        wardModal.setCode(editAddressNoteRequestDTO.getWard().getCode());
        wardModal.setName(editAddressNoteRequestDTO.getWard().getName());

        addressNoteModal.setCityModal(cityModal);
        addressNoteModal.setDistrictModal(districtModal);
        addressNoteModal.setWardModal(wardModal);
        addressNoteModal.setUserAddressNoteModal(userRepository.findUserByID(editAddressNoteRequestDTO.getIdUser()));

        addressNoteModal.setHomeAddress(editAddressNoteRequestDTO.getHomeAddress());
        addressNoteModal.setNameCustomer(editAddressNoteRequestDTO.getNameCustomer());
        addressNoteModal.setPhoneNumber(editAddressNoteRequestDTO.getPhoneNumber());

        return addressNoteModal;
    }
}
