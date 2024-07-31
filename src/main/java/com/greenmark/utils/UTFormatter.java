package com.greenmark.utils;

import java.text.DecimalFormat;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2021</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 *  
 * <p>Title: UTFormatter</p>
 * <p>Description: This class contains convenience methods used to display formatted numbers on our GUIs.
 *    TODO: It has become a real mess and needs to be redesigned and refactored by a junior developer.</p>
 *
 * @author Monte Seibel
 * @version 7.0
 * @formatter:on
 */
public class UTFormatter {
    public static final String CLASSNAME = "UTFormatter";
    public static final String TAB = "   ";

    public static final String PATTERN_PRICE = "###,###,##0.00";
    public static final String PATTERN_SHARES = "#,###,###";
    public static final String PATTERN_SHARES_F = "###,###,##0.0000000";
    public static final String PATTERN_NO_PENNIES = "#,###,###";
    public static final String PATTERN_KSHARES = "#####";
    public static final String PATTERN_PERCENT = "+###0.00;-###0.00";
    public static final String PATTERN_PERCENT_3DIGIT = "+###0.00;-###0.000";
    public static final String PATTERN_PERCENTPLUS = "+###0.00;-###0.00";
    public static final String PATTERN_FLOAT = "###,##0.00";
    public static final String PATTERN_FLOAT_ONE_DIGIT = "###,##0.0";

    /////////////////////////////////////////////////
    // CONSTRUCTORS
    /////////////////////////////////////////////////
    private UTFormatter() {
        // can't call
    }

    // Format Float number to be exactly: ###.###
    public static final String formatPrice(float value) {
        String methodname = "formatPrice:float";
        try {
            DecimalFormat myFormatter = new DecimalFormat(PATTERN_PRICE);
            String result = myFormatter.format(value);
            return result;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
        }
        return "Exc";
    }

    // Format Float number to be exactly: ###.###
    public static final String formatPrice(double value) {
        String methodname = "formatPrice:double";
        try {
            DecimalFormat myFormatter = new DecimalFormat(PATTERN_PRICE);
            String result = myFormatter.format(value);
            return result;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
        }
        return "Exc";
    }

    public static final String formatDoubleNoPennies(double value) {
        String methodname = "formatNumShares";
        try {
            DecimalFormat myFormatter = new DecimalFormat(PATTERN_NO_PENNIES);
            String result = myFormatter.format(value);
            return result;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
        }
        return "Exc";
    }

    // Will make 100,000 executedNumShares = 100K and format smaller number of share values appropriately.
    // Will right justify output.
    // numDigits is how many output spaces the display can take.
    // The minimum numDigits allowed is 4. Since the min. order size is 100 executedNumShares. and we must
    // also account for 100K as a display output value.
    public static final String formatShares(int value) {
        String methodname = "formatNumShares";
        try {
            DecimalFormat myFormatter = new DecimalFormat(PATTERN_SHARES);
            String result = myFormatter.format(value);
            return result;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
        }
        return "Exc";
    }

    public static final String formatShares(float value) {
        String methodname = "formatNumShares";
        try {
            DecimalFormat myFormatter = new DecimalFormat(PATTERN_SHARES_F);
            String result = myFormatter.format(value);
            return result;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
        }
        return "Exc";
    }

    // Will make 100,000 executedNumShares = 100K and format smaller number of share values appropriately.
    // Will right justify output.
    // numDigits is how many output spaces the display can take.
    // The minimum numDigits allowed is 4. Since the min. order size is 100 executedNumShares. and we must
    // also account for 100K as a display output value.
    public static final String formatKShares(int value) {
        String methodname = "formatNumShares";
        try {
            DecimalFormat myFormatter = new DecimalFormat(PATTERN_KSHARES);
            if (value < 1000) {
                String result = myFormatter.format(value);
                return result;
            } else {
                int numShares = value / 1000;
                String result = myFormatter.format(numShares) + "K";
                return result;
            }
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
        }
        return "Exc";
    }

    /**
     * @param value
     * @return
     */
    public static final String formatPercent(float value) {
        String methodname = "formatPercent";
        try {
            DecimalFormat myFormatter = new DecimalFormat(PATTERN_PERCENT);
            String result = myFormatter.format(value);
            return result;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
        }
        return "Exc";
    }

    public static final String formatPercent3Digit(float value) {
        String methodname = "formatPercent3Digit";
        try {
            DecimalFormat myFormatter = new DecimalFormat(PATTERN_PERCENT_3DIGIT);
            String result = myFormatter.format(value);
            return result;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
        }
        return "Exc";
    }

    public static final String formatPercent3Digit(double value) {
        String methodname = "formatPercent3Digit";
        try {
            DecimalFormat myFormatter = new DecimalFormat(PATTERN_PERCENT_3DIGIT);
            String result = myFormatter.format(value);
            return result;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
        }
        return "Exc";
    }

    /**
     * @param value
     * @return
     */
    public static final String formatPercentPlus(float value) {
        String methodname = "formatPercentPlus";
        try {
            DecimalFormat myFormatter = new DecimalFormat(PATTERN_PERCENTPLUS);
            String result = myFormatter.format(value);
            return result;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
        }
        return "Exc";
    }

    /**
     * @param value
     * @return
     */
    public static final String roundFloat(float value) {
        String methodname = "roundFloat";
        try {
            DecimalFormat myFormatter = new DecimalFormat(PATTERN_FLOAT);
            String result = myFormatter.format(value);
            return result;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
        }
        return "Exc";
    }

    public static final String roundDouble1Digit(double value) {
        String methodname = "roundDouble1Digit";
        try {
            DecimalFormat myFormatter = new DecimalFormat(PATTERN_FLOAT_ONE_DIGIT);
            String result = myFormatter.format(value);
            return result;
        } catch (Exception e) {
            System.out.println("Exception in " + CLASSNAME + "." + methodname + "; message [" + e.getMessage() + "]");
        }
        return "Exc";
    }

    public static String returnEmptyString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
