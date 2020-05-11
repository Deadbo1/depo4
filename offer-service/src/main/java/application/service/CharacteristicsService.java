package application.service;

import application.models.Characteristics;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CharacteristicsService {

    Characteristics getCharacteristicsById(Long id);

    void createCharacteristics(Characteristics characteristics);

    ResponseEntity deleteCharacteristics(Characteristics characteristics);

    void updateCharacteristics(Long id, Characteristics characteristics);

    List<Characteristics> getCheckCharacteristics(List<Characteristics> characteristics);

    boolean checkCharacteristics(List<Characteristics> characteristics);

    List<Characteristics> findAll();
}
