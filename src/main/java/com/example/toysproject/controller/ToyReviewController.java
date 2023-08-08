package com.example.toysproject.controller;

import com.example.toysproject.dto.ToyDto;
import com.example.toysproject.dto.ToyReviewDto;
import com.example.toysproject.service.ToyReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ToyReviewController {

    // Service responsible for business logic related to Toy Reviews.
    private final ToyReviewService toyReviewService;

    /**
     * Create a review for a specific toy.
     *
     * @param toysId - ID of the toy to be reviewed.
     * @param toyReviewDto - Data Transfer Object containing review details.
     * @param uriComponentsBuilder - Helper for building URIs.
     * @return ResponseEntity containing the created toy review.
     */
    @Operation(summary = "comment on a toy")
    @ApiResponses(value = {
            @ApiResponse(
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ToyDto.class))),
                    description = "Review added successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content(mediaType = "application/json"),
                    description = "UnAuthorized"
            )
    })
    @PostMapping("/toys/{id}/toyReviews")
    public ResponseEntity<ToyReviewDto> toyReview(
            @PathVariable("id") long toysId,
            @Valid @RequestBody ToyReviewDto toyReviewDto,
            UriComponentsBuilder uriComponentsBuilder) {

        var saved = toyReviewService.createToyReview(toysId, toyReviewDto);
        var uri = uriComponentsBuilder
                .path("/api/v1/toys/{toy_id}/{toyReview_id}")
                .buildAndExpand(toysId, saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(saved);
    }

    /**
     * Retrieve all reviews for a specific toy.
     *
     * @param toysId - ID of the toy.
     * @return ResponseEntity containing a list of toy reviews.
     */
    @Operation(summary = "retrieves the toy reviews by the given toy id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "got all the toy review for that toy id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ToyDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "toy reviews not found",
                    content = @Content) })
    @GetMapping("/toys/{id}/toyReviews")
    public ResponseEntity<List<ToyReviewDto>> getToyReviewByToyId(@PathVariable("id") long toysId) {
        return ResponseEntity.ok(toyReviewService.findToyReviewByToyId(toysId));
    }

    /**
     * Retrieve a specific toy review by its ID.
     *
     * @param id - ID of the toy review.
     * @return ResponseEntity containing the toy review.
     */
    @Operation(summary = "retrieve a specific toy review by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "got the toy review by its id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ToyDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "toy review not found",
                    content = @Content) })
    @GetMapping("/toyReviews/{id}")
    public ResponseEntity<ToyReviewDto> getToyReviewById(@PathVariable long id) {
        return ResponseEntity.ok(toyReviewService.findToyReviewById(id));
    }

    /**
     * Update an existing toy review.
     *
     * @param id - ID of the toy review to be updated.
     * @param dto - Data Transfer Object containing updated review details.
     * @return ResponseEntity containing the updated toy review.
     */
    @Operation(summary = " updating an existing ToyReview")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updated the ToyReview",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ToyDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "toy review not found",
                    content = @Content) })
    @PutMapping("/toyReviews/{id}")
    public ResponseEntity<ToyReviewDto> updateToyReview(@PathVariable long id, @Valid @RequestBody ToyReviewDto dto) {
        return ResponseEntity.ok(toyReviewService.updateToyReviewById(id, dto));
    }

    /**
     * Delete a specific toy review by its ID.
     *
     * @param id - ID of the toy review to be deleted.
     * @return ResponseEntity containing the deleted toy review.
     */
    @Operation(summary = " deleted ToyReview by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted the ToyReview",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ToyDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "toy review not found",
                    content = @Content)
    })
    @DeleteMapping("/toyReviews/{id}")
    public ResponseEntity<ToyReviewDto> deleteToyReview(@PathVariable long id) {
        return ResponseEntity.ok(toyReviewService.deleteToyReviewById(id));
    }
}