package com.greenmark.common.core;

import com.greenmark.database.db.entity.BucketMinute01;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Slf4j
public class BucketItem01 {
    private String name;
    private List<BucketMinute01> list = new ArrayList<BucketMinute01>();

    public BucketItem01(String name, List<BucketMinute01> items) {
        this.name = name;
        this.list.addAll(items);
    }

    public Integer updateItems() {
        Iterator<BucketMinute01> iterator = list.iterator();
        while (iterator.hasNext()) {
            BucketMinute01 item = iterator.next();

        }
    }
}
