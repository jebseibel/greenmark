package com.greenmark.database.db.mapper;

import com.greenmark.common.database.domain.BucketDailyDb;
import com.greenmark.database.db.entity.BucketDaily;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class BucketDailyMapper {

    public BucketDailyDb toDb(BucketDaily item) {
        return new ModelMapper().map(item, BucketDailyDb.class);
    }

    public BucketDaily toEntity(BucketDailyDb itemDb) {
        return new ModelMapper().map(itemDb, BucketDaily.class);
    }

    public List<BucketDailyDb> toList(List<BucketDaily> items) {
        return items.stream().map( item -> toDb(item)).toList();
    }
}
