package application.controller;

import application.models.Categories;
import application.service.impl.ServiceForCategoriesImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerForCategories {
    private final ServiceForCategoriesImpl serviceForCategories;

    public ControllerForCategories(ServiceForCategoriesImpl serviceForCategories) {
        this.serviceForCategories = serviceForCategories;
    }

    @GetMapping("/categories")
    public List<Categories> getAllCategories() {
        return serviceForCategories.findAll();
    }
}
