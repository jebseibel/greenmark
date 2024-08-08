package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketMinute01Db;
import com.greenmark.database.db.entity.BucketMinute01;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class BucketMinute01Mapper {

    public BucketMinute01Db toDb(BucketMinute01 item) {
        return new ModelMapper().map(item, BucketMinute01Db.class);
    }

    public BucketMinute01 toEntity(BucketMinute01Db itemDb) {
        return new ModelMapper().map(itemDb, BucketMinute01.class);
    }

    public List<BucketMinute01Db> toList(List<BucketMinute01> items) {
        return items.stream().map( item -> toDb(item)).toList();
    }
}
