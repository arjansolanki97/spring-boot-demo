package com.anjaneya.spring.boot.demo.utils;

import com.anjaneya.spring.boot.demo.entites.response.ApiResponseContainer;
import com.anjaneya.spring.boot.demo.entites.response.ResponseContainerEntity;
import com.anjaneya.spring.boot.demo.exceptions.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

@UtilityClass
public class ResponseUtils {

	public static void setResponse(HttpServletResponse response, String message, AtomicBoolean isException) {

		try {
			isException.set(true);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			response.setStatus(HttpStatus.UNAUTHORIZED.value());

			ResponseContainerEntity<Object> responseContainerEntity = ApiResponseContainer.getResponse(message, null, HttpStatus.UNAUTHORIZED, true);
			String json = new ObjectMapper().writeValueAsString(responseContainerEntity);
			response.getWriter().write(json);
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}
}
