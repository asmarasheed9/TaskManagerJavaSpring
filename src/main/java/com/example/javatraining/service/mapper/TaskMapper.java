package com.example.javatraining.service.mapper;

import com.example.javatraining.dto.TaskDto;
import com.example.javatraining.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto toDto(Task task);
    Task toEntity(TaskDto taskdto);
}
