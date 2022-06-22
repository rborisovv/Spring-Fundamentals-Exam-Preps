package bg.softuni.shoppinglist.service;

import bg.softuni.shoppinglist.bindingModel.AddProductDto;
import bg.softuni.shoppinglist.bindingModel.ProductDto;
import bg.softuni.shoppinglist.dao.ProductRepository;
import bg.softuni.shoppinglist.enumeration.CategoryEnum;
import bg.softuni.shoppinglist.model.Category;
import bg.softuni.shoppinglist.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    public void addProduct(AddProductDto addProductDto) {
        Product productEntity = modelMapper.map(addProductDto, Product.class);
        Category category = categoryService.findCategoryByName(CategoryEnum.valueOf(addProductDto.getCategory()));
        productEntity.setCategory(category);
        productRepository.save(productEntity);
    }

    public Double getTotalProductsPrice() {
        return productRepository.findTotalPriceSum();
    }

    public Set<ProductDto> findProductsByCategory(CategoryEnum categoryEnum) {
        return productRepository.findAllByCategory_Name(categoryEnum)
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toSet());
    }

    public void buyProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void buyAll() {
        this.productRepository.deleteAll();
    }
}