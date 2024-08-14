package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketMinute05Db;
import com.greenmark.database.db.entity.BucketMinute05;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class BucketMinute05Mapper {

    public BucketMinute05Db toDb(BucketMinute05 item) {
        return new ModelMapper().map(item, BucketMinute05Db.class);
    }

    public BucketMinute05 toEntity(BucketMinute05Db itemDb) {
        return new ModelMapper().map(itemDb, BucketMinute05.class);
    }

    public List<BucketMinute05Db> toList(List<BucketMinute05> items) {
        return items.stream().map(item -> toDb(item)).toList();
    }
}
