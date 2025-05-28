package com.example.javatraining.service.mapper;

import com.example.javatraining.dto.UserDto;
import com.example.javatraining.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    UserDto toDto(User user);
    User toEntity(UserDto userdto);
}
