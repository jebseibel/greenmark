package com.greenmark.database.service;

import com.greenmark.database.db.DomainBuilder;
import org.junit.jupiter.api.Test;

class BasicDbServiceTest {

    private final BasicDbService dbService = new BasicDbService("Test");
    @Test
    void getCreatedMessage() {
        String extid = DomainBuilder.getUUID();
        String result = dbService.getCreatedMessage(extid);

        System.out.println(result);
    }
}