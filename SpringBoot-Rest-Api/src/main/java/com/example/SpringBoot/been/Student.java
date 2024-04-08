package com.example.SpringBoot.been;

public class Student {
    private   int Id;
    private   String  FirstName;
    private String LastName;



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Student(int id, String firstName, String lastName) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
    }
}
