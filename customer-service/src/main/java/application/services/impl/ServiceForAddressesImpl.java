package application.services.impl;


import application.models.Customer;
import application.repository.AddressesDao;
import application.models.Addresses;
import application.repository.CustomerDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import application.services.AddressService;

import java.util.List;

@Service
public class ServiceForAddressesImpl implements AddressService {
    private final AddressesDao addressesDao;
    private final CustomerDao customerDao;

    public ServiceForAddressesImpl(AddressesDao addressesDao, CustomerDao customerDao) {
        this.addressesDao = addressesDao;
        this.customerDao = customerDao;
    }

    @Override
    public void updateAddresses(Addresses addresses) {
        addressesDao.save(addresses);
    }

    @Override
    public ResponseEntity deleteAddresses(Addresses addresses) {
        List<Customer> customers = customerDao.findAll();
        for (Customer customer : customers) {
            if (customer.getAddressId().equals(addresses))
                return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        addressesDao.delete(addresses);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public Addresses getAddressesById(Long id) {
        return addressesDao.getAddressesById(id);
    }

    @Override
    public Addresses createAddresses(Addresses addresses) {
        return addressesDao.save(addresses);
    }

    @Override
    public boolean checkAddress(Addresses addres) {
        List<Addresses> addresses = addressesDao.findAll();
        for (Addresses address : addresses) {
            if (addres.equals(address)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Addresses getCheckAddress(Addresses addres) {
        List<Addresses> addresses = addressesDao.findAll();
        for (Addresses address : addresses) {
            if (addres.equals(address)) {
                return address;
            }
        }
        return null;
    }

    @Override
    public List<Addresses> findAll() {
        return addressesDao.findAll();
    }
}
