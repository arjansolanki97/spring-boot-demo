package com.anjaneya.spring.boot.demo.mapper;

import java.util.List;

public interface ModelMapperService {

	<T> T convertToType(Object source, Class<T> resultClass);

	<T, D> List<D> convertListEntityToListDto(List<T> list, Class<D> convertInto);
}