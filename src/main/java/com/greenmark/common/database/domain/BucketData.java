package com.greenmark.common.database.domain;

public interface BucketData {
    String getSymbol();

    java.math.BigDecimal getCurrent();

    java.math.BigDecimal getOpen();

    java.math.BigDecimal getHigh();

    java.math.BigDecimal getLow();

    java.math.BigDecimal getPreviousClose();

    java.math.BigDecimal getChanged();

    java.math.BigDecimal getChangedPercent();

    java.math.BigDecimal getMacd();

    java.math.BigDecimal getStochk();

    java.time.LocalDateTime getCreatedAt();

    java.time.LocalDateTime getModifiedAt();

    java.time.LocalDateTime getDeletedAt();

    Integer getActive();

    void setSymbol(String symbol);

    void setCurrent(java.math.BigDecimal current);

    void setOpen(java.math.BigDecimal open);

    void setHigh(java.math.BigDecimal high);

    void setLow(java.math.BigDecimal low);

    void setPreviousClose(java.math.BigDecimal previousClose);

    void setChanged(java.math.BigDecimal changed);

    void setChangedPercent(java.math.BigDecimal changedPercent);

    void setMacd(java.math.BigDecimal macd);

    void setStochk(java.math.BigDecimal stochk);

    void setCreatedAt(java.time.LocalDateTime createdAt);

    void setModifiedAt(java.time.LocalDateTime modifiedAt);

    void setDeletedAt(java.time.LocalDateTime deletedAt);

    void setActive(Integer active);

    String toStringTA();
}
