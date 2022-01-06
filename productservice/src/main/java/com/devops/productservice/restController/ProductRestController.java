package com.devops.productservice.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.devops.productservice.dto.Coupon;
import com.devops.productservice.model.Product;
import com.devops.productservice.repos.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

	@Autowired
	private ProductRepo repo;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${couponService.url}")
	private String couponServiceURL;

	public String getCouponServiceURL() {
		return couponServiceURL;
	}

	public void setCouponServiceURL(String couponServiceURL) {
		this.couponServiceURL = couponServiceURL;
	}

	@PostMapping(value = "/products")
	public Product create(@RequestBody Product product) {
		Coupon coupon = restTemplate.getForObject(getCouponServiceURL() + product.getCouponCode(), Coupon.class);
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return repo.save(product);

	}

}