package com.project.manager.controller;

import com.project.manager.model.CurrencyResponse;
import com.project.manager.model.Product;
import com.project.manager.model.ProductPrice;
import com.project.manager.repository.PriceRepository;
import com.project.manager.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final ProductRepository productRepo;
    private final PriceRepository priceRepo;
    private final RestTemplate template;

    public OrderController(ProductRepository productRepo, PriceRepository priceRepo, RestTemplate template) {
        this.productRepo = productRepo;
        this.priceRepo = priceRepo;
        this.template = template;
    }


    @GetMapping("/all")
    public List<Product> getAllOrders(){
        return productRepo.findAll();
    }



    @GetMapping("/purchase")
    public Product order(@RequestParam String productName, @RequestParam String currency){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        ProductPrice price = priceRepo
                .findByName(productName)
                .orElseThrow(RuntimeException::new);

        Product product = new Product(null, productName, price.getPrice(), currency, dateFormat.format(new Date()));

        return productRepo.save(product);
    }

    @GetMapping("/report")
    public Double getReport(@RequestParam String date, @RequestParam String currency){
        return productRepo.findAllByOrderDate(date).stream()
                .mapToDouble(product -> {
                    CurrencyResponse response = template.getForObject("https://api.exchangeratesapi.io/history?start_at=2018-01-01&end_at=" + date, CurrencyResponse.class);
                    return response.getRates().get(date).get(currency).asDouble() * product.getPrice();
                })
                .sum();
    }

    @DeleteMapping()
    public void deleteAllByDate(@RequestParam String date){
        productRepo.deleteAllByOrderDate(date);
    }

}
