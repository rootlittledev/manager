package com.project.manager.controller;

import com.project.manager.model.ProductPrice;
import com.project.manager.repository.PriceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price")
public class PriceController {
    private final PriceRepository priceRepo;

    public PriceController(PriceRepository priceRepo) {
        this.priceRepo = priceRepo;
    }

    @GetMapping("/all")
    public List<ProductPrice> allPrices(){
        return priceRepo.findAll();
    }

    @PostMapping("/add")
    public ProductPrice addPrice(@RequestBody ProductPrice price) {
        return priceRepo.save(price);
    }
}
