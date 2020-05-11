package application.repository;

import application.models.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressesDao extends JpaRepository<Addresses, Long> {
    List<Addresses> findAll();

    Addresses getAddressesById(Long id);
}
