package com.greenmark.database.dynamo;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDBLocalConfig {
    public static void main(String[] args) {
        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        "http://localhost:8000", "us-west-2")) // Use any region since it's local
                .build();

        System.out.println("DynamoDB Local connected successfully!");
    }
}
