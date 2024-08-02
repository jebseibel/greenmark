package com.greenmark.database.db.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "stock_nightly")
public class StockNightly implements Serializable {
    private static final long serialVersionUID = -3779377594651740105L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "symbol", length = 8, nullable = false)
    private String symbol;

    @Column(name = "open", nullable = false)
    private Float open;

    @Column(name = "close", nullable = false)
    private Float close;

    @Column(name = "high", nullable = false)
    private Float high;

    @Column(name = "low", nullable = false)
    private Float low;

    @Column(name = "volume")
    private Long volume;

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