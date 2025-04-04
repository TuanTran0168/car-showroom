package com.tuantran.CarShowroom.payload.request.type;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeCreateRequest {

    @NotBlank
    private String name;
}
