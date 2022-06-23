package bg.softuni.coffeeshop.dao;

import bg.softuni.coffeeshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT SUM(o.category.neededTime) FROM Order o")
    Integer totalTimeForAllOrders();
}