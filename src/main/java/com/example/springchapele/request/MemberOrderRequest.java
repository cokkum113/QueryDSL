package com.example.springchapele.request;

import com.example.springchapele.entity.Category;
import com.example.springchapele.entity.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberOrderRequest {
    private BigDecimal priceLow;
    private Category category;
    private String nameContains;
    private List<ProductStatus> statuses;

}
