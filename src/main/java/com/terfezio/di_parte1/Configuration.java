package com.terfezio.di_parte1.Practica6;

public class Configuration {
    private Boolean png;
    private Boolean jpg;
    private Boolean gif;
    private String path;
    private double seconds;

    public Configuration(Boolean png, Boolean jpg, Boolean gif, String path, float seconds) {
        this.png = png;
        this.jpg = jpg;
        this.gif = gif;
        this.path = path;
        this.seconds = seconds;
    }

    public Configuration(String path) {
        this.path = path;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }

    public Boolean getPng() {
        return png;
    }

    public double getSeconds() {
        return seconds;
    }

    public void setPng(boolean png) {
        this.png = png;
    }

    public Boolean getJpg() {
        return jpg;
    }

    public void setJpg(boolean jpg) {
        this.jpg = jpg;
    }

    public Boolean getGif() {
        return gif;
    }

    public void setGif(boolean gif) {
        this.gif = gif;
    }

    public String getPath() {
        return path;
    }
}
