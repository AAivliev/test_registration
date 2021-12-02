package com.testproject.registration.mapper;

import com.testproject.registration.config.MappersConfig;
import com.testproject.registration.dto.UserRegistrationDto;
import com.testproject.registration.repo.entitys.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappersConfig.class)
public interface UserMapper {
  @Mapping(target = "id", ignore = true)
  User toEntity(UserRegistrationDto userRegistrationDto);
}
