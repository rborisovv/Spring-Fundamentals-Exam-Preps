package bg.softuni.shoppinglist.dao;

import bg.softuni.shoppinglist.enumeration.CategoryEnum;
import bg.softuni.shoppinglist.model.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT SUM(p.price) FROM Product p")
    Double findTotalPriceSum();

    Set<Product> findAllByCategory_Name(@NonNull CategoryEnum category_name);
}