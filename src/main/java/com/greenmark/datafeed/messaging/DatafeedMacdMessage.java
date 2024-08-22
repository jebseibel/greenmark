package com.greenmark.datafeed.messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DatafeedMacdMessage implements Serializable {

    static final long serialVersionUID = 1575179616450084950L;

    private UUID id;
    private String message;
}
