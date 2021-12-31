package com.sir.dbms_mini.SQLProduct;

public class ModelSQL {
    String eName, eCategory, eWidth, eHeight, eDesc, eCost;

    public ModelSQL(String eName, String eCategory, String eWidth, String eHeight, String eDesc, String eCost) {
        this.eName = eName;
        this.eCategory = eCategory;
        this.eWidth = eWidth;
        this.eHeight = eHeight;
        this.eDesc = eDesc;
        this.eCost = eCost;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteCategory() {
        return eCategory;
    }

    public void seteCategory(String eCategory) {
        this.eCategory = eCategory;
    }

    public String geteWidth() {
        return eWidth;
    }

    public void seteWidth(String eWidth) {
        this.eWidth = eWidth;
    }

    public String geteHeight() {
        return eHeight;
    }

    public void seteHeight(String eHeight) {
        this.eHeight = eHeight;
    }

    public String geteDesc() {
        return eDesc;
    }

    public void seteDesc(String eDesc) {
        this.eDesc = eDesc;
    }

    public String geteCost() {
        return eCost;
    }

    public void seteCost(String eCost) {
        this.eCost = eCost;
    }
}
