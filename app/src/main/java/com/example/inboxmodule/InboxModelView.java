package com.example.inboxmodule;

public class InboxModelView {
    public String id; //Image Link
    public String matter; //News Title
    public String brief;// News Body
    public String date;
    public String read;
    public InboxModelView(String matter, String id,String brief,String date,String read)
    {
        this.id = id;
        this.matter = matter;
        this.brief=brief;
        this.date =date;
        this.read=read;
    }

    public String getID() {
        return id;
    }

    public String getMatter() {
        return matter;
    }
    public String getBrief() {
        return brief;
    }
    public String getDate() {
        return date;
    }
    public String getRead() {
        return read;
    }

}
