package edu.miu.springdatademo.service.impl;

import edu.miu.springdatademo.Dto.ProductDto;
import edu.miu.springdatademo.Repository.ProductRepo;
import edu.miu.springdatademo.Repository.ReviewRepo;
import edu.miu.springdatademo.entity.Product;
import edu.miu.springdatademo.entity.Review;
import edu.miu.springdatademo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;




import org.modelmapper.ModelMapper;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ReviewRepo reviewRepo;
    private final ModelMapper modelMapper;

//    @Override
//    public List<ProductDto> findAll() {
//        return productRepo.findAll().stream().map(a -> modelMapper.map(a, ProductDto.class)).toList();
//    }

    @Override
    public ProductDto findById(int id) {
        return productRepo.findById(id).map(a -> modelMapper.map(a, ProductDto.class)).get();
    }

    @Override
    public void save(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productRepo.save(product);
    }

    @Override
    public void update(int id, ProductDto productDto) {
        productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found."));
        Product product = modelMapper.map(productDto, Product.class);
        productRepo.save(product);
    }

    @Override
    public void delete(int id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found."));
        productRepo.delete(product);
    }

    @Override
    public List<Product> findAllByPriceGreaterThan(double price) {
        return productRepo.findAllByPriceGreaterThan(price);
    }

    @Override
    public List<Product> findAllByCategoryAndPriceLessThan(String cat, double price) {
        return productRepo.findAllByCategory_NameAndPriceLessThan(cat, price);
    }

    @Override
    public List<Product> findAllByNameContainingIgnoreCase(String keyword) {
        return productRepo.findAllByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Review> findReviewsByProductId(int id) {
        return reviewRepo.findAllByProduct_Id(id);
    }
}