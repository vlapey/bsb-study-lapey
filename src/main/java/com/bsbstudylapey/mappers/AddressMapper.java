package com.bsbstudylapey.mappers;

import com.bsbstudylapey.dto.AddressDto;
import com.bsbstudylapey.models.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto addressToDto(Address address);
    Address dtoToAddress(AddressDto addressDto);
}
