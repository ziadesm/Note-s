package com.android.echomachine.mindly.modle;

public class NoteModle {

    private int id;
    private String title;
    private String date;
    private String time;
    private String text;

    public NoteModle() {

    }

    public NoteModle(String title, String date, String text, String time) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.text = text;
    }

    public NoteModle(int id, String title, String date, String text, String time) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
