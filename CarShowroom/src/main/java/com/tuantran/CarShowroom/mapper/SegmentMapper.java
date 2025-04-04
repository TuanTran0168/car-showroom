package com.tuantran.CarShowroom.mapper;


import com.tuantran.CarShowroom.entity.Segment;
import com.tuantran.CarShowroom.payload.request.segment.SegmentCreateRequest;
import com.tuantran.CarShowroom.payload.request.segment.SegmentUpdateRequest;
import com.tuantran.CarShowroom.payload.response.segment.SegmentCreateResponse;
import com.tuantran.CarShowroom.payload.response.segment.SegmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SegmentMapper {
    @Mapping(source = "brand", target = "brand")
    SegmentResponse toSegmentResponse(Segment segment);
    Segment toSegment(SegmentCreateRequest segmentCreateRequest);
    SegmentCreateResponse toSegmentCreateResponse(Segment segment);
    void updateSegment(@MappingTarget Segment segment, SegmentUpdateRequest segmentUpdateRequest);
}
