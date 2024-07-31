package com.greenmark.database.db.entity;

import com.greenmark.common.core.Labels;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.greenmark.core.enums.OrderValues.*;
import static com.greenmark.core.enums.OrderValues.ORDER_ID_UNKNOWN;

@Data
@Entity
@Table(name = "account")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 330515747211210728L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "extid", length = 36, nullable = false, unique = true)
    private String extid;

    @Column(name = "stock_symbol", length = 64, nullable = false, unique = true)
    private String stockSymbol;

    @Column(name = "description", length = 128, nullable = false)
    private String description;

    private float entryPrice = 0;
    private float currentPrice = 0;
    private int status = STATUS_OPEN;
    private int buyOrSellOrder = TYPE_ORDER_BUY;
    private int longOrShortOrder = 1;
    private int stopOrMarketOrder = 1;
    private int targetSizeInShares = 0;
    private float targetAmountInDollars = 0;
    private float transactionFee = 0;
    private int orderReason = ORDER_REASON_DEFAULT;
    private String externalOrderId;
    private String externalTrackingNum;
    private String replaceOrderId = ORDER_ID_UNKNOWN;
    private boolean hasExecutions;
    private float executedPrice = 0;
    private int executedNumShares = 0;
    private LocalDateTime executedDatetime;
    private long positionId = 0;

    /**
     * BASE FIELDS
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "active")
    private Integer active;
}