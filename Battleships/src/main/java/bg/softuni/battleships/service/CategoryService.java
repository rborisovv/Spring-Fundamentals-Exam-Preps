package bg.softuni.battleships.service;

import bg.softuni.battleships.dao.CategoryRepository;
import bg.softuni.battleships.enums.CategoryEnum;
import bg.softuni.battleships.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(CategoryEnum.valueOf(name));
    }
}