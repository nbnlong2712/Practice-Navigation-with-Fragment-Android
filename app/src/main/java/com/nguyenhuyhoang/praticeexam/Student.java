package com.nguyenhuyhoang.praticeexam;

import java.io.Serializable;
import java.util.UUID;

public class Student implements Serializable {
    private String id;
    private String name;
    private String region;
    private String birth;

    public Student(String name, String region, String birth) {
        this.name = name;
        this.region = region;
        this.birth = birth;
    }

    public Student(String id, String name, String region, String birth) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.birth = birth;
    }

    //---------------------------------------------------------


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
