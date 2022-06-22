package bg.softuni.shoppinglist.service;

import bg.softuni.shoppinglist.dao.CategoryRepository;
import bg.softuni.shoppinglist.enumeration.CategoryEnum;
import bg.softuni.shoppinglist.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findCategoryByName(CategoryEnum name) {
        return categoryRepository.findByName(name);
    }
}
