package bg.softuni.coffeeshop.dao;

import bg.softuni.coffeeshop.enumeration.CategoryEnum;
import bg.softuni.coffeeshop.model.Category;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(@NonNull CategoryEnum name);
}