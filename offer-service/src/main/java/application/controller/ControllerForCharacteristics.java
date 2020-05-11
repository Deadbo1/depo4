package application.controller;

import application.models.Characteristics;
import application.service.impl.ServiceForCharacteristicsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerForCharacteristics {

    private final ServiceForCharacteristicsImpl serviceForCharacteristicsImpl;

    public ControllerForCharacteristics (ServiceForCharacteristicsImpl serviceForCharacteristicsImpl) {
        this.serviceForCharacteristicsImpl = serviceForCharacteristicsImpl;
    }

    @GetMapping("/characteristics")
    public List<Characteristics> getAllCharacteristics() {
        return serviceForCharacteristicsImpl.findAll();
    }

    @GetMapping("/characteristics/{id}")
    public Characteristics getCharacteristicsById(@PathVariable("id") Long id) {
        return serviceForCharacteristicsImpl.getCharacteristicsById(id);
    }

    @PostMapping("/characteristics")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOffers(@RequestBody Characteristics characteristics) {
        serviceForCharacteristicsImpl.createCharacteristics(characteristics);
    }

    @PutMapping("/characteristics/{id}")
    public void updateOffers(@PathVariable("id") Long id, @RequestBody Characteristics characteristics) {
        serviceForCharacteristicsImpl.updateCharacteristics(id, characteristics);
    }

    @DeleteMapping("/characteristics/{id}")
    public ResponseEntity deleteOffers(@PathVariable("id") Long id) {
        return serviceForCharacteristicsImpl.deleteCharacteristics(getCharacteristicsById(id));
    }
}
