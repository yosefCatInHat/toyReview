package com.example.toysproject.dto;

import lombok.*;

import java.util.List;

/**
 * Data Transfer Object (DTO) to represent a paginated set of toys.
 * This DTO provides details about the current page, total pages, and the toys in the current page.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ToyPageDto {

    // List of toys with their respective reviews for the current page
    private List<ToysWithToyReviewDto> results;

    // Total number of pages available
    private int totalPages;

    // Total number of toy elements across all pages
    private long totalElements;

    // Flag indicating if the current page is the last one
    private boolean isLast;

    // Current page number
    private int pageNo;

    // Number of toys per page
    private int pageSize;
}
