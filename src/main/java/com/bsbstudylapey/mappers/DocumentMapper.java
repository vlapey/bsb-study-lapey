package com.bsbstudylapey.mappers;

import com.bsbstudylapey.dto.DocumentDto;
import com.bsbstudylapey.models.Document;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    DocumentDto documentToDto(Document document);

    Document dtoToDocument(DocumentDto documentDto);
}
