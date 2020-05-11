package application.repository;

import application.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
    List<Customer> findAll();

    Customer getCustomerById(Long id);
    @Query("SELECT c FROM Customer c WHERE c.email=:email")
    Customer getCustomerByEmail(@Param("email") String email);
}