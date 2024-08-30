package com.greenmark.app.buckets;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class BucketDataContainer {

    @JsonProperty("bucketDataList")
    private List<BucketData> bucketDataList;

    public BucketDataContainer( ) {}

//    public BucketDataContainer(
//            List<BucketData> bucketDataList
//    ) {
//        this.bucketDataList = bucketDataList;
//    }
}
