package com.tuantran.CarShowroom.payload.response.color;

import lombok.Data;

import java.util.Date;

@Data
public class ColorResponse {
    private String name;
    private String code;
    private Date createdDate;
    private Date updatedDate;
}
