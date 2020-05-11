package application.repository;

import application.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesDao extends JpaRepository<Categories,Long> {

    List<Categories> findAll();

    Categories getCategoriesById(Long id);
}
