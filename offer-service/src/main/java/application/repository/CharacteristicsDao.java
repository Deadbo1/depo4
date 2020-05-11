package application.repository;

import application.models.Characteristics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacteristicsDao extends JpaRepository<Characteristics, Long> {

    List<Characteristics> findAll();

    Characteristics getCharacteristicsById(Long id);

    @Query("SELECT c FROM Characteristics c WHERE c.name =:name AND c.description =:description")
    Characteristics findCharacteristicsByNameAndAndDescription (@Param("name") String name, @Param("description") String description);
}
