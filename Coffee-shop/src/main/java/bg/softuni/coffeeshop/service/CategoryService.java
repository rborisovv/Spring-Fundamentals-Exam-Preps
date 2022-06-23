package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.dao.CategoryRepository;
import bg.softuni.coffeeshop.enumeration.CategoryEnum;
import bg.softuni.coffeeshop.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(CategoryEnum.valueOf(name));
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}