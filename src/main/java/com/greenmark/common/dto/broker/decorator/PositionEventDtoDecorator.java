package com.greenmark.common.dto.broker.decorator;

import com.greenmark.common.GmConstants;
import com.greenmark.common.GmConstantsBroker;
import com.greenmark.common.dto.broker.PositionEventDto;
import com.greenmark.utils.UTDatetime;
import com.greenmark.utils.UTDisplayFormatter;
import com.greenmark.utils.UTFormatter;
import com.greenmark.utils.UTTimeframe;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: PositionEventDtoDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its PositionEventDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class PositionEventDtoDecorator extends PositionEventDto implements Serializable {
    public static final String CLASSNAME = "PositionEventDtoDecorator";
    private static final long serialVersionUID = 1L;

    public PositionEventDtoDecorator() {
    }

    public PositionEventDtoDecorator(PositionEventDtoDecorator positionEvent) {
        try {
            if (positionEvent != null)
                BeanUtils.copyProperties(this, positionEvent);
        } catch (InvocationTargetException ite) {
            System.out.println("ERROR creating " + CLASSNAME + ", InvocationTargetException, message: " + ite.getMessage());
        } catch (IllegalAccessException iae) {
            System.out.println("ERROR creating " + CLASSNAME + ", IllegalAccessException, message: " + iae.getMessage());
        }
    }

    public PositionEventDtoDecorator(String xmldata) {
        super(xmldata);
    }

    public PositionEventDtoDecorator(OrderDtoDecorator currentOrder) {
        super(currentOrder);
    }

    public PositionEventDtoDecorator(PositionDtoDecorator currentPosition) {
        super(currentPosition);
    }

    public PositionEventDtoDecorator(OrderDtoDecorator currentOrder, PositionDtoDecorator currentPosition) {
        super(currentOrder, currentPosition);
    }

    public String getPriceDisplay() {
        if ((this.type == GmConstants.EVENT_MARKET_ORDER_PLACED) || (this.type == GmConstants.EVENT_BUY_ORDER_PLACED) || (this.type == GmConstants.EVENT_SELL_ORDER_PLACED)
                || (this.type == GmConstants.EVENT_BUY_ORDER_REPLACED) || (this.type == GmConstants.EVENT_SELL_ORDER_REPLACED) || (this.type == GmConstants.EVENT_BUY_ORDER_EXECUTED)
                || (this.type == GmConstants.EVENT_SELL_ORDER_EXECUTED)) {
            if (orderEntryPrice == 0F)
                return "";
            String returnString = UTFormatter.formatShares(orderEntryPrice); // This gives more decimal points for small prices
            return returnString;
        }
        return "";
    }

    // ------------------------------------ WEBAPP CONVENIENCE METHODS ---------------------------------------------
    public String getOrderEntryPriceDatetimeString() {
        if (orderEntryPriceDatetime != null)
            return UTDatetime.toString(orderEntryPriceDatetime);

        return "";
    }

    public String getOrderEntryCalcPriceHigherString() {
        if (orderEntryCalcPriceHigher == 0)
            return "No";
        else
            return "Yes";
    }

    public String getDatetimeDisplay() {
        return UTDatetime.toString(eventDatetime);
    }

    public String getFromTimeframeDisplay() {
        return UTTimeframe.getChartTimeframeStringForGmanTimeframe(fromTimeframe);
    }

    public String getToTimeframeDisplay() {
        return UTTimeframe.getChartTimeframeStringForGmanTimeframe(toTimeframe);
    }

    public String getBuySellDisplay() {
        if (buyOrSell == GmConstantsBroker.TYPE_BUY)
            return "Buy";
        else
            return "Sell";
    }

    public String getMin1highDisplay() {
        return UTDisplayFormatter.formatPriceDisplay(min1high, 4);
    }

    public String getMin1lowDisplay() {
        return UTDisplayFormatter.formatPriceDisplay(min1low, 4);
    }

    public String getMin1openDisplay() {
        return UTDisplayFormatter.formatPriceDisplay(min1open, 4);
    }

    public String getMin1closeDisplay() {
        return UTDisplayFormatter.formatPriceDisplay(min1close, 4);
    }

    public String getOrderBuySellDisplay() {
        if (buyOrSell == GmConstantsBroker.TYPE_BUY)
            return "Buy";
        else
            return "Sell";
    }

    public String getOrderStopOrMarketDisplay() {
        if (stopOrMarket == GmConstantsBroker.TYPE_MARKET)
            return "Market";
        else
            return "Stop";
    }

    public String getTransactionFeeDisplay() {
        return UTDisplayFormatter.formatPriceDisplay(orderTransactionFee, 4);
    }

    public String getMarginFeeDisplay() {
        return UTDisplayFormatter.formatPriceDisplay(orderMarginFee, 4);
    }

    public String getOrderPlacedDatetimeDisplay() {
        return UTDatetime.toString(orderPlacedDatetime);
    }

    public String getOrderExecutedDatetimeDisplay() {
        return UTDatetime.toString(orderExecutedDatetime);
    }

    public String getOrderEntryNumSharesDisplay() {
        return UTFormatter.formatShares(orderEntryNumShares);
    }

    public String getOrderEntryPriceDisplay() {
        return UTDisplayFormatter.formatPriceDisplay(orderEntryPrice, 4);
    }

    public String getOrderExecutedNumSharesDisplay() {
        return UTFormatter.formatShares(orderExecutedNumShares);
    }

    public String getOrderExecutedPriceDisplay() {
        return UTDisplayFormatter.formatPriceDisplay(orderExecutedPrice, 4);
    }

    public String getPositionStoplossPriceDisplay() {
        return UTDisplayFormatter.formatPriceDisplay(positionStoplossPrice, 4);
    }
}
