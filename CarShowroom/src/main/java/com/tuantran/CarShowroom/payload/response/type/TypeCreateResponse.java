package com.tuantran.CarShowroom.payload.response.type;

import lombok.Data;

import java.util.Date;

@Data
public class TypeCreateResponse {
    private long id;
    private String name;
    private Date createdDate;
    private Date updatedDate;
}
