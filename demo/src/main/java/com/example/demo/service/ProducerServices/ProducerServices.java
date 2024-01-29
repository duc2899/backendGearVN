package com.example.demo.service.ProducerServices;

import com.example.demo.DTO.ProducerDTO.ProducerResponseDTO;
import com.example.demo.modal.ProducerPackage.ProducerModal;
import com.example.demo.repository.ProductRepository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerServices {
    private final ProducerRepository producerRepository;


    public List<ProducerResponseDTO> getAllProducer() {
        List<ProducerModal> producerModalList = producerRepository.findAll();
        List<ProducerResponseDTO> producerResponseDTOS = new ArrayList<>();
        for (ProducerModal item : producerModalList) {
            ProducerResponseDTO producerResponseDTO = new ProducerResponseDTO();
            producerResponseDTO.setId(item.getId_producer());
            producerResponseDTO.setName(item.getName_producer());
            producerResponseDTOS.add(producerResponseDTO);
        }
        return producerResponseDTOS;
    }
}
