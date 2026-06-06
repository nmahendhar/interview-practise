package com.hcl.lead.mapper;

import com.hcl.lead.dto.ProductResponse;
import com.hcl.lead.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface ProductMapper {

    @Mapping(target = "generatedAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "stockStatus", source = "quantity", qualifiedByName = "calculateStockStatus")
    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponseList(List<Product> products);

    @Named("calculateStockStatus")
    default String calculateStockStatus(Integer quantity) {
        if (quantity == null || quantity <= 0) return "OUT_OF_STOCK";
        if (quantity < 10) return "LOW_STOCK";
        return "IN_STOCK";
    }
}