package com.traini8.traini8.repositories;

import com.traini8.traini8.dtos.TrainingCenterDto;
import com.traini8.traini8.models.TrainingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingCenterRepository extends JpaRepository<TrainingCenter, String> {
    TrainingCenter findByCenterCode(Long centerCode);

}
