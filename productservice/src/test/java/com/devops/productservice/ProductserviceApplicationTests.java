package com.devops.productservice;

import com.devops.productservice.dto.Coupon;
import com.devops.productservice.model.Product;
import com.devops.productservice.repos.ProductRepo;
import com.devops.productservice.restController.ProductRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductserviceApplicationTests {

	public static final String COUPONAPI_COUPONS = "http://localhost:9091/couponapi/coupons/";
	public static final String SUPERSALE = "SUPERSALE";
	@Mock
	private RestTemplate template;

	@Mock
	private ProductRepo repo;

	@InjectMocks
	private ProductRestController restController;

	@Test
	public void contextLoads() {
		Coupon coupon = new Coupon();
		coupon.setCode(SUPERSALE);
		coupon.setDiscount(new BigDecimal(10));

		Product product = new Product();
		product.setCouponCode(SUPERSALE);
		product.setPrice(new BigDecimal(10));

		restController.setCouponServiceURL(COUPONAPI_COUPONS);
		when(template.getForObject(COUPONAPI_COUPONS+ SUPERSALE, Coupon.class)).thenReturn(coupon);
		when(repo.save(product)).thenReturn(product);

		Product productCreated = restController.create(product);
		verify(template).getForObject(COUPONAPI_COUPONS+SUPERSALE, Coupon.class);
		verify(repo).save(product);

		Assertions.assertNotNull(productCreated);
		Assertions.assertEquals(SUPERSALE, productCreated.getCouponCode());

	}

}
