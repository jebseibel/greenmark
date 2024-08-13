package com.greenmark.app;

import com.greenmark.common.database.domain.BucketMinute60Db;
import com.greenmark.datafeed.finnhub.client.FinnhubClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
public class Bucket60Service {

    List<BucketMinute60Db> list;
    BigDecimal promote;
    BigDecimal demote;

    public Bucket60Service(BigDecimal promote, BigDecimal demote) {
        this.list = new ArrayList<>();
        this.promote = promote;
        this.demote = demote;
    }

    /////////////////////////////////////////////////////////////
    // Methods
    /////////////////////////////////////////////////////////////
    public int size() {
        return list.size();
    }

    public void addItem(BucketMinute60Db bucketMinute60) {
        if(! list.contains(bucketMinute60))
            list.add(bucketMinute60);
    }

    public void removeItem(BucketMinute60Db bucketMinute60) {
        list.remove(bucketMinute60);
    }

    public List<BucketMinute60Db> getBucketMinute60Db() {
        return list;
    }

    /////////////////////////////////////////////////////////////////////////////////
    public List<BucketMinute60Db> getAllPromote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(promote) > 0)
                .collect(Collectors.toList());
    }

    /////////////////////////////////////////////////////////////////////////////////
    public List<BucketMinute60Db> getAllDemote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(demote) < 0)
                .collect(Collectors.toList());
    }

    /////////////////////////////////////////////////////////////////////////////////
//    public List<BucketMinute60Db> updateAll(FinnhubClient client) {
//        for(BucketMinute60Db bucketMinute60 : list) {
//            String symbol = bucketMinute60.getSymbol();
//            try {
//                client.getQuote(symbol);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//    }
}
