package application.repository;

import application.models.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffersDao extends JpaRepository<Offers, Long> {

    List<Offers> findAll();

    Offers getOffersById(Long id);
}
