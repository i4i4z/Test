package com.example.pc.mvptest.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

/**
 * @author
 * @version 1.0
 * @date 2017/7/26
 */
@Entity
public class Teacher {
    @Property(nameInDb = "id")
    @Id(autoincrement = true)
    private Long _id;
    private String name;
    private int age;
    private String info;
    public String getInfo() {
        return this.info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    @Generated(hash = 2079059719)
    public Teacher(Long _id, String name, int age, String info) {
        this._id = _id;
        this.name = name;
        this.age = age;
        this.info = info;
    }
    @Generated(hash = 1630413260)
    public Teacher() {
    }
}
