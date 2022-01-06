package com.devops.productservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devops.productservice.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
