package application.controller;


import application.models.PaidType;
import application.services.impl.ServiceForCustomersImpl;
import application.models.Customer;
import application.models.AuAnswer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerForCustomer {

    private final ServiceForCustomersImpl serviceForCustomersImpl;
    private static String k;

    public ControllerForCustomer(ServiceForCustomersImpl serviceForCustomersImpl) {
        this.serviceForCustomersImpl = serviceForCustomersImpl;
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return serviceForCustomersImpl.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) {
        return serviceForCustomersImpl.getCustomerById(id);
    }

    @GetMapping("/customer/getCustomerByEmail/{email}")
    public Customer getCustomerByEmail(@PathVariable("email") String email) {
        return serviceForCustomersImpl.getCustomerByEmail(email);
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody Customer customer) {
        serviceForCustomersImpl.createCustomer(customer);
    }

    @PutMapping("/customer/{id}")
    public void updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        serviceForCustomersImpl.updateCustomer(id,customer);
    }

    @DeleteMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("id") Long id) {
        serviceForCustomersImpl.deleteCustomer(getCustomerById(id));
    }

    @GetMapping("/customer/getTypes/{id}")
    public List<PaidType> getPaidTypeByCustomerId (@PathVariable("id") Long id){
        return serviceForCustomersImpl.getPaidTypeByCustomerId(id);
    }

    @GetMapping("/customer/checkCustomerByEP/{email}/{password}")
    public Customer checkCustomer (@PathVariable String email,@PathVariable String password) {
        return serviceForCustomersImpl.checkCustomer(email, password);
    }

    @PostMapping("/customer/login")
    public String login(@RequestBody AuAnswer authAnswer) {
        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity("http://localhost:8300/login", authAnswer, String.class);
        k = responseEntity.getBody();
        return k;
    }

    @GetMapping("/customer/getIdByToken")
    public Long getIdByToken() {
        return serviceForCustomersImpl.getIdByToken(k);
    }
}
