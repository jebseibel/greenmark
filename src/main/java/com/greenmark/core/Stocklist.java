package com.greenmark.core;

import javax.swing.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;


/////////////////////////////////////////////////
// START CLASS: Stocklist
/////////////////////////////////////////////////

public class Stocklist {

    /**
     * This is the class name.
     */
    public static final String CLASSNAME = "Stocklist";

    public Hashtable stocklist = new Hashtable();
    public String name = "";

////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTORS
////////////////////////////////////////////////////////////////////////////////

    public Stocklist() {
    }

    public Stocklist(String name) {
        this.name = name;
    }

////////////////////////////////////////////////////////////////////////////////
// PUBLIC METHODS
////////////////////////////////////////////////////////////////////////////////

    public final void clearStocklist() {
        this.stocklist.clear();
    }

    /**
     * Adds a stock to the stock list.
     *
     * @param stock the stock to add.
     */
    public final void addStock(Stock stock) {
        //this.stocklist.put( stock.getSymbol(), stock );
    }

    /**
     * Adds hashtable of stocks to the stock list.
     */
    public final void addStocks(Hashtable stocks) {
        this.stocklist.putAll(stocks);
    }

    /**
     * Adds hashtable of stocks to the stock list.
     */
    public final void addStocks(Stocklist stocklist) {
        Hashtable stocks = stocklist.getStocks();
        this.stocklist.putAll(stocks);
    }

    /**
     * Returns a value depending on if this stock is in this list
     */
    public final boolean hasStock(String symbol) {
        //Hashtable stocks = stocklist.getStocks();
        return stocklist.containsKey(symbol);
    }


    /**
     * The string representation of this object.
     *
     * @return String
     */
    public String toString() {
        StringBuffer buff = new StringBuffer(10240);
        buff.append("###########################################################\n");
        buff.append("## STOCK LIST\n");
        buff.append("###########################################################\n");
        for (Enumeration e = this.stocklist.elements(); e.hasMoreElements(); ) {
            Stock stock = (Stock) e.nextElement();
            buff.append(stock.toString());
        }
        return (buff.toString());
    }

    /**
     * The string representation of this object.
     *
     * @return String
     */
    public String toStringSymbols() {
        String buff = "";
        boolean first = true;
        for (Enumeration e = this.stocklist.elements(); e.hasMoreElements(); ) {
            Stock stock = (Stock) e.nextElement();
            if (first) {
                //buff.append(stock.getSymbol());
                first = false;
            }
            //else
            //buff.append( ", " + stock.getSymbol() );
        }
        return (buff);
    }

    /**
     * Removes the stock specified by the symbol passed in.
     *
     * @param symbol the stock to remove.
     * @return The removed stock.
     */
    public final Stock removeStock(String symbol) {
        return ((Stock) this.stocklist.remove(symbol));
    }

    /**
     * Removes the stock specified by the stock passed in.
     *
     * @param stock the stock to remove.
     * @return The removed stock.
     */
    public final Stock removeStock(Stock stock) {

        return ((Stock) this.stocklist.remove(stock.toString()));
    }

    /**
     * Retrieves a specific stock
     *
     * @param symbol of the stock to return.
     * @return the stock or null if the stock you requested is not in the list.
     */
    public final Stock getStock(String symbol) {
        return ((Stock) this.stocklist.get(symbol));
    }

    /**
     * Returns a list of stock symbols in this stock list.
     *
     * @return Vector of symbols.
     */
    public final Set getSymbols() {
        return (this.stocklist.keySet());
    }

    /**
     * Returns all the Stock objects held by this list.
     */
    public final Hashtable getStocks() {
        return this.stocklist;
    }

    /**
     * Returns the number of stocks held in this list
     */
    public final int size() {
        return this.stocklist.size();
    }

    /**
     * Returns all the Stock objects held by this list.
     */
    public final String getStocksNameAndSymbol(String endline) {
        String stb = "";

        for (Enumeration e = this.stocklist.keys(); e.hasMoreElements(); ) {
            String name = (String) e.nextElement();
            Stock valu = (Stock) this.stocklist.get(name);
            //stb.append( valu.getName() + ":" + valu.getSymbol() + endline );
        }

        return stb;
    }

    /**
     * Returns a DefaultListModel of candidates
     *
     * @return DefaultListModel
     */
    public final DefaultListModel getDefaultListModel() {
        DefaultListModel defaultList = new DefaultListModel();

        Iterator iSM = this.stocklist.keySet().iterator();
        while (iSM.hasNext()) {
            String name = (String) iSM.next();
            Stock stock = (Stock) this.stocklist.get(name);
            defaultList.addElement(stock);
        }

        return defaultList;
    }

    public Hashtable getStocklist() {
        return stocklist;
    }

}
