package com.traini8.traini8.services;

import com.traini8.traini8.dtos.AddressDto;
import com.traini8.traini8.dtos.TrainingCenterDto;
import com.traini8.traini8.models.Address;
import com.traini8.traini8.models.TrainingCenter;
import com.traini8.traini8.repositories.AddressRepository;
import com.traini8.traini8.repositories.TrainingCenterRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingCenterService {


    TrainingCenterRepository trainingCenterRepository;
    AddressRepository addressRepository;

    public TrainingCenterService(TrainingCenterRepository trainingCenterRepository,AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
        this.trainingCenterRepository = trainingCenterRepository;
    }


    public TrainingCenterDto createTrainingCenter(TrainingCenterDto trainingCenterDto) {
        TrainingCenter center = trainingCenterRepository.findByCenterCode(trainingCenterDto.getCenterCode());
        if(center!=null){
            throw new IllegalArgumentException();
        }
        TrainingCenter trainingCenter = new TrainingCenter();
        trainingCenter.setCenterName(trainingCenterDto.getCenterName());
        trainingCenter.setCenterCode(trainingCenterDto.getCenterCode());
        trainingCenter.setStudentCapacity(trainingCenter.getStudentCapacity());
        trainingCenter.setCreatedOn(Instant.now().getEpochSecond());
        trainingCenter.setContactEmail(trainingCenterDto.getContactEmail());
        trainingCenter.setContactPhone(trainingCenterDto.getContactPhone());

        Address address = new Address();
        address.setDetailedAddress(trainingCenterDto.getAddress().getDetailedAddress());
        address.setCity(trainingCenterDto.getAddress().getCity());
        address.setState(trainingCenterDto.getAddress().getState());
        address.setPincode(trainingCenterDto.getAddress().getPincode());
        addressRepository.save(address);
        trainingCenter.setAddress(address);


        trainingCenter.setCoursesOffered(trainingCenterDto.getCoursesOffered());

        TrainingCenter response = trainingCenterRepository.save(trainingCenter);
        return convertTrainingCenterToTrainingCenterDto(response);
    }

    public List<TrainingCenterDto> findAllTrainingCenters() {
        List<TrainingCenter> trainingCenters = trainingCenterRepository.findAll();
        List<TrainingCenterDto> trainingCenterDtos = new ArrayList<>();
        for( TrainingCenter trainingCenter : trainingCenters){
            trainingCenterDtos.add(convertTrainingCenterToTrainingCenterDto(trainingCenter));
        }
        return trainingCenterDtos;
    }

    public TrainingCenterDto convertTrainingCenterToTrainingCenterDto(TrainingCenter trainingCenter) {
        TrainingCenterDto trainingCenterDto = new TrainingCenterDto();
        trainingCenterDto.setCenterName(trainingCenter.getCenterName());
        trainingCenterDto.setCenterCode(trainingCenter.getCenterCode());
        trainingCenterDto.setStudentCapacity(trainingCenter.getStudentCapacity());
        trainingCenterDto.setCreatedOn(trainingCenter.getCreatedOn());
        trainingCenterDto.setContactEmail(trainingCenter.getContactEmail());
        trainingCenterDto.setContactPhone(trainingCenter.getContactPhone());

        AddressDto address = new AddressDto();
        address.setDetailedAddress(trainingCenter.getAddress().getDetailedAddress());
        address.setCity(trainingCenter.getAddress().getCity());
        address.setState(trainingCenter.getAddress().getState());
        address.setPincode(trainingCenter.getAddress().getPincode());
        trainingCenterDto.setAddress(address);

        trainingCenterDto.setCoursesOffered(trainingCenter.getCoursesOffered());
        return trainingCenterDto;
    }
}
