package com.tuantran.CarShowroom.payload.response.cartemplate;

import com.tuantran.CarShowroom.payload.response.brand.BrandForCarResponse;
import com.tuantran.CarShowroom.payload.response.segment.SegmentForCarResponse;
import com.tuantran.CarShowroom.payload.response.type.TypeForCarResponse;
import lombok.Data;

import java.util.Date;

@Data
public class CarTemplateResponse {
    private long id;
    private String name;
    private String description;
    private BrandForCarResponse brand;
    private SegmentForCarResponse segment;
    private TypeForCarResponse type;
    private Date createdDate;
    private Date updatedDate;
}
