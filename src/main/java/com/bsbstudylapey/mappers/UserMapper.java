package com.bsbstudylapey.mappers;

import com.bsbstudylapey.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "user.id", source = "id")
    @Mapping(target = "user.firstName", source = "first_name")
    @Mapping(target = "user.lastName", source = "last_name")
    @Mapping(target = "user.phoneNumber", source = "phone_number")
    @Mapping(target = "user.email", source = "email")
    @Mapping(target = "user.createdAt", source = "created_at")
    @Mapping(target = "user.updatedAt", source = "updated_at")
    User toUserRequest(User user);

}
