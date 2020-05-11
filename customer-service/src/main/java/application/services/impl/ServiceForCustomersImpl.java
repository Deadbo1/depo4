package application.services.impl;

import application.models.Customer;
import application.models.PaidType;
import application.models.TokenData;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import application.repository.CustomerDao;
import application.services.CustomerService;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ServiceForCustomersImpl implements CustomerService {

    private final CustomerDao customerDao;
    private final ServiceForAddressesImpl serviceForAddressesImpl;
    @Value("dgf32kld23")
    private String key;


    public ServiceForCustomersImpl(CustomerDao customerDao, ServiceForAddressesImpl serviceForAddressesImpl) {
        this.customerDao = customerDao;
        this.serviceForAddressesImpl = serviceForAddressesImpl;
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerDao.getCustomerByEmail(email);
    }

    @Override
    public void createCustomer(Customer customer) {
        if (!serviceForAddressesImpl.findAll().isEmpty() && serviceForAddressesImpl.checkAddress(customer.getAddressId())) {
            customer.setAddressId(serviceForAddressesImpl.getCheckAddress(customer.getAddressId()));
        } else {
            serviceForAddressesImpl.createAddresses(customer.getAddressId());
        }

        customerDao.save(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerDao.delete(customer);
    }

    @Override
    public void updateCustomer(Long id,Customer customer) {
        Customer customer1 = getCustomerById(id);
        customer1.setFirstName(customer.getFirstName());
        customer1.setLastName(customer.getLastName());
        customer1.setPaidTypeList(customer.getPaidTypeList());
        customer1.setEmail(customer.getEmail());
        customer1.setPassword(customer.getPassword());
        customer1.setPhone(customer.getPhone());
        customer1.setRole(customer.getRole());
        if (serviceForAddressesImpl.checkAddress(customer.getAddressId())) {
            customer1.setAddressId(serviceForAddressesImpl.getCheckAddress(customer.getAddressId()));
        } else {
            serviceForAddressesImpl.createAddresses(customer.getAddressId());
            customer1.setAddressId(customer.getAddressId());
        }
        customerDao.save(customer1);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public List<PaidType> getPaidTypeByCustomerId(Long id) {
        Customer customer = getCustomerById(id);
        return customer.getPaidTypeList();
    }

    public Customer checkCustomer (final String email, final String password) {
        Customer customer = getCustomerByEmail(email);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }
        throw new RuntimeException("Error");
    }

    public String getToken() {
            ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://localhost:8300/getToken", String.class);
            String response = responseEntity.getBody();
            return response;
    }


    public Long getIdByToken(String token) {
        DefaultClaims claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
       return claims.get(TokenData.ID.getValue(), Number.class).longValue();
    }


}
