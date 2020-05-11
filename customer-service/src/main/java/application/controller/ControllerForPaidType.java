package application.controller;

import application.services.impl.ServiceForPaidTypeImpl;
import application.models.PaidType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerForPaidType {

    private final ServiceForPaidTypeImpl serviceForPaidTypeImpl;

    public ControllerForPaidType(ServiceForPaidTypeImpl serviceForPaidTypeImpl) {
        this.serviceForPaidTypeImpl = serviceForPaidTypeImpl;
    }

    @GetMapping("/paid_type")
    public List<PaidType> getAllPaidType() {
        return serviceForPaidTypeImpl.findAll();
    }

    @GetMapping("/paid_type/{id}")
    public PaidType getPaidTypeById(@PathVariable("id") Long id) {
        return serviceForPaidTypeImpl.getPaidTypeById(id);
    }

    @PostMapping("/paid_type")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPaidType(@RequestBody PaidType paidType) {
        serviceForPaidTypeImpl.createPaidType(paidType);
    }

    @PutMapping("/paid_type/{id}")
    public void updatePaidType(@PathVariable("id") Long id, @RequestBody PaidType paidType) {
        serviceForPaidTypeImpl.updatePaidType(id,paidType);
    }

    @DeleteMapping("/paid_type/{id}")
    public ResponseEntity deletePaidType(@PathVariable("id") Long id) {
        return serviceForPaidTypeImpl.deletePaidType(getPaidTypeById(id));
    }
}
