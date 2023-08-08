package com.example.toysproject.service;

import com.example.toysproject.dto.ToyReviewDto;
import com.example.toysproject.entity.ToyReview;
import com.example.toysproject.error.BadRequestException;
import com.example.toysproject.error.ResourceNotFound;
import com.example.toysproject.mapper.ToyReviewMapper;
import com.example.toysproject.repository.ToyRepository;
import com.example.toysproject.repository.ToyReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

/**
 * ToyReviewServiceImpl is a service class that provides functionality to manage toy reviews.
 */
@Service
@RequiredArgsConstructor
public class ToyReviewServiceImpl implements ToyReviewService {

    private final ToyReviewRepository toyReviewRepository;
    private final ToyRepository toyRepository;
    private final ToyReviewMapper toyReviewMapper;

    @Override
    public ToyReviewDto createToyReview(Long toyId, ToyReviewDto dto) {
        // Find the toy associated with the toyId or throw ResourceNotFound if not found
        var toy = toyRepository.findById(toyId).orElseThrow(
                () -> new ResourceNotFound("Toy", "id", toyId)
        );
        var toyReview = toyReviewMapper.toEntity(dto);
        toyReview.setToys(toy);
        ToyReview saved = toyReviewRepository.save(toyReview);
        // Save the comment with reference to the toy
        return toyReviewMapper.toDto(saved);
    }

    @Override
    public List<ToyReviewDto> findToyReviewByToyId(long toysId) {
        // Find all toy reviews associated with a specific toy and map them to ToyReviewDto
        List<ToyReview> toyReview = toyReviewRepository.findToyReviewByToysId(toysId);
        return toyReview.stream().map(
                toyReviewMapper::toDto
        ).toList();
    }

    @Override
    public ToyReviewDto findToyReviewById(long toyReviewId) {
        // Find a specific toy review by its ID or throw ResourceNotFound if not found
        var toyReview = toyReviewRepository.findById(toyReviewId)
                .orElseThrow(() -> new ResourceNotFound(
                        "ToyReview", "id", toyReviewId
                ));

        return toyReviewMapper.toDto(toyReview);
    }

    @Override
    public ToyReviewDto updateToyReviewById(long id, ToyReviewDto toyReviewDto) {
        // Get the original toy review from the database or throw ResourceNotFound if not found
        var toyReview = toyReviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(
                        "ToyReview", "id", id
                ));
        // Make sure the ids match:
        if (toyReviewDto.getId() == null) {
            toyReviewDto.setId(id);
        } else if (toyReviewDto.getId() != id) {
            throw new BadRequestException("ToyReview", "Id must match");
        }
        ToyReview beforeSave = toyReviewMapper.toEntity(toyReviewDto);
        // Set the toyId (required):
        beforeSave.setToys(toyReview.getToys());
        // Map the saved result:
        return toyReviewMapper.toDto(toyReviewRepository.save(beforeSave));
    }

    @Override
    public ToyReviewDto deleteToyReviewById(long id) {
        // Find a specific toy review by its ID or throw ResourceNotFound if not found
        var toyReview = toyReviewRepository.findById(id).orElseThrow(resourceNotFound(id));
        toyReviewRepository.deleteById(id);
        return toyReviewMapper.toDto(toyReview);
    }

    private Supplier<RuntimeException> resourceNotFound(long id) {
        return () -> new ResourceNotFound("ToyReview", "id", id);
    }
}
