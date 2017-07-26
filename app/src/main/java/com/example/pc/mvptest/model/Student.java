package com.example.pc.mvptest.model;

/**
 * @author
 * @version 1.0
 * @date 2017/7/24
 */
public class Student {
    int _id;
    String name;
    int age;
    String info;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Student{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", info='" + info + '\'' +
                '}';
    }
}
