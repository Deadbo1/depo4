package application.repository;

import application.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusDao extends JpaRepository<Status,Long> {

    List<Status> findAll();

    Status getStatusById(Long id);
}
