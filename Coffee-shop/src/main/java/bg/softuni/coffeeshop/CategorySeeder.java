package bg.softuni.coffeeshop;

import bg.softuni.coffeeshop.dao.CategoryRepository;
import bg.softuni.coffeeshop.enumeration.CategoryEnum;
import bg.softuni.coffeeshop.model.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;

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
                    .forEach(category -> {
                        switch (category.getName().name().toLowerCase(Locale.ROOT)) {
                            case "drink" -> category.setNeededTime(1);
                            case "coffee" -> category.setNeededTime(2);
                            case "other" -> category.setNeededTime(5);
                            case "cake" -> category.setNeededTime(10);
                        }
                        categoryRepository.save(category);
                    });
        }
    }
}
