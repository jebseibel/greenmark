package com.greenmark.common.database.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FollowDb {

    private String extid;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
    private Integer active;
}
