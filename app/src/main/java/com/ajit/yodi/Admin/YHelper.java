package com.ajit.yodi.Admin;

public class YHelper {

    String disn,yname,yreps,imageuri;

    public YHelper(){

    }

    public YHelper(String disn, String yname, String yreps,String imageuri) {
        this.disn = disn;
        this.yname = yname;
        this.yreps = yreps;
        this.imageuri = imageuri;
    }

    public String getDisn() {
        return disn;
    }

    public void setDisn(String disn) {
        this.disn = disn;
    }

    public String getYname() {
        return yname;
    }

    public void setYname(String yname) {
        this.yname = yname;
    }

    public String getYreps() {
        return yreps;
    }

    public void setYreps(String yreps) {
        this.yreps = yreps;
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }
}
