package com.project.manager.repository;

import com.project.manager.model.ProductPrice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PriceRepository extends CrudRepository<ProductPrice, String> {
    Optional<ProductPrice> findByName(String name);
    List<ProductPrice> findAll();
}
