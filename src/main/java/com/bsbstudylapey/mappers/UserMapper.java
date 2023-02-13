package com.bsbstudylapey.mappers;

import com.bsbstudylapey.dto.UserDto;
import com.bsbstudylapey.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToDto(User user);

    User dtoToUser(UserDto userDto);
}
