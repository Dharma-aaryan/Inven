package com.sir.dbms_mini.FirebaseProduct;

import android.widget.EditText;

import java.io.Serializable;

public class ModelProduct implements Serializable {
    private String id;
    private String proName;
    private String proCategory;
    private String proWidth;
    private String proHeight;
    private String proDesc;
    private String proCost;

    public ModelProduct() {
    }

    public ModelProduct(String proName, String proCategory, String proWidth, String proHeight, String proDesc, String proCost) {
        this.proName = proName;
        this.proCategory = proCategory;
        this.proWidth = proWidth;
        this.proHeight = proHeight;
        this.proDesc = proDesc;
        this.proCost = proCost;
    }

//    public ModelProduct(EditText name, EditText category, EditText width, EditText height, EditText description) {
//    }


    public String getProCost() {
        return proCost;
    }

    public void setProCost(String proCost) {
        this.proCost = proCost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProCategory() {
        return proCategory;
    }

    public void setProCategory(String proCategory) {
        this.proCategory = proCategory;
    }

    public String getProWidth() {
        return proWidth;
    }

    public void setProWidth(String proWidth) {
        this.proWidth = proWidth;
    }

    public String getProHeight() {
        return proHeight;
    }

    public void setProHeight(String proHeight) {
        this.proHeight = proHeight;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }
}
