package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.bindingModel.OrderDto;
import bg.softuni.coffeeshop.dao.OrderRepository;
import bg.softuni.coffeeshop.model.Order;
import bg.softuni.coffeeshop.session.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final UserService userService;
    private final UserSession userSession;

    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper, CategoryService categoryService, UserService userService, UserSession userSession) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.userService = userService;
        this.userSession = userSession;
    }

    public void createOrder(OrderDto orderDto) {
        Order orderEntity = modelMapper.map(orderDto, Order.class);
        String categoryName = orderDto.getCategory();
        orderEntity.setCategory(categoryService.findCategoryByName(categoryName));
        Long currUserId = userSession.getId();
        orderEntity.setEmployee(userService.findUserById(currUserId));
        orderRepository.save(orderEntity);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Order::getPrice).reversed())
                .collect(Collectors.toList());
    }

    public void orderReady(Long productId) {
        Optional<Order> optionalOrder = orderRepository.findById(productId);

        if (optionalOrder.isPresent()) {
            Order orderEntity = optionalOrder.get();
            orderRepository.delete(orderEntity);
        }
    }

    public Integer findTotalOrderTime() {
        return orderRepository.totalTimeForAllOrders();
    }
}