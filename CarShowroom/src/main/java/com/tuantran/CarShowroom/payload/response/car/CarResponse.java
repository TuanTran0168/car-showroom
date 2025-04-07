package com.tuantran.CarShowroom.payload.response.car;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CarResponse {
    private long id;
    private String name;
    private long carTemplateId;
    private List<FeatureForCarResponse> featureList = new ArrayList<>();
    private Date createdDate;
    private Date updatedDate;
}
