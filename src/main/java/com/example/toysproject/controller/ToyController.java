package com.example.toysproject.controller;

import com.example.toysproject.dto.ToyDto;
import com.example.toysproject.dto.ToyPageDto;
import com.example.toysproject.service.ToyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.Map;

// ... [import statements]

@RestController
@RequestMapping("/api/v1/toys")
@RequiredArgsConstructor
@Tag(name = "Toy controller", description = "All the toys")
@SecurityRequirement(name = "Bearer Authentication")
public class ToyController {

    private final ToyService toyService;

    /**
     * Endpoint to add a new toy.
     */
    @Operation(summary = "Add a toy")
    // ... [Swagger annotations]
    @PostMapping
    public ResponseEntity<ToyDto> addToy(@Valid @RequestBody ToyDto toyDto, UriComponentsBuilder uriBuilder) {
        var saved = toyService.createToy(toyDto);
        var uri = uriBuilder.path("/api/v1/toys/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    /**
     * Endpoint to get all toys.
     */
    @Operation(summary = "Get All the toys")
    // ... [Swagger annotations]
    @GetMapping
    public ResponseEntity<Collection<ToyDto>> getAllToys() {
        return ResponseEntity.ok(toyService.getAllToys());
    }

    /**
     * Endpoint to delete a toy by ID.
     */
    @Operation(summary = "Delete a Toy by its id")
    // ... [Swagger annotations]
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteToyById(@PathVariable long id) {
        toyService.deleteToyById(id);
        return ResponseEntity.ok(Map.of("message", "Deleted successfully"));
    }

    /**
     * Endpoint to get a toy by ID.
     */
    @Operation(summary = "Get a Post by its id")
    // ... [Swagger annotations]
    @GetMapping("/{id}")
    public ResponseEntity<ToyDto> getToyById(@PathVariable long id) {
        return ResponseEntity.ok(toyService.getToyById(id));
    }

    /**
     * Endpoint to update a toy by ID.
     */
    @Operation(summary = "Update a Toy by its id")
    // ... [Swagger annotations]
    @PutMapping("/{id}")
    public ResponseEntity<ToyDto> updateToyById(@PathVariable long id, @Valid @RequestBody ToyDto dto) {
        return ResponseEntity.ok(toyService.updateToyById(id, dto));
    }

    /**
     * Endpoint to get a paginated view of all toys.
     */
    @Operation(summary = "Get all pages")
    // ... [Swagger annotations]
    @GetMapping("/page")
    public ResponseEntity<ToyPageDto> pageAllToys(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return ResponseEntity.ok(toyService.getAllToys(pageNo, pageSize, sortBy, sortDir));
    }
}
