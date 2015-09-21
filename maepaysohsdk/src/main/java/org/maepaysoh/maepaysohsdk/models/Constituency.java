package org.maepaysoh.maepaysohsdk.models;

import java.io.Serializable;

/**
 * Created by Ye Lin Aung on 15/08/03.
 */
public class Constituency implements Serializable {
  private String name;
  private String number;
  private String ST_PCODE;
  private String DT_PCODE;
  private String TS_PCODE;
  private String AM_PCODE;
  private String parent;


  public Constituency() {
  }


  /**
   * @return The name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name The name
   */
  public void setName(String name) {
    this.name = name;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getST_PCODE() {
    return ST_PCODE;
  }

  public void setST_PCODE(String ST_PCODE) {
    this.ST_PCODE = ST_PCODE;
  }

  public String getDT_PCODE() {
    return DT_PCODE;
  }

  public void setDT_PCODE(String DT_PCODE) {
    this.DT_PCODE = DT_PCODE;
  }

  public String getTS_PCODE() {
    return TS_PCODE;
  }

  public void setTS_PCODE(String TS_PCODE) {
    this.TS_PCODE = TS_PCODE;
  }

  public String getAM_PCODE() {
    return AM_PCODE;
  }

  public void setAM_PCODE(String AM_PCODE) {
    this.AM_PCODE = AM_PCODE;
  }

  public String getParent() {
    return parent;
  }

  public void setParent(String parent) {
    this.parent = parent;
  }
}
