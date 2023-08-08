package com.example.toysproject.mapper;

import com.example.toysproject.dto.ToyReviewDto;
import com.example.toysproject.entity.ToyReview;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between ToyReviewDto and ToyReview entities.
 * MapStruct will automatically implement the mapping methods during the build process.
 */
@Mapper(componentModel = "spring")
public interface ToyReviewMapper {

    /**
     * Convert a ToyReview entity to a ToyReviewDto.
     *
     * @param toyReview the ToyReview entity to be converted
     * @return the corresponding ToyReviewDto
     */
    ToyReviewDto toDto(ToyReview toyReview);

    /**
     * Convert a ToyReviewDto to a ToyReview entity.
     *
     * @param toyReviewDto the ToyReviewDto to be converted
     * @return the corresponding ToyReview entity
     */
    ToyReview toEntity(ToyReviewDto toyReviewDto);
}
