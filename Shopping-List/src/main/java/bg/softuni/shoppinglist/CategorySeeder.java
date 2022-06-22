package bg.softuni.shoppinglist;

import bg.softuni.shoppinglist.dao.CategoryRepository;
import bg.softuni.shoppinglist.enumeration.CategoryEnum;
import bg.softuni.shoppinglist.model.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategorySeeder implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    public CategorySeeder(CategoryRepository categoryRepository) {
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