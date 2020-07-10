package com.beshoykamal.movieappui.data;

import com.beshoykamal.movieappui.model.Modelretro;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("result")
    @Expose
    private List<Modelretro> students = null;

    public List<Modelretro> getStudents() {
        return students;
    }

    public void setStudents(List<Modelretro> students) {
        this.students = students;
    }
}
