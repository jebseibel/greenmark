package com.greenmark.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTDisplayFormatter</p>
 * <p>Description: This class contains convenience methods used to display formatted numbers on our GUIs.
 *    TODO: It has become a real mess and needs to be redesigned and refactored by a junior developer.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
public class UTDisplayFormatter {
	public static final String CLASSNAME = "UTDisplayFormatter";

	public static final String TAB = "     ";

	/////////////////////////////////////////////////
	// CONSTRUCTORS
	/////////////////////////////////////////////////
	private UTDisplayFormatter() {
		// can't call
	}

	public static final String formatNumSpaces(int numberOfSpaces) {
		String methodname = "formatNumSpaces";
		StringBuffer outBuffer = new StringBuffer();

		for (int index = 0; index < numberOfSpaces; index++) {
			outBuffer.append(" ");
		}

		return outBuffer.toString();
	}

	public static final String formatExactPriceString(float inFloat) {
		String methodname = "formatExactPriceString";
		try {
			NumberFormat formatter = new DecimalFormat("#0.00");
			return formatter.format(inFloat) + "   ";
			// return padString(truncAndPadFloatString(floatString, 2), 5, true);
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	// Cannot use trace in these methods. They are used by the GUI toString() w/ no arguments allowed.

	// Format Float number to have commas and only 2 decimal points: ###.##
	// NOTE: This function assumes a positive number.
	public static final String formatLargeMoneyDisplay(double inFloat) {
		String methodname = "formatLargeMoneyDisplay";
		try {
			if (inFloat == 0) {
				return "0.00"; // Return a pretty zero string.
			} else {
				// String floatString = truncFloatString( floatToString( inFloat ), 2 );
				String floatString = floatToString(inFloat);

				// System.out.println( "formatLargeMoneyDisplay ---- FloatToString: [" + floatString + "]" );
				int strLen = floatString.length();

				int decPt = floatString.indexOf("."); // Find the decimal point
				if (decPt != -1) // If there is a decimal pt.
				{
					// Truncate fractional part of number to 2 places
					String truncStr = truncCurrencyString(floatString);
					// System.out.println( "truncCurrencyString ---- truncStr: [" + truncStr + "]" );
					String outString;
					int strlen = truncStr.length();

					// If value is divisible by 1,000,000 then it needs two commas
					if ((Math.abs(inFloat) / 1000000) >= 1) {
						int firstDigitPosition = strlen - 9;
						if (firstDigitPosition < 0) {
							firstDigitPosition = 0;

						}
						outString = truncStr.substring(0, firstDigitPosition) + "," + truncStr.substring(strlen - 9, strlen - 6) + "," + truncStr.substring(strlen - 6, strlen);
						// System.out.println("Million Size outString: [" + outString + "]");
					} else if ((Math.abs(inFloat) / 10000) >= 1) // If value is divisible by 10,000 then it needs one comma
					{
						int firstDigitPosition = strlen - 6;
						if (firstDigitPosition < 0) {
							firstDigitPosition = 0;

						}
						outString = truncStr.substring(0, firstDigitPosition) + "," + truncStr.substring(strlen - 6, strlen);
						// System.out.println("Thousand Size outString: [" + outString + "]");
					} else {
						outString = truncStr;
						// System.out.println("Hundreds Size outString: [" + outString + "]");
					}

					// System.out.println("Returning: [" + outString + "]");
					return (outString);

				} // Endif decPt <= -1
				else // There is no decimal pt in formatted number.
				{
					// System.out.println("No Decimal pt in formatted number Returning: [" + floatString + "]");
					return (floatString);
				} // End else
			} // Endelse inFloat=0
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	// Format Float number to have commas and only 2 decimal points: ###.##
	// NOTE: This function assumes a positive number.
	public static final String formatLargeMoneyDisplay(float inFloat) {
		String methodname = "formatLargeMoneyDisplay";
		try {
			if (inFloat == 0) {
				return "0.00"; // Return a pretty zero string.
			} else {
				// String floatString = truncFloatString( floatToString( inFloat ), 2 );
				String floatString = floatToString(inFloat);

				// System.out.println( "formatLargeMoneyDisplay ---- FloatToString: [" + floatString + "]" );
				int strLen = floatString.length();

				int decPt = floatString.indexOf("."); // Find the decimal point
				if (decPt != -1) // If there is a decimal pt.
				{
					// Truncate fractional part of number to 2 places
					String truncStr = truncCurrencyString(floatString);
					// System.out.println( "truncCurrencyString ---- truncStr: [" + truncStr + "]" );
					String outString;
					int strlen = truncStr.length();

					// If value is divisible by 1,000,000 then it needs two commas
					if ((Math.abs(inFloat) / 1000000) >= 1) {
						int firstDigitPosition = strlen - 9;
						if (firstDigitPosition < 0) {
							firstDigitPosition = 0;

						}
						outString = truncStr.substring(0, firstDigitPosition) + "," + truncStr.substring(strlen - 9, strlen - 6) + "," + truncStr.substring(strlen - 6, strlen);
						// System.out.println("Million Size outString: [" + outString + "]");
					} else if ((Math.abs(inFloat) / 10000) >= 1) // If value is divisible by 10,000 then it needs one comma
					{
						int firstDigitPosition = strlen - 6;
						if (firstDigitPosition < 0) {
							firstDigitPosition = 0;

						}
						outString = truncStr.substring(0, firstDigitPosition) + "," + truncStr.substring(strlen - 6, strlen);
						// System.out.println("Thousand Size outString: [" + outString + "]");
					} else {
						outString = truncStr;
						// System.out.println("Hundreds Size outString: [" + outString + "]");
					}

					// System.out.println("Returning: [" + outString + "]");
					return (outString);

				} // Endif decPt <= -1
				else // There is no decimal pt in formatted number.
				{
					// System.out.println("No Decimal pt in formatted number Returning: [" + floatString + "]");
					return (floatString);
				} // End else
			} // Endelse inFloat=0
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	public static final String formatPriceDisplay(float priceAmount, int numDigits) {
		String methodname = "formatPriceDisplay";
		try {
			String outString = "";

			if (priceAmount > 999) {
				NumberFormat formatter = new DecimalFormat("#0");
				return formatter.format(priceAmount) + "   ";
			}

			if (priceAmount > 1) {
				NumberFormat formatter = new DecimalFormat("#0.00");
				return formatter.format(priceAmount) + "   ";
			}

			NumberFormat formatter = new DecimalFormat("#0.00000");
			outString = formatter.format(priceAmount);
			return truncAndPadPriceString(outString, numDigits);
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	public static final String formatPriceDisplay(float priceAmount) {
		String methodname = "formatPriceDisplay";
		try {
			String outString = "";

			if (priceAmount > 1F || priceAmount < -1F) {
				NumberFormat formatter = new DecimalFormat("#0.00");
				return formatter.format(priceAmount);
			}

			if (priceAmount == 0F) {
				return "0.00";
			}

			NumberFormat formatter = new DecimalFormat("#0.00000");
			outString = formatter.format(priceAmount);
			return outString;
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	public static final String formatPriceDisplay(double priceAmount) {
		String methodname = "formatPriceDisplay";
		try {
			String outString = "";

			if (priceAmount > 1D || priceAmount < -1D) {
				NumberFormat formatter = new DecimalFormat("#0.00");
				return formatter.format(priceAmount);
			}

			if (priceAmount == 0D) {
				return "0.00";
			}

			NumberFormat formatter = new DecimalFormat("#0.00000");
			outString = formatter.format(priceAmount);
			return outString;
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	// Truncates the fractional part of a floating point number to numDigits..
	public static final String truncCurrencyString(String inString) {
		String methodname = "truncCurrencyString";
		try {
			int decPt = inString.indexOf(".");
			int endIndex = inString.length();
			if (endIndex > decPt + 3) { // Truncate the String
				endIndex = decPt + 3;
				return inString.substring(0, endIndex);
			} else // Pad the String
			{
				if (endIndex == decPt + 1) {
					return inString.substring(0, endIndex) + "00";
				} else if (endIndex == decPt + 2) {
					return inString.substring(0, endIndex) + "0";
				} else {
					return inString.substring(0, endIndex);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	// Truncates the fractional part of a floating point number to numDigits. Pads with zeros if less than numDigits.
	public static final String truncAndPadFloatString(String inString, int numDigits) {
		String methodname = "truncAndPadFloatString";
		try {
			int decPt = inString.indexOf(".");
			int endIndex = inString.length();
			if (numDigits == 0) {
				return inString.substring(0, decPt);
			}

			if (endIndex > decPt + numDigits + 1) { // Truncate the String
				endIndex = decPt + numDigits + 1;
				return inString.substring(0, endIndex);
			} else // Pad the String
			{
				// System.out.println( "TruncAndPad Returning: [" + " " + inString.substring( 0, endIndex ) + "]" );
				if (endIndex == decPt + 1) {
					return inString.substring(0, endIndex) + "   ";
				} else if (endIndex == decPt + 2) {
					return inString.substring(0, endIndex) + "  ";
				} else if (endIndex == decPt + 3) {
					return inString.substring(0, endIndex) + " ";
				} else {
					return inString.substring(0, endIndex);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in " + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	// Truncates the fractional part of a floating point number to numDigits.

	// NO DON'T DO THIS: Pads with zeros if less than numDigits.

	// If dollar executedPrice is > 9999, put in k format. i.e. 47.5k 23.2k
	public static final String truncAndPadPriceString(String inString, int numDigits) {
		String methodname = "truncAndPadPriceString";

		StringBuffer returnStringBuffer = new StringBuffer();
		if ("NaN".equals(inString))
			return inString;

		try {
			int decPt = inString.indexOf(".");

			String dollarAmount = inString.substring(0, decPt);
			String penniesAmount = inString.substring(decPt + 1);

			Integer dollarAmountI = Integer.parseInt(dollarAmount);

			if (dollarAmountI > 9999) {
				////////// Put in format: 47.5k

				// Truncate to a multiple of 100
				Integer displayAmount = dollarAmountI / 100;
				String displayAmountString = displayAmount.toString();

				int strlen = displayAmountString.length();
				int hundredDollarIndex = strlen - 1;

				String thousands = displayAmountString.substring(0, hundredDollarIndex);
				String hundreds = displayAmountString.substring(hundredDollarIndex, strlen);

				returnStringBuffer.append(thousands + "." + hundreds + "k");
			} else if (dollarAmountI > 99) {
				/////////// Put in format dollars with 3 decimals
				returnStringBuffer.append(dollarAmount + ".");

				int substringEnd = 3;
				if (penniesAmount.length() == 1) {
					substringEnd = 1;
				} else if (penniesAmount.length() == 2) {
					substringEnd = 2;
				}

				returnStringBuffer.append(penniesAmount, 0, substringEnd);
			} else {
				/////////// Put in format dollars with 6 decimals
				returnStringBuffer.append(dollarAmount + ".");

				int substringEnd = 6;
				if (penniesAmount.length() == 1) {
					substringEnd = 1;
				} else if (penniesAmount.length() == 2) {
					substringEnd = 2;
				} else if (penniesAmount.length() == 3) {
					substringEnd = 3;
				} else if (penniesAmount.length() == 4) {
					substringEnd = 4;
				} else if (penniesAmount.length() == 5) {
					substringEnd = 5;
				}

				returnStringBuffer.append(penniesAmount, 0, substringEnd);
			}

		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}

		return returnStringBuffer.toString();
	}

	// Will make 100,000 executedNumShares = 100K and format smaller number of share values appropriately.
	// Will right justify output.
	// numDigits is how many output spaces the display can take.
	// The minimum numDigits allowed is 4. Since the min. order size is 100 executedNumShares. and we must
	// also account for 100K as a display output value.
	public static final String formatNumShares(float inNumShares, int numDigits) {
		String methodname = "formatNumShares";
		try {
			String outString = "";
			// If we are buying more than 10 executedNumShares, we don't calculate fractions but round to a whole number
			if (inNumShares > 10) {
				return (int) inNumShares + "  ";
			}
			if (inNumShares > 1) {
				NumberFormat formatter = new DecimalFormat("#0.00");
				return formatter.format(inNumShares) + "   ";
			}

			NumberFormat formatter = new DecimalFormat("#0.00000");
			outString = formatter.format(inNumShares);
			return truncAndPadPriceString(outString, numDigits);
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	// Will round a floating point number and display in a 3 digit display
	public static final String formatFloatToWholeNumber(float inFloat) {
		String methodname = "formatKSlow";
		try {
			int wholeNumber = Math.round(inFloat);
			return padString(Integer.toString(wholeNumber), 3, true);
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	// Will display a floating point number truncated to 2 decimal points and display in a 5 digit display
	public static final String formatIndicatorNoDecimals(float inFloat) {
		String methodname = "formatIndicatorNoDecimals";
		try {
			String floatString = floatToString(inFloat);
			return padString(truncAndPadFloatString(floatString, 0), 5, true);
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	// Will display a floating point number truncated to 2 decimal points and display in a 5 digit display
	public static final String formatIndicator_2dec(float inFloat) {
		String methodname = "formatIndicator_2dec";
		try {
			String floatString = floatToString(inFloat);
			return padString(truncAndPadFloatString(floatString, 2), 5, true);
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	// Will display a floating point number truncated to 3 decimal points and display in a 6 digit display
	public static final String formatIndicator_3dec(float inFloat) {
		String methodname = "formatIndicator_3dec";
		try {
			String floatString = floatToString(inFloat);
			return padString(truncAndPadFloatString(floatString, 3), 6, true);
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	// Will display a floating point number truncated to 3 decimal points and display in a 6 digit display
	public static final String formatMacD(float inFloat) {
		String methodname = "formatMacD";
		try {
			String floatString = floatToString(inFloat);
			return padString(truncAndPadFloatString(floatString, 1), 6, true);
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	// Need 1 digit for +/- sign
	// 3 digits for 0-100 percent
	// 1 digit for decimal pt.
	// 2 digits for precision.
	public static final String formatPercent(float inFloat) {
		String methodname = "formatPercent";
		try {
			String outString = truncAndPadFloatString(floatToString(inFloat), 2);
			return padString(outString, 7, true) + "%";
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	// This will look for floating point numbers with the E-# ending and format correctly.
	public static final String floatToString(float inFloat) {
		String methodname = "floatToString  ";
		try {
			// If we are buying more than 10 executedNumShares, we don't calculate fractions but round to a whole number with 2 decimals
			if (inFloat > 10) {
				NumberFormat formatter = new DecimalFormat("#0.00");
				return formatter.format(inFloat);
			}

			if (inFloat > 1) {
				NumberFormat formatter = new DecimalFormat("#0.0000");
				return formatter.format(inFloat);
			}

			NumberFormat formatter = new DecimalFormat("#0.00000000");
			return formatter.format(inFloat);

		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	// This antiquated display method is only used by formatLargeMoneyDisplay. Let's not change it.
	// BUT DON'T USE THIS ANYMORE!!
	public static final String floatToString(double inFloat) {
		String methodname = "floatToString";
		try {
			String tempString = Double.toString(inFloat);
			int eLocation = tempString.indexOf('E');
			// Look for the 'E' character
			if (inFloat == 0) {
				return "0";
			}

			if (eLocation == -1) {
				return tempString;
			} else {
				String expValue = tempString.substring(eLocation + 2);
				int expValueI = Integer.parseInt(expValue);
				StringBuffer returnString = new StringBuffer();
				int firstDigitLocation = 0;

				// Prepend the negative sign if it exists.
				if (tempString.charAt(0) == '-') {
					returnString.append("-0.");
					firstDigitLocation = 1; // Must account for the negative sign.
				} else {
					returnString.append("0.");
					// Add the correct amount of leading zeroes
				}
				for (int index = 0; index < expValueI - 1; index++) {
					returnString.append("0");
					// Now grab the integers from the original string, skip the decimal point
				}
				returnString.append(tempString.charAt(firstDigitLocation));
				// Skip the decimal point and add two more
				int endOfString = firstDigitLocation + 5;
				if (endOfString >= eLocation) {
					endOfString = eLocation;
				}
				returnString.append(tempString, firstDigitLocation + 2, eLocation);
				return returnString.toString();
			}
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	public static final String formatDate(java.util.Date inDate) {
		String methodname = "formatDate";
		try {
			SimpleDateFormat formatter;
			formatter = new SimpleDateFormat("MM-dd-yy");
			return formatter.format(inDate);
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}

		// return a deafault
		return "00-00-00";
	}

	public static final String formatTime(java.util.Date inDate) {
		String methodname = "formatTime";
		try {
			SimpleDateFormat formatter;
			formatter = new SimpleDateFormat("HH:mm:ss");
			return formatter.format(inDate);
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}

		// return a deafault
		return "00:00:00";
	}

	public static final String formatText(String text, int length) {
		String methodname = "formatText";
		try {
			int thislen = text.length();

			// exact! - return
			if (thislen == length) {
				return text;
			}

			// too long
			if (thislen > length) {
				return text.substring(0, length);
			}

			// too short
			String pad = UTDisplayFormatter.buildPad(length - thislen);
			return text + pad;
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exception!";
	}

	/**
	 * creates a String with the sent in number of blank spaces
	 */
	private static final String buildPad(int length) {
		StringBuffer stb = new StringBuffer();

		for (int i = 1; i <= length; i++) {
			stb.append(" ");
		}
		return stb.toString();
	}

	// Makes the input string exactly as wide as numDigits. Aligns data to the right.
	// numDigits cannot be greater than 8
	public static final String padString(String inString, int numDigits, boolean padToFront) {
		String methodname = "padString";
		try {
			StringBuffer outString = new StringBuffer();

			int strLen = inString.length();
			if (strLen > numDigits) {
				return inString;
			} else {
				int numToPad = numDigits - strLen;
				for (int padIndex = 0; padIndex < numToPad; padIndex++)
					outString.append(" ");

				if (padToFront)
					return outString + inString;
				else
					return inString + outString;
			} // Endelse
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	// ------------------------------------------- METHODS USED BY HistoryComparatorService ---------------------------------------------------
	public static final String formatPercentSimulatorSummary(float inFloat) {
		NumberFormat formatter = new DecimalFormat("#0.00");

		// If positive number, add a + sign
		if (inFloat > 0F)
			return "+" + formatter.format(inFloat) + "%";

		return formatter.format(inFloat) + "%";
	}

	public static final String formatPriceDisplaySimulatorSummary(float priceAmount) {
		String methodname = "formatPriceDisplay";
		try {
			String outString = formatPriceDisplay(priceAmount);

			// If positive number, add a + sign
			if (priceAmount > 0F)
				return "+" + outString;

			return outString;
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	public static final String formatPriceDisplaySimulatorSummary(double priceAmount) {
		String methodname = "formatPriceDisplay";
		try {
			String outString = formatPriceDisplay(priceAmount);

			// If positive number, add a + sign
			if (priceAmount > 0F)
				return "+" + outString;

			return outString;
		} catch (Exception e) {
			System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
		}
		return "Exc";
	}

	public static final String formatIntegerSimulatorSummary(Integer amount) {
		// If positive number, add a + sign
		if (amount > 0)
			return "+" + amount;

		return amount.toString();
	}

	// This won't have any decimal points, but will have the % sign at the end. It has no padding, used in html.
	public static final String formatIntegerPercent(float inFloat) {
		Integer floatInt = Math.round(inFloat);
		return floatInt + "%";
	}

	// This won't have any decimal points, but will have the % sign at the end. It has no padding, used in html.
	public static final String formatIntegerPercentSimulatorSummary(float inFloat) {
		Integer floatInt = Math.round(inFloat);

		// If positive number, add a + sign
		if (floatInt > 0)
			return "+" + floatInt + "%";

		return floatInt + "%";
	}

	/////////////////////////////////////////////////
	// STATIC METHODS
	/////////////////////////////////////////////////

	/* ************************************************************************************ */
	/* ********************** MAIN ******************************************************** */
	/* ************************************************************************************ */
	public static void main(String[] args) {
		System.out.println("START");

		// Trace trace = new Trace();
		// trace.setGlobalMessageOn();

		// Stock oStock = new Stock( "Microsoft", "MSFT", "market", Stock.LONG_STOCK );
		// THESE WOULD HAVE BEEN GREAT IN A JUNIT TEST :)
		try {
			float testFloat = (float) 1000000.0;
			String myString = formatLargeMoneyDisplay(testFloat);
			System.out.println("Formatted Float: [" + myString + "]   Input Float: [" + testFloat + "]");

			testFloat = (float) 48359.996;
			myString = formatLargeMoneyDisplay(testFloat);
			System.out.println("Formatted Float: [" + myString + "]   Input Float: [" + testFloat + "]");

			testFloat = (float) 4835.900;
			myString = formatLargeMoneyDisplay(testFloat);
			System.out.println("Formatted Float: [" + myString + "]   Input Float: [" + testFloat + "]");

			/**
			 * float testFloat = ( float ) 0.0043; String myString = floatToString( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = ( float ) 0.00043; myString = floatToString( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = ( float ) 0.000043; myString = floatToString( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = ( float ) 0.0000043; myString = floatToString( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = ( float ) 0.00000043; myString = floatToString( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = ( float ) 0.000000043; myString = floatToString( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = ( float ) - 0.0043; myString = floatToString( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = ( float ) - 0.00043; myString = floatToString( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = ( float ) - 0.000043; myString = floatToString( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = ( float ) - 0.0000043; myString = floatToString( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = ( float ) - 0.00000043; myString = floatToString( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = ( float ) - 0.000000043; myString = floatToString( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 **/

			/**
			 * float testFloat = (float) 0; String myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 3; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 3.2; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 3.23; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 3.234; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 3.234567; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 23; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 23.2; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 23.23; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 23.234; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 23.234567; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 123; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 123.2; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 123.23; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 123.234; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * testFloat = (float) 123.234567; myString = formatPriceDisplay( testFloat ); System.out.println( "Formatted Float: [" + myString + "] Input Float: [" + testFloat + "]" );
			 * 
			 * 
			 * 
			 * 
			 * String symbol = "BZ"; myString = padString( symbol, 5 ); System.out.println( "Formatted String: [" + myString + "] Input String: [" + symbol + "]" );
			 * 
			 * symbol = "CCU"; myString = padString( symbol, 5 ); System.out.println( "Formatted String: [" + myString + "] Input String: [" + symbol + "]" );
			 * 
			 * symbol = "MSFT"; myString = padString( symbol, 5 ); System.out.println( "Formatted String: [" + myString + "] Input String: [" + symbol + "]" );
			 * 
			 * symbol = "NASDQ"; myString = padString( symbol, 5 ); System.out.println( "Formatted String: [" + myString + "] Input String: [" + symbol + "]" );
			 * 
			 * 
			 * 
			 * int testShares = 0; myString = formatNumShares( testShares, 4 ); System.out.println( "Formatted Shares: [" + myString + "] Input Shares: [" + testShares + "]" );
			 * 
			 * testShares = 100; myString = formatNumShares( testShares, 4 ); System.out.println( "Formatted Shares: [" + myString + "] Input Shares: [" + testShares + "]" );
			 * 
			 * testShares = 300; myString = formatNumShares( testShares, 4 ); System.out.println( "Formatted Shares: [" + myString + "] Input Shares: [" + testShares + "]" );
			 * 
			 * testShares = 1200; myString = formatNumShares( testShares, 4 ); System.out.println( "Formatted Shares: [" + myString + "] Input Shares: [" + testShares + "]" );
			 * 
			 * testShares = 12000; myString = formatNumShares( testShares, 4 ); System.out.println( "Formatted Shares: [" + myString + "] Input Shares: [" + testShares + "]" );
			 * 
			 * testShares = 120000; myString = formatNumShares( testShares, 4 ); System.out.println( "Formatted Shares: [" + myString + "] Input Shares: [" + testShares + "]" );
			 * 
			 * testShares = 500000; myString = formatNumShares( testShares, 4 ); System.out.println( "Formatted Shares: [" + myString + "] Input Shares: [" + testShares + "]" );
			 * 
			 * testShares = 50000; myString = formatNumShares( testShares, 4 ); System.out.println( "Formatted Shares: [" + myString + "] Input Shares: [" + testShares + "]" );
			 **/

		} catch (Exception e) {
			System.out.println("Exception [" + e.getMessage() + "]");
		}
		System.out.println("END");
	}

}
