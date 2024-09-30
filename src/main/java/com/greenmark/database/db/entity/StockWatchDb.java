package com.greenmark.database.db.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "stock_watch")
public class StockWatchDb implements Serializable {

    @Serial
    private static final long serialVersionUID = -3779377594651740105L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "timeframe", length = 16, nullable = false)
    private String timeframe;

    @Column(name = "symbol", length = 8, nullable = false)
    private String symbol;

    @Column(name = "current", nullable = false)
    private BigDecimal current;

    @Column(name = "open", nullable = false)
    private BigDecimal open;

    @Column(name = "high", nullable = false)
    private BigDecimal high;

    @Column(name = "low", nullable = false)
    private BigDecimal low;

    @Column(name = "previous_close")
    private BigDecimal previousClose;

    @Column(name = "changed")
    private BigDecimal changed;

    @Column(name = "changed_percent")
    private BigDecimal changedPercent;

    @Column(name = "macd")
    private BigDecimal macd;

    @Column(name = "stochk")
    private BigDecimal stochk;

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