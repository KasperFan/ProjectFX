package com.system.DAO.entity;

public class Astronaut {
    private int id;
    private String name;
    private int age;
    private String sex;
    private String photoUrl;

    public Astronaut(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Astronaut(int id, String name, int age, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Astronaut(int id, String name, int age, String sex, String photoUrl) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.photoUrl = photoUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    @Override
    public String toString() {
        return "Astronaut{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", photoName='" + photoUrl + '\'' +
                '}';
    }
}
