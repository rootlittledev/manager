package com.project.manager.repository;

import com.project.manager.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findAllByOrderDate(String date);
    void deleteAllByOrderDate(String date);
}
