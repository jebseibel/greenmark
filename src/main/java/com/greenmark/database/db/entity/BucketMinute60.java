package com.greenmark.database.db.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bucket_minute60")
public class BucketMinute60 implements Serializable {
    private static final long serialVersionUID = -3779377594651740105L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "symbol", length = 8, nullable = false)
    private String symbol;

    @Column(name = "current", nullable = false)
    private Float current;

    @Column(name = "open", nullable = false)
    private Float open;

    @Column(name = "high", nullable = false)
    private Float high;

    @Column(name = "low", nullable = false)
    private Float low;

    @Column(name = "close")
    private Float close;

    @Column(name = "changed")
    private Float changed;

    @Column(name = "changed_percent")
    private Float changedPercent;

    @Column(name = "macd")
    private Float macd;

    @Column(name = "stochk")
    private Float stochk;

    /**
     * BASE FIELDS
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "active", columnDefinition = "integer default 1")
    private Integer active;
}