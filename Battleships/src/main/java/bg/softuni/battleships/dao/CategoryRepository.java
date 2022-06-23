package bg.softuni.battleships.dao;

import bg.softuni.battleships.enums.CategoryEnum;
import bg.softuni.battleships.model.Category;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(@NonNull CategoryEnum name);
}