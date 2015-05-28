package com.bangla.store.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.bangla.store.domain.Product;
import com.bangla.store.exception.ProductNotFoundException;
import com.bangla.store.service.ProductService;
/**
 *
 * @author Masudur Rahman <masud.java@gmail.com>
 */
public class ProductIdValidator implements
		ConstraintValidator<ProductId, String> {
	@Autowired
	private ProductService productService;

	public void initialize(ProductId constraintAnnotation) {
		// intentionally left blank; this is the place to initialize the
		// constraint annotation for any sensible default values.
	}

//	public boolean isValid(String value, ConstraintValidatorContext context) {
//		Product product;
//		try {
//			product = productService.getProductById(value);
//		} catch (ProductNotFoundException e) {
//			return true;
//		}
//		if (product != null) {
//			return false;
//		}
//		return true;
//	}
//        
        
        
        @Override
public boolean isValid(String value, ConstraintValidatorContext context) {
   Product product = productService.getProductById(value);
    if (product != null) {
        context.disableDefaultConstraintViolation();
        context
            .buildConstraintViolationWithTemplate("Product " + value + "already exists!")
            .addConstraintViolation();
        return false;
    }
    return true;
}
}