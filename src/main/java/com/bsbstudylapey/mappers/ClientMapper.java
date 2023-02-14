package com.bsbstudylapey.mappers;

import com.bsbstudylapey.dto.ClientDto;
import com.bsbstudylapey.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDto clientToDto(Client client);

    Client dtoToClient(ClientDto clientDto);
}
