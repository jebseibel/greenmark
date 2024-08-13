package com.greenmark.app;

import com.greenmark.common.database.domain.BucketMinute60Db;
import com.greenmark.common.enums.CompResult;
import com.greenmark.common.indicators.Macd;
import com.greenmark.common.indicators.TAIndicator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

//@Configuration
//@ConfigurationProperties(prefix = "model.minute60")
@Slf4j
@Data
public class Bucket60Service {

    List<BucketMinute60Db> list;
    BigDecimal promote;
    BigDecimal demote;

    public Bucket60Service(BigDecimal promote, BigDecimal demote) {
        this.list = new ArrayList<BucketMinute60Db>();
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

    public List<BucketMinute60Db> getAllPromoteOld() {

        List<BucketMinute60Db> results = new ArrayList<BucketMinute60Db>();

        for (BucketMinute60Db bucketMinute60 : list) {
            BigDecimal macd = bucketMinute60.getMacd();
            int comp = macd.compareTo(promote);
            System.out.println(macd + " = " + promote);
            System.out.println("comp:" + comp);

            if (comp > 0) {
                results.add(bucketMinute60);
                System.out.println("add");
            }
        }

        System.out.println("results : "+results.size());
        return results;
    }

    /////////////////////////////////////////////////////////////////////////////////
    public List<BucketMinute60Db> getAllDemote() {
        return list.stream()
                .filter(item -> item.getMacd().compareTo(demote) < 0)
                .collect(Collectors.toList());
    }

    public List<BucketMinute60Db> getAllDemoteOld() {

        List<BucketMinute60Db> results = new ArrayList<BucketMinute60Db>();

        for (BucketMinute60Db bucketMinute60 : list) {
            BigDecimal macd = bucketMinute60.getMacd();
            int comp = macd.compareTo(demote);

            System.out.println();
            System.out.println(macd + " = demote:" + demote);
            System.out.println("comparision :" + getComparision(comp));

            if (comp < 0) {
                results.add(bucketMinute60);
                System.out.println("add");
            }
        }

        System.out.println("results : "+results.size());
        return results;
    }

    public String getComparision(int incoming) {
        if (incoming == -1) { return "LESSER"; }
        if (incoming == 1) { return "GREATER"; }
        if (incoming == 0) { return "EQUAL"; }
        return "NONE";
    }
}
