package com.devops.couponservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.devops.couponservice.model.Coupon;
import com.devops.couponservice.repo.CouponRepo;

@CrossOrigin
@RestController
@RequestMapping("/couponapi")
public class CouponRestController {

	@Autowired
	CouponRepo repo;

	@PostMapping(value = "/coupons")
	public Coupon create(@RequestBody Coupon coupon) {
		if(coupon==null){
			throw new IllegalArgumentException("Coupon is required");
		}
		return repo.save(coupon);

	}

	@GetMapping(value = "/coupons/{code}")
	public Coupon getCoupon(@PathVariable("code") String code) {
		return repo.findByCode(code);

	}

}
