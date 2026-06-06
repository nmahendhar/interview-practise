package com.hcl.lead.mapper;

import com.hcl.lead.dto.ProductResponse;
import com.hcl.lead.model.Product;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-05T23:53:42+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponse toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        String stockStatus = null;
        Long id = null;
        String productCode = null;
        String name = null;
        Double price = null;
        Integer quantity = null;

        stockStatus = calculateStockStatus( product.getQuantity() );
        id = product.getId();
        productCode = product.getProductCode();
        name = product.getName();
        price = product.getPrice();
        quantity = product.getQuantity();

        LocalDateTime generatedAt = LocalDateTime.now();

        ProductResponse productResponse = new ProductResponse( id, productCode, name, price, quantity, stockStatus, generatedAt );

        return productResponse;
    }

    @Override
    public List<ProductResponse> toResponseList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductResponse> list = new ArrayList<ProductResponse>( products.size() );
        for ( Product product : products ) {
            list.add( toResponse( product ) );
        }

        return list;
    }
}
