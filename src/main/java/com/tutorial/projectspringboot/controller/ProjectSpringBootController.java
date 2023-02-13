package com.tutorial.projectspringboot.controller;

import com.tutorial.projectspringboot.bean.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectSpringBootController {


    //url - localhost:8080/products
    @GetMapping("/products")
    public List<Product> getProducts(){
        List<Product> productList = new ArrayList();
        productList.add(new Product(1, "Ladder", "LaddersInc", 50, 5));
        productList.add(new Product(2, "Hammer", "HammersInc", 10, 8));

        return productList;
    }

    //url - localhost:8080/product/{id}
    @GetMapping("/product/{id}")
    public Product getProductByID(@PathVariable String id){

        try{
            Integer.valueOf(id);
        } catch (NumberFormatException nfe){
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[INVALID ID VALUE]"+nfe.getLocalizedMessage());
        }

        if(Integer.valueOf(id) == 1){
            return new Product(1, "Ladder", "LaddersInc", 50, 5);
        } else if (Integer.valueOf(id) == 2) {
            return new Product(2, "Hammer", "HammersInc", 10, 8);
        }

        //Product not found

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[Product not found] Product with ID "+id+" not found.");
    }


}
