package application.services.impl;

import application.models.Customer;
import application.repository.CustomerDao;
import application.repository.PaidTypeDao;
import application.models.PaidType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import application.services.PaidTypeService;

import java.util.List;

@Service
public class ServiceForPaidTypeImpl implements PaidTypeService {
    private final PaidTypeDao paidTypeDao;
    private final CustomerDao customerDao;

    public ServiceForPaidTypeImpl(PaidTypeDao paidTypeDao, CustomerDao customerDao) {
        this.paidTypeDao = paidTypeDao;
        this.customerDao = customerDao;
    }

    @Override
    public PaidType getPaidTypeById(Long id) {
        return paidTypeDao.getPaidTypeById(id);
    }

    @Override
    public List<PaidType> findAll() {
        return paidTypeDao.findAll();
    }

    @Override
    public void createPaidType(PaidType paidType) {
        paidTypeDao.save(paidType);
    }

    @Override
    public ResponseEntity deletePaidType(PaidType paidType) {
        List<Customer> customers = customerDao.findAll();
        for (Customer customer : customers) {
            if (customer.getPaidTypeList().contains(paidType))
                return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        paidTypeDao.delete(paidType);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public void updatePaidType(Long id,PaidType paidType) {
        PaidType paidType1 = getPaidTypeById(id);
        paidType1.setName(paidType.getName());
        paidTypeDao.save(paidType1);
    }
}
