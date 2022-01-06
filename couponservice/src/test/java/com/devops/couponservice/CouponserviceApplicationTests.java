package com.devops.couponservice;

import com.devops.couponservice.controller.CouponRestController;
import com.devops.couponservice.model.Coupon;
import com.devops.couponservice.repo.CouponRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CouponserviceApplicationTests {

    public static final String SUPERSALE = "SUPERSALE";
    @Mock
    private CouponRepo repo;

    @InjectMocks
    private CouponRestController controller;

    @Test
    public void test_create_pass() {
        Coupon coupon = new Coupon();
        coupon.setCode("SUPERSALE");
        when(repo.save(coupon)).thenReturn(coupon);
        Coupon couponCreated = controller.create(coupon);
        verify(repo).save(coupon);
        Assertions.assertNotNull(couponCreated);
        Assertions.assertEquals(SUPERSALE, coupon.getCode());
    }

    @Test
    public void test_create_throws_exception_when_coupon_is_null() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            controller.create(null);
        });
    }

    @Test
    public void test_get_coupon_pass() {
        Coupon coupon = new Coupon();
        coupon.setId(123l);
        coupon.setCode(SUPERSALE);
        coupon.setDiscount(new BigDecimal(10));
        when(repo.findByCode(SUPERSALE)).thenReturn(coupon);
        Coupon couponResponse = controller.getCoupon(SUPERSALE);
        verify(repo).findByCode(SUPERSALE);
        Assertions.assertNotNull(couponResponse);
        Assertions.assertEquals(new BigDecimal(10), couponResponse.getDiscount());
    }

}
