package com.example.pc.mvptest.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * @author
 * @version 1.0
 * @date 2017/7/24
 */
public class CommonDBUtils {

    Context context;
    SQLiteDatabase db;
    
    public CommonDBUtils(Context context){
        this.context = context;
        if(null == db){
            db =getDb();
        }
        dos();
    }
    
    public SQLiteDatabase getDb(){
        if(null == db){
            return db = context.openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);
        }else {
            return db;
        }
    }
    
    public void dos(){
        //打开或创建test.db数据库
//        db = context.openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS student");
        //创建student表
        db.execSQL("CREATE TABLE student (_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, age SMALLINT)");

        db.execSQL("INSERT INTO student VALUES (NULL, ?, ?)", new Object[]{"杨A", 25});

        ContentValues contentValues = new ContentValues();
        contentValues.put("name","杨B");
        contentValues.put("age", 21);
        db.insert("student", null, contentValues);
        
        contentValues =new ContentValues();
        contentValues.put("age", 26);
        db.update("student", contentValues, "name = ?", new String[]{"杨A"});

        Cursor cursor = db.rawQuery("SELECT * FROM student WHERE age >= ?", new String[]{"20"});
        while (cursor.moveToNext()){
            Log.d("dd","id: "+cursor.getInt(cursor.getColumnIndex("_id"))
                    +",name: "+cursor.getString(cursor.getColumnIndex("name"))
                    +",age: "+cursor.getInt(cursor.getColumnIndex("age")));
        }

        //删除数据  
//        db.delete("person", "age < ?", new String[]{"25"});

        cursor.close();

        //删除test.db数据库  
        //deleteDatabase("test.db");
    }
    
}
