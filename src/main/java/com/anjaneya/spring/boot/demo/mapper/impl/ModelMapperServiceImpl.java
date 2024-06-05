package com.anjaneya.spring.boot.demo.mapper.impl;

import com.anjaneya.spring.boot.demo.mapper.ModelMapperService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ModelMapperServiceImpl implements ModelMapperService {

	ModelMapper modelMapper;

	@Override
	public <T> T convertToType(Object source, Class<T> resultClass) {
		return modelMapper.map(source, resultClass);
	}

	@Override
	public <T, D> List<D> convertListEntityToListDto(List<T> list, Class<D> convertInto) {
		return list.stream().map(e -> convertToType(e, convertInto)).toList();
	}
}