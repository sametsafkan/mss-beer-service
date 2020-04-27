package com.sametsafkan.brewery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto implements Serializable {

    static final long serialVersionUID = 5180635144110769305L;

    @Null
    private UUID id;
    @Null
    private Integer version;
    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = STRING)
    private OffsetDateTime createDate;
    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = STRING)
    private OffsetDateTime lastModifiedDate;
    @NotBlank
    private String name;
    @NotNull
    private BeerStyle style;
    @NotNull
    private String upc;
    @NotNull
    @Positive
    @JsonFormat(shape = STRING)
    private BigDecimal price;
    private Integer quantityOnHand;
}