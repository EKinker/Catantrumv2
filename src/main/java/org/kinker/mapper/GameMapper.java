package org.kinker.mapper;

import org.kinker.entities.GameDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {

    @Mapping(target = "title", expression = "java(dto.getName())")
    @Mapping(target = "category", expression = "java(mapCategories(dto))")
    @Mapping(target = "gameMechanics", ignore = true)
    GameDetail toEntity(org.kinker.api.GameDetail dto);

    default List<String> mapCategories(org.kinker.api.GameDetail dto) {
        return dto.getGameCategories().stream()
                .map(org.kinker.api.GameDetail.AdditionalDetail::getDetailValue)
                .toList();
    }
}
