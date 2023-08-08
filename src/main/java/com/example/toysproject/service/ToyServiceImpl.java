package com.example.toysproject.service;

import com.example.toysproject.dto.ToyDto;
import com.example.toysproject.dto.ToyPageDto;
import com.example.toysproject.entity.Toys;
import com.example.toysproject.error.BadRequestException;
import com.example.toysproject.error.ResourceNotFound;
import com.example.toysproject.mapper.ToysMapper;
import com.example.toysproject.repository.ToyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ToyServiceImpl is the implementation of the ToyService interface and provides methods for managing toys in the application.
 */
@Service
@RequiredArgsConstructor
public class ToyServiceImpl implements ToyService {
    private final ToyRepository toyRepository;
    private final ToysMapper toysMapper;

    @Override
    public ToyDto createToy(ToyDto dto) {
        var toys = toysMapper.toEntity(dto);
        var saved = toyRepository.save(toys);
        return toysMapper.toDto(saved);
    }

    @Override
    public List<ToyDto> getAllToys() {
        return toyRepository.findAll().stream()
                .map(toysMapper::toDto).toList();
    }

    @Override
    public ToyDto getToyById(long id) {
        return toysMapper.toDto(
                toyRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFound("Toys", "id", String.valueOf(id))
                        )

        );
    }

    @Override
    public ToyDto updateToyById(long id, ToyDto dto) {
        getToyById(id);
        if (dto.getId() == null) {
            dto.setId(id);
        }
        if (id != dto.getId()) {
            throw new BadRequestException(
                    "id",
                    "The id %s supplied must match the id field in the dto".formatted(id)
            );
        }
        var toys = toysMapper.toEntity(dto);
        var saved = toyRepository.save(toys);
        return toysMapper.toDto(saved);
    }

    @Override
    public void deleteToyById(long id) {
        getToyById(id);
        toyRepository.deleteById(id);
    }

    @Override
    public ToyPageDto getAllToys(int pageNo, int pageSize, String sortBy, String sortDir){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.fromString(sortDir), sortBy);
        Page<Toys> page = toyRepository.findAll(pageable);
        return ToyPageDto.builder()
                .results(page.getContent().stream().map(toysMapper::toDtoWithReviews).toList())
                .pageSize(page.getSize())
                .pageNo(page.getNumber())
                .totalElements(page.getTotalElements())
                .isLast(page.isLast())
                .totalPages(page.getTotalPages())
                .build();
    }
}
