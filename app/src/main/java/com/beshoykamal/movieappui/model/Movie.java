package com.beshoykamal.movieappui.model;

public class Movie {

    private String tiltle;
    private String desctiption;
    private int thumpmail;
    private String studio;
    private String ratimg;
    private String streeminglink;
    private int coverphoto;
    private String id;

    public Movie(String tiltle, String desctiption, int thumpmail, String studio, String ratimg, String streeminglink) {
        this.tiltle = tiltle;
        this.desctiption = desctiption;
        this.thumpmail = thumpmail;
        this.studio = studio;
        this.ratimg = ratimg;
        this.streeminglink = streeminglink;
    }


    public Movie(String tiltle, int thumpmail, String id) {
        this.tiltle = tiltle;
        this.thumpmail = thumpmail;
        this.id = id;
    }

    public Movie(String tiltle, int thumpmail, int coverphoto) {
        this.tiltle = tiltle;
        this.thumpmail = thumpmail;
        this.coverphoto = coverphoto;
    }

    public int getCoverphoto() {
        return coverphoto;
    }

    public void setCoverphoto(int coverphoto) {
        this.coverphoto = coverphoto;
    }

    public Movie(String tiltle, int thumpmail) {
        this.tiltle = tiltle;
        this.thumpmail = thumpmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getThumpmail() {
        return thumpmail;
    }

    public void setThumpmail(int thumpmail) {
        this.thumpmail = thumpmail;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getRatimg() {
        return ratimg;
    }

    public void setRatimg(String ratimg) {
        this.ratimg = ratimg;
    }

    public String getStreeminglink() {
        return streeminglink;
    }

    public void setStreeminglink(String streeminglink) {
        this.streeminglink = streeminglink;
    }
}
