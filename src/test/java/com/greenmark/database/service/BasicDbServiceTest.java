package com.greenmark.database.service;

import com.greenmark.database.DomainBuilderDatabase;
import org.junit.jupiter.api.Test;

class BasicDbServiceTest {

    private final BaseDbService dbService = new BaseDbService("Test");

    @Test
    void getCreatedMessage() {
        String extid = DomainBuilderDatabase.getUUID();
        String result = dbService.getCreatedMessage(extid);

        System.out.println(result);
    }
}