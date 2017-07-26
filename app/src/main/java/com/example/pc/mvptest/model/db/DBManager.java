package com.example.pc.mvptest.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.pc.mvptest.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2017/7/24
 */
public class DBManager {
    private DBHelper mHelper;
    private SQLiteDatabase db;
    
    public DBManager(Context context){
        mHelper = new DBHelper(context);
        db = mHelper.getWritableDatabase();
    }
    
    public void add(List<Student> students){
        db.beginTransaction();
        try {
            for(Student student : students){
                db.execSQL("INSERT INTO student VALUES(null, ?, ?, ?)", new Object[]{student.getName(), student.getAge(), student.getInfo()});
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }
    
    public void updateAge(Student student){
        ContentValues values = new ContentValues();
        values.put("age",student.getAge());
        db.update("student", values, "name = ?", new String[]{student.getName()});
    }
    
    public void deleteOldStudent(Student student){
        db.delete("student", "age >= ?", new String[]{String.valueOf(student.getAge())});
    }

    public List<Student> query() {
        ArrayList<Student> students = new ArrayList<Student>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            Student student = new Student();
            student.set_id(c.getInt(c.getColumnIndex("_id")));
            student.setName(c.getString(c.getColumnIndex("name")));
            student.setAge(c.getInt(c.getColumnIndex("age")));
            student.setInfo(c.getString(c.getColumnIndex("info")));
            students.add(student);
        }
        c.close();
        return students;
    }

    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM student", null);
        return c;
    }

    public void closeDB() {
        db.close();
    }
}
