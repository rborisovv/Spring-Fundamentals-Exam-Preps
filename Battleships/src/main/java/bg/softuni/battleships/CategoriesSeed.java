package bg.softuni.battleships;

import bg.softuni.battleships.dao.CategoryRepository;
import bg.softuni.battleships.enums.CategoryEnum;
import bg.softuni.battleships.model.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategoriesSeed implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    public CategoriesSeed(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryEnum.values())
                    .map(Category::new)
                    .forEach(categoryRepository::save);
        }
    }
}