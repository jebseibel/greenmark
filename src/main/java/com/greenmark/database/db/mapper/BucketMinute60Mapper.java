package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketMinute60Db;
import com.greenmark.database.db.entity.BucketMinute60;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class BucketMinute60Mapper {

    public BucketMinute60Db toDb(BucketMinute60 item) {
        return new ModelMapper().map(item, BucketMinute60Db.class);
    }

    public BucketMinute60 toEntity(BucketMinute60Db itemDb) {
        return new ModelMapper().map(itemDb, BucketMinute60.class);
    }

    public List<BucketMinute60Db> toList(List<BucketMinute60> items) {
        return items.stream().map( item -> toDb(item)).toList();
    }
}
