package application.service.impl;

import application.models.Categories;
import application.repository.CategoriesDao;
import application.service.CategoriesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceForCategoriesImpl implements CategoriesService {

    private final CategoriesDao categoriesDao;


    public ServiceForCategoriesImpl(CategoriesDao categoriesDao) {
        this.categoriesDao = categoriesDao;
    }

    @Override
    public Categories getCategoriesById(Long id) {
        return categoriesDao.getCategoriesById(id);
    }

    @Override
    public void createCategories(Categories categories) {
        categoriesDao.save(categories);
    }

    @Override
    public void deleteCategories(Categories categories) {
        categoriesDao.delete(categories);
    }

    @Override
    public void updateCategories(Long id, Categories categories) {
        Categories categories1 = getCategoriesById(id);
        categories1.setName(categories.getName());
        categoriesDao.save(categories1);
    }

    @Override
    public List<Categories> findAll() {
        return categoriesDao.findAll();
    }

    public boolean checkCategories(Categories categories) {
        List<Categories> categories1 = categoriesDao.findAll();
        for (Categories categoriess : categories1) {
            if (categories.equals(categoriess)) {
                return true;
            }
        }
        return false;
    }

    public Categories getCheckCategories(Categories categories) {
        List<Categories> categories1 = categoriesDao.findAll();
        for (Categories categoriess : categories1) {
            if (categories.equals(categoriess)) {
                return categoriess;
            }
        }
        return null;
    }
}
