package bg.softuni.shoppinglist.dao;

import bg.softuni.shoppinglist.enumeration.CategoryEnum;
import bg.softuni.shoppinglist.model.Category;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(@NonNull CategoryEnum name);
}