package com.georgebrown.prototype1.Model;

public class Restaurant {

    private String name,address,city,state,zip,tasks,tags,phone,email;


    public Restaurant(String name, String address, String city, String state, String zip, String tasks, String tags, String phone, String email) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.tasks = tasks;
        this.tags = tags;
        this.phone = phone;
        this.email = email;


    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getTasks() {
        return tasks;
    }

    public String getTags() {
        return tags;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }



}
