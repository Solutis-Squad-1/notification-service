package br.com.solutis.squad1.notificationservice.mapper;

import br.com.solutis.squad1.notificationservice.dto.EmailDto;
import br.com.solutis.squad1.notificationservice.dto.EmailResponseDto;
import br.com.solutis.squad1.notificationservice.model.entity.Email;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    @Mapping(target = "id", ignore = true)
    Email dtoToEntity(EmailDto emailDto);

    EmailResponseDto toResponseDto(Email email);
}
