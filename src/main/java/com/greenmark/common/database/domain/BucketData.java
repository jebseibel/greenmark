package com.greenmark.common.database.domain;

public interface BucketData {
    String getSymbol();

    void setSymbol(String symbol);

    java.math.BigDecimal getCurrent();

    void setCurrent(java.math.BigDecimal current);

    java.math.BigDecimal getOpen();

    void setOpen(java.math.BigDecimal open);

    java.math.BigDecimal getHigh();

    void setHigh(java.math.BigDecimal high);

    java.math.BigDecimal getLow();

    void setLow(java.math.BigDecimal low);

    java.math.BigDecimal getPreviousClose();

    void setPreviousClose(java.math.BigDecimal previousClose);

    java.math.BigDecimal getChanged();

    void setChanged(java.math.BigDecimal changed);

    java.math.BigDecimal getChangedPercent();

    void setChangedPercent(java.math.BigDecimal changedPercent);

    java.math.BigDecimal getMacd();

    void setMacd(java.math.BigDecimal macd);

    java.math.BigDecimal getStochk();

    void setStochk(java.math.BigDecimal stochk);

    java.time.LocalDateTime getCreatedAt();

    void setCreatedAt(java.time.LocalDateTime createdAt);

    java.time.LocalDateTime getModifiedAt();

    void setModifiedAt(java.time.LocalDateTime modifiedAt);

    java.time.LocalDateTime getDeletedAt();

    void setDeletedAt(java.time.LocalDateTime deletedAt);

    Integer getActive();

    void setActive(Integer active);

    String toStringTA();
}
