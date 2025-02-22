package edu.miu.springdatademo.Dto;

import edu.miu.springdatademo.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private int id;
    private String name;
    private List<Product> products;
}
