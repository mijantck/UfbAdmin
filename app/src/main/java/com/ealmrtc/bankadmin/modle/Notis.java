package com.ealmrtc.bankadmin.modle;

public class Notis {
    String id ;
    String Name;
    String Details;

    public Notis() {
    }

    public Notis(String id, String name, String details) {
        this.id = id;
        Name = name;
        Details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
