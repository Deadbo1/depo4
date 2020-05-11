package application.services;

import application.models.Customer;
import application.models.PaidType;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(Long id);

    Customer getCustomerByEmail(String email);

    void createCustomer(Customer customer);

    void deleteCustomer(Customer customer);

    void updateCustomer(Long id, Customer customer);

    List<Customer> findAll();

    List<PaidType> getPaidTypeByCustomerId(Long id);
}
