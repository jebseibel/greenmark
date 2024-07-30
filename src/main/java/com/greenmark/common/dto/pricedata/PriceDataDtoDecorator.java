package com.greenmark.common.dto.pricedata;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: PriceDataDtoDecorator</p>
 * <p>Description: This decorator class contains convenience methods for its PriceDataDto base class.
 *    Decorator classes never contain member variables, they only contain methods that 'Decorate', or provide functionality for its base class.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */

public class PriceDataDtoDecorator extends PriceDataDto implements Serializable {
	public static final String CLASSNAME = "PriceDataDtoDecorator";
	private static final long serialVersionUID = 1L;

	public PriceDataDtoDecorator() {
		super();
	}

	public String getDateString(int index) {
		return dateString[index];
	}

	public LocalDateTime getDateLDT(int index) {
		return dateLDT[index];
	}

	public final void removeDate(int i) {
		if (i < 0)
			return;
		for (int j = i; j < currentGreenmanPeriod - 1; j++) {
			numdate[j] = numdate[j + 1];
			dateLDT[j] = dateLDT[j + 1];
			dateString[j] = dateString[j + 1];

			open[j] = open[j + 1];
			high[j] = high[j + 1];
			low[j] = low[j + 1];
			close[j] = close[j + 1];
			volume[j] = volume[j + 1];
			moneyflow[j] = moneyflow[j + 1];
			dateEpochSeconds[j] = dateEpochSeconds[j + 1];
		}

		currentGreenmanPeriod--;
	}

	protected void buildTenMinuteDate() {
		buildTenMinuteDate(1);
	}

	protected void buildTenMinuteDate(int i) {
		if (dateEpochSeconds[currentGreenmanPeriod - 1] % 10 != 0)
			currentGreenmanPeriod--;
		int l = currentGreenmanPeriod;
		int j = i;
		for (int k = i - 1; j < l; k++) {
			if (dateEpochSeconds[j] % 10 != 0)
				j++;
			open[k] = open[j - 1];
			high[k] = high[j] <= high[j - 1] ? high[j - 1] : high[j];
			low[k] = low[j] >= low[j - 1] ? low[j - 1] : low[j];
			close[k] = close[j];
			volume[k] = volume[j - 1] + volume[j];
			dateEpochSeconds[k] = dateEpochSeconds[j];
			currentGreenmanPeriod = k + 1;
			j += 2;
		}
	}

	public void reverseOrder() {
		for (int j = 0; j < currentGreenmanPeriod / 2; j++) {
			int i5 = numdate[j];
			numdate[j] = numdate[currentGreenmanPeriod - j - 1];
			numdate[currentGreenmanPeriod - j - 1] = i5;

			String i = dateString[j];
			dateString[j] = dateString[currentGreenmanPeriod - j - 1];
			dateString[currentGreenmanPeriod - j - 1] = i;

			LocalDateTime ldt = dateLDT[j];
			dateLDT[j] = dateLDT[currentGreenmanPeriod - j - 1];
			dateLDT[currentGreenmanPeriod - j - 1] = ldt;

			int dateI = dateEpochSeconds[j];
			dateEpochSeconds[j] = dateEpochSeconds[currentGreenmanPeriod - j - 1];
			dateEpochSeconds[currentGreenmanPeriod - j - 1] = dateI;

			float f = open[j];
			open[j] = open[currentGreenmanPeriod - j - 1];
			open[currentGreenmanPeriod - j - 1] = f;

			f = close[j];
			close[j] = close[currentGreenmanPeriod - j - 1];
			close[currentGreenmanPeriod - j - 1] = f;

			f = high[j];
			high[j] = high[currentGreenmanPeriod - j - 1];
			high[currentGreenmanPeriod - j - 1] = f;

			f = low[j];
			low[j] = low[currentGreenmanPeriod - j - 1];
			low[currentGreenmanPeriod - j - 1] = f;

			f = volume[j];
			volume[j] = volume[currentGreenmanPeriod - j - 1];
			volume[currentGreenmanPeriod - j - 1] = f;

			// f = moneyflow[j];
			// moneyflow[j] = moneyflow[currentGmDays - j - 1];
			// moneyflow[currentGmDays - j - 1] = f;
		}
	}

	public String getStockPricesString() {
		String methodname = "Chartdata.getStockPricesString";
		StringBuffer returnBuf = new StringBuffer();

		for (int dataIndex = 0; dataIndex < currentGreenmanPeriod; dataIndex++) {

			if (dateString[dataIndex] != null) {
				returnBuf.append(dateString[dataIndex]);
				returnBuf.append(",");
			} else {
				returnBuf.append("dateString null");
			}

			returnBuf.append(high[dataIndex]);
			returnBuf.append(",");

			returnBuf.append(low[dataIndex]);
			returnBuf.append(",");

			returnBuf.append(open[dataIndex]);
			returnBuf.append(",");

			returnBuf.append(close[dataIndex]);
			returnBuf.append(",");

			returnBuf.append(volume[dataIndex]);
			returnBuf.append(",");

			returnBuf.append(volume[dataIndex]);
			returnBuf.append(";\n");

		}
		return returnBuf.toString();
	}
}
