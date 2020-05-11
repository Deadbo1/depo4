package application.service;

import application.models.Categories;

import java.util.List;

public interface CategoriesService {

    Categories getCategoriesById(Long id);

    void createCategories(Categories categories);

    void deleteCategories(Categories categories);

    void updateCategories(Long id, Categories categories);

    List<Categories> findAll();
}
