package org.kinker.mapper;

import org.kinker.api.GameDetail;
import org.kinker.entities.BggItemDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GameMapper {

    @Mapping(target = "title", expression = "java(dto.getName())")
    @Mapping(target = "category", expression = "java(mapCategories(dto))")
    @Mapping(target = "gameMechanics", ignore = true)
    BggItemDetail toEntity(GameDetail dto);

    default List<String> mapCategories(GameDetail dto) {
        return dto.getGameCategories().stream()
                .map(GameDetail.AdditionalDetail::getDetailValue)
                .toList();
    }
}
