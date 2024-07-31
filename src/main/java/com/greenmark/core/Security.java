package com.greenmark.core;

import com.greenmark.common.core.Labels;

import java.util.Vector;

public class Security
{
   /** This is the class name. */
   private static final String CLASSNAME = "Security";

   /** Used by the database **/
   private long id;           //leave as null for hibernate
   private int active = Labels.OBJECT_ACTIVE;
   /**************************/

   private String name;
   private String symbol;

	private Vector exchanges = null;
	//private Long exchange_id;

   public Security()
   {
   }

   public Security( String name, String symbol)
   {
      this.name = name;
      this.symbol = symbol;
   }

  public int getActive() {
    return active;
  }

  public String getName() {
    return name;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setActive(int active) {
    this.active = active;
  }

  public String dbSummary()
  {
     StringBuffer stb = new StringBuffer();
     stb.append( " > > " + CLASSNAME + " :: ");
     stb.append( "id [" +id+ "] ");
     stb.append( "active [" +active+ "] ");
     return stb.toString();
  }


   public String toString()
   {
      return "";
   }

   public long getId()
   {
      return id;
   }

   public void setId(long id)
   {
      this.id = id;
   }

   public String getClassname()
   {
      return CLASSNAME;
   }

   public Vector getExchanges()
   {
      return exchanges;
   }

   public void setExchanges(Vector exchanges)
   {
      this.exchanges = exchanges;
   }

}
