package com.example.pdfsaver;

class DataModel {
    private String name, contactNumber;

    DataModel(String name, String contactNumber){
        this.name = name;
        this.contactNumber = contactNumber;
    }

    String getName(){ return name; }
    String getContactNumber(){ return contactNumber; }
}
