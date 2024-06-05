package com.anjaneya.spring.boot.demo.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiPathConstants {

	public static final String BASE_PATH = "/api";

	public static final String USER_API = BASE_PATH + "/user";

	public static final String CATEGORY_API = BASE_PATH + "/category";

	public static final String PRODUCT_API = BASE_PATH + "/product";

	public static final String PRODUCT_BY_CATEGORY = "/category/{categoryId}";
}
