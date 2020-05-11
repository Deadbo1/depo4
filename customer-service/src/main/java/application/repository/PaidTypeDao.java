package application.repository;

import application.models.PaidType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaidTypeDao extends JpaRepository<PaidType, Long> {
    List<PaidType> findAll();

    PaidType getPaidTypeById(Long id);
}
