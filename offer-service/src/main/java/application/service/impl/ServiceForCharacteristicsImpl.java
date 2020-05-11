package application.service.impl;

import application.models.Characteristics;
import application.models.Offers;
import application.repository.CharacteristicsDao;
import application.repository.OffersDao;
import application.service.CharacteristicsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceForCharacteristicsImpl implements CharacteristicsService {

    private final CharacteristicsDao characteristicsDao;
    private final OffersDao offersDao;

    public ServiceForCharacteristicsImpl(CharacteristicsDao characteristicsDao, OffersDao offersDao) {
        this.characteristicsDao = characteristicsDao;
        this.offersDao = offersDao;
    }

    @Override
    public Characteristics getCharacteristicsById(Long id) {
        return characteristicsDao.getCharacteristicsById(id);
    }

    @Override
    public void createCharacteristics(Characteristics characteristics) {
        characteristicsDao.save(characteristics);
    }

    public Characteristics findCharacteristics (Characteristics characteristics) {
        return characteristicsDao.findCharacteristicsByNameAndAndDescription(characteristics.getName(),characteristics.getDescription());
    }

    @Override
    public ResponseEntity deleteCharacteristics(Characteristics characteristics) {
        List<Offers> offers = offersDao.findAll();
        for (Offers offers1 : offers) {
            if (offers1.getCharacteristics().contains(characteristics))
                return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        characteristicsDao.delete(characteristics);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @Override
    public void updateCharacteristics(Long id, Characteristics characteristics) {
        Characteristics characteristics1 = getCharacteristicsById(id);
        characteristics1.setName(characteristics.getName());
        characteristics1.setDescription(characteristics.getDescription());
        characteristicsDao.save(characteristics1);
    }

    @Override
    public List<Characteristics> findAll() {
        return characteristicsDao.findAll();
    }

    @Override
    public boolean checkCharacteristics(List<Characteristics> characteristics) {
        List<Characteristics> characteristicsList = characteristicsDao.findAll();
        return characteristicsList.containsAll(characteristics);
    }

    @Override
    public List<Characteristics> getCheckCharacteristics(List<Characteristics> characteristics) {
        List<Characteristics> characteristicsList = new ArrayList<>();
        List<Characteristics> characteristicsList1 = characteristicsDao.findAll();
        for (Characteristics characteristics1 : characteristics) {
            for (Characteristics characteristics2 : characteristicsList1) {
                if (characteristics2.equals(characteristics1)) {
                    characteristicsList.add(characteristics2);
                }
            }
        }
        return characteristicsList;
    }
}
