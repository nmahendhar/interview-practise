package com.hcl.lead.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductRequest(
    @NotBlank(message = "Product code cannot be empty")
    @Size(min = 3, max = 10, message = "Product code must be between 3 and 10 alphanumeric characters")
    String productCode,

    @NotBlank(message = "Product name cannot be empty")
    String name,

    @NotNull(message = "Price field is mandatory")
    @Positive(message = "Price must be strictly greater than zero")
    Double price,

    @NotNull(message = "Quantity field is mandatory")
    Integer quantity
) {}
