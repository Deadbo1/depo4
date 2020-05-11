package application.services;

import application.models.PaidType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaidTypeService {
    PaidType getPaidTypeById(Long id);

    List<PaidType> findAll();

    void createPaidType(PaidType paidType);

    ResponseEntity deletePaidType(PaidType paidType);

    void updatePaidType(Long id, PaidType paidType);
}
