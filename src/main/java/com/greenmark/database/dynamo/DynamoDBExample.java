package com.greenmark.database.dynamo;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;

public class DynamoDBExample {
    public static void main(String[] args) {
        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        "http://localhost:8000", "us-west-2"))
                .build();

        CreateTableRequest request = new CreateTableRequest()
                .withTableName("Movies")
                .withKeySchema(new KeySchemaElement("Year", KeyType.HASH), // Partition key
                        new KeySchemaElement("Title", KeyType.RANGE)) // Sort key
                .withAttributeDefinitions(new AttributeDefinition("Year", ScalarAttributeType.N),
                        new AttributeDefinition("Title", ScalarAttributeType.S))
                .withProvisionedThroughput(new ProvisionedThroughput(10L, 10L));

        dynamoDB.createTable(request);
        System.out.println("Table created successfully!");
    }
}

