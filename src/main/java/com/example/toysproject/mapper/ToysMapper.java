package com.example.toysproject.mapper;

import com.example.toysproject.dto.ToyDto;
import com.example.toysproject.dto.ToysWithToyReviewDto;
import com.example.toysproject.entity.Toys;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between Toys entity and ToyDto/ToysWithToyReviewDto DTOs.
 * MapStruct will automatically implement the mapping methods during the build process.
 */
@Mapper(componentModel = "spring")
public interface ToysMapper {

    /**
     * Convert a Toys entity to a ToyDto.
     *
     * @param toys the Toys entity to be converted
     * @return the corresponding ToyDto
     */
    ToyDto toDto(Toys toys);

    /**
     * Convert a Toys entity to a ToysWithToyReviewDto DTO.
     *
     * @param toys the Toys entity to be converted
     * @return the corresponding ToysWithToyReviewDto DTO
     */
    ToysWithToyReviewDto toDtoWithReviews(Toys toys);

    /**
     * Convert a ToyDto to a Toys entity.
     *
     * @param toyDto the ToyDto to be converted
     * @return the corresponding Toys entity
     */
    Toys toEntity(ToyDto toyDto);
}
