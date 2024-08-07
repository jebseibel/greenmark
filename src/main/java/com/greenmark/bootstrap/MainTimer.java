package com.greenmark.bootstrap;

import com.greenmark.database.db.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MainTimer implements CommandLineRunner {

    private List<BucketDaily> bucketDailys = new ArrayList<BucketDaily>();
    private List<BucketMinute60> bucketMinute60s = new ArrayList<BucketMinute60>();
    private List<BucketMinute15> bucketMinute15s = new ArrayList<BucketMinute15>();
    private List<BucketMinute05> bucketMinute05s = new ArrayList<BucketMinute05>();
    private List<BucketMinute01> bucketMinute01s = new ArrayList<BucketMinute01>();
    private List<StockDaily> stockDailies = new ArrayList<StockDaily>();

    public MainTimer() {
    }

    @Override
    public void run(String... args) throws Exception {

    }
    public void start() {

        // initialize or refresh

        // FOR EACH MINUTE
        // update StockDaily
        // update BucketDaily
        // update BucketMinute60
        // update BucketMinute15
        // update BucketMinute05
        // update BucketMinute01

    }

    public void initializeBuckets() {

    }

    public void checkUpdateDaily() {

    }

    public void checkUpdateMinute60() {

    }

    public void checkUpdateMinute15() {

    }

    public void checkUpdateMinute05() {

    }

    public void checkUpdateMinute01() {

    }


}
