package com.tuantran.CarShowroom.payload.response.feature;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FeatureCreateResponse {
    private long id;
    private String name;
}
