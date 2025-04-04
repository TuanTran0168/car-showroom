package com.tuantran.CarShowroom.utils;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Additional filter parameters")
@Data
public class FilterParamUtils {
    @Schema(description = "Search by name", example = "Tuan")
    private String name;

    @Schema(description = "Filter by active", example = "True")
    private String active;
}
