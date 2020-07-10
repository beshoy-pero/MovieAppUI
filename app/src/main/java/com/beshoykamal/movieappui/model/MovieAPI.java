package com.beshoykamal.movieappui.model;

public class MovieAPI {

    private String tiltle;
    private String desctiption;
    private String thumpmail;
    private int id;
    private int ratvote;
    private String background;
    private String dateimg;

    public MovieAPI(String tiltle, String thumpmail, int id) {
        this.tiltle = tiltle;
        this.thumpmail = thumpmail;
        this.id = id;
    }

    public MovieAPI(String tiltle, String desctiption, String thumpmail, int id) {
        this.tiltle = tiltle;
        this.desctiption = desctiption;
        this.thumpmail = thumpmail;
        this.id = id;
    }

    public MovieAPI(String tiltle, String desctiption, String thumpmail, int id, int ratvote, String background, String dateimg) {
        this.tiltle = tiltle;
        this.desctiption = desctiption;
        this.thumpmail = thumpmail;
        this.id = id;
        this.ratvote = ratvote;
        this.background = background;
        this.dateimg = dateimg;
    }

    public String getTiltle() {
        return tiltle;
    }

    public void setTiltle(String tiltle) {
        this.tiltle = tiltle;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
    }

    public String getThumpmail() {
        return thumpmail;
    }

    public void setThumpmail(String thumpmail) {
        this.thumpmail = thumpmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRatvote() {
        return ratvote;
    }

    public void setRatvote(int ratvote) {
        this.ratvote = ratvote;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getDateimg() {
        return dateimg;
    }

    public void setDateimg(String dateimg) {
        this.dateimg = dateimg;
    }
}
