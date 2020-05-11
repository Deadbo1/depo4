package application.services;

import application.models.Addresses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddressService {
    Addresses getCheckAddress(Addresses addresses);

    void updateAddresses(Addresses addresses);

    ResponseEntity deleteAddresses(Addresses addresses);

    Addresses getAddressesById(Long id);

    Addresses createAddresses(Addresses addresses);

    boolean checkAddress(Addresses addresses);

    List<Addresses> findAll();
}
