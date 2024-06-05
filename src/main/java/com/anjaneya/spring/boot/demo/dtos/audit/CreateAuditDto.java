package com.anjaneya.spring.boot.demo.dtos.audit;

import com.anjaneya.spring.boot.demo.dtos.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuditDto {

	private OffsetDateTime createdOn;

	private UserDto createdBy;

}