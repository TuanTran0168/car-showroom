package com.tuantran.CarShowroom.payload.response.car;

import com.tuantran.CarShowroom.payload.response.brand.BrandForCarResponse;
import com.tuantran.CarShowroom.payload.response.segment.SegmentForCarResponse;
import com.tuantran.CarShowroom.payload.response.type.TypeForCarResponse;
import lombok.Data;

import java.util.Date;

@Data
public class CarResponse {
    private long id;
    private String name;
    private String description;
    private BrandForCarResponse brand;
    private TypeForCarResponse type;
    private SegmentForCarResponse segment;
    private Date createdDate;
    private Date updatedDate;
}
