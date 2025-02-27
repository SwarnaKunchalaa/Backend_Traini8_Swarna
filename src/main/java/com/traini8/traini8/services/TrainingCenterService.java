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
            throw new IllegalArgumentException("Invalid Input");
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

    public TrainingCenterDto findTrainingCenterByCenterCode(Long centerCode) {
        TrainingCenter trainingCenter = trainingCenterRepository.findByCenterCode(centerCode);
        if(trainingCenter==null){
            throw new NullPointerException("Invalid input");
        }
        return convertTrainingCenterToTrainingCenterDto(trainingCenter);
    }

    public void deleteTrainingCenter(Long trainingCenterId) {
        TrainingCenter trainingCenter = trainingCenterRepository.findByCenterCode(trainingCenterId);
        if(trainingCenter==null){
            throw new NullPointerException("Invalid input");
        }
        trainingCenterRepository.delete(trainingCenter);
    }

    public TrainingCenterDto updateTrainingCenter(Long centerCode,TrainingCenterDto trainingCenterDto) {
        TrainingCenter trainingCenter = trainingCenterRepository.findByCenterCode(centerCode);
        if(trainingCenter==null){
            throw new NullPointerException("Invalid input");
        }
        if(trainingCenterDto.getCenterName()!=null)
            trainingCenter.setCenterName(trainingCenterDto.getCenterName());
        if(trainingCenterDto.getContactEmail()!=null)
            trainingCenter.setContactEmail(trainingCenterDto.getContactEmail());
        if(trainingCenterDto.getContactPhone()!=null)
            trainingCenter.setContactPhone(trainingCenterDto.getContactPhone());
        Address address = null;
        if(trainingCenterDto.getAddress()!=null) {
            address = trainingCenter.getAddress();
            if (trainingCenterDto.getAddress().getDetailedAddress() != null)
                address.setDetailedAddress(trainingCenterDto.getAddress().getDetailedAddress());
            if (address.getCity() != null)
                address.setCity(trainingCenterDto.getAddress().getCity());
            if (address.getState() != null)
                address.setState(trainingCenterDto.getAddress().getState());
            if (address.getPincode() != null)
                address.setPincode(trainingCenterDto.getAddress().getPincode());
            trainingCenter.setAddress(address);
        }
        trainingCenterRepository.save(trainingCenter);
        return convertTrainingCenterToTrainingCenterDto(trainingCenter);
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
