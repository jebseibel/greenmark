package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketMinute15Db;
import com.greenmark.database.db.entity.BucketMinute15;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class BucketMinute15Mapper {

    public BucketMinute15Db toDb(BucketMinute15 item) {
        return new ModelMapper().map(item, BucketMinute15Db.class);
    }

    public BucketMinute15 toEntity(BucketMinute15Db itemDb) {
        return new ModelMapper().map(itemDb, BucketMinute15.class);
    }

    public List<BucketMinute15Db> toList(List<BucketMinute15> items) {
        return items.stream().map( item -> toDb(item)).toList();
    }
}
