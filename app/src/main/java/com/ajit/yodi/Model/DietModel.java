package com.ajit.yodi.Model;

public class DietModel {

    public static final int Donts_Head = 0;
    public static final int Diet_Dos_Layout = 1;
    public static final int Diet_Donts_Layout = 2;

    private int viewType;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    // Dos and Dont's Layout
    private int diet_img;
    private String diet_name;
    private String diet_desc;

    public DietModel(int viewType, int diet_img, String diet_name, String diet_desc) {
        this.viewType = viewType;
        this.diet_img = diet_img;
        this.diet_name = diet_name;
        this.diet_desc = diet_desc;
    }

    public int getDiet_img() {
        return diet_img;
    }

    public void setDiet_img(int diet_img) {
        this.diet_img = diet_img;
    }

    public String getDiet_name() {
        return diet_name;
    }

    public void setDiet_name(String diet_name) {
        this.diet_name = diet_name;
    }

    public String getDiet_desc() {
        return diet_desc;
    }

    public void setDiet_desc(String diet_desc) {
        this.diet_desc = diet_desc;
    }

    // Dos and Dont's Layout


    // Dont's Head

    private int dont_head_img;
    private String dont_head;

    public DietModel(int viewType, int dont_head_img, String dont_head) {
        this.viewType = viewType;
        this.dont_head_img = dont_head_img;
        this.dont_head = dont_head;
    }

    public int getDont_head_img() {
        return dont_head_img;
    }

    public void setDont_head_img(int dont_head_img) {
        this.dont_head_img = dont_head_img;
    }

    public String getDont_head() {
        return dont_head;
    }

    public void setDont_head(String dont_head) {
        this.dont_head = dont_head;
    }

    // Dont's Head

}
//*****************************************************************************
//*****************************************************************************

//    public static final int Diet_Dos_Layout = 1;
//    public static final int Diet_Donts_Layout = 2;
//
//    private int viewType;
//
//    public int getViewType() {
//        return viewType;
//    }
//
//    public void setViewType(int viewType) {
//        this.viewType = viewType;
//    }
//
//    int diet_img;
//    String diet_name;
//    String diet_desc;
//
//    public DietModel(int viewType,int diet_img, String diet_name, String diet_desc) {
//        this.viewType = viewType;
//        this.diet_img = diet_img;
//        this.diet_name = diet_name;
//        this.diet_desc = diet_desc;
//    }
//
//    public int getDiet_img() {
//        return diet_img;
//    }
//
//    public void setDiet_img(int diet_img) {
//        this.diet_img = diet_img;
//    }
//
//    public String getDiet_name() {
//        return diet_name;
//    }
//
//    public void setDiet_name(String diet_name) {
//        this.diet_name = diet_name;
//    }
//
//    public String getDiet_desc() {
//        return diet_desc;
//    }
//
//    public void setDiet_desc(String diet_desc) {
//        this.diet_desc = diet_desc;
//    }