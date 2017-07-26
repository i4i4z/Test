package com.example.pc.mvptest.model.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pc.mvptest.model.entity.Teacher;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

/**
 * @author
 * @version 1.0
 * @date 2017/7/27
 */

public class DBManager {
    private static final String tableName="test.db";
    DaoMaster.DevOpenHelper mDevOpenHelper;
//    DaoMaster mDaoMaster;
//    DaoSession mDaoSession;
    Context mContext;
    private static DBManager dBManager;
    
    public DBManager(Context context){
        mContext = context;
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context,tableName,null);
    }
    
    public static DBManager getDBManager(Context context){
        if(null == dBManager){
            synchronized (DBManager.class){
                if(null == dBManager){
                    dBManager = new DBManager(context);
                }
            }
        }
        return dBManager;
    }
    
    private SQLiteDatabase getWritableDatase(){
        if(null == mDevOpenHelper){
            mDevOpenHelper = new DaoMaster.DevOpenHelper(mContext,tableName,null);
        }
        return mDevOpenHelper.getWritableDatabase();
    }
    
    private SQLiteDatabase getReadableDatase(){
        if(null==mDevOpenHelper){
            mDevOpenHelper = new DaoMaster.DevOpenHelper(mContext,tableName,null);
        }
        return mDevOpenHelper.getReadableDatabase();
    }
    
    public <T> void insertEntity(T t){
        if(null==t){
            return;
        }
        DaoMaster master = new DaoMaster(getWritableDatase());
        DaoSession session = master.newSession();
        if(t instanceof Teacher){
            TeacherDao teacherDao = session.getTeacherDao();
            Teacher teacher = (Teacher) t;
            Log.d("ee","id: "+teacher.get_id());
            teacherDao.insert((Teacher) t);
        }
        
    }
    
    public <T> void deleteEntity(T t){
        if(null==t){
            return;
        }
        if(t instanceof Teacher){
            DaoMaster master = new DaoMaster(getWritableDatase());
            DaoSession session = master.newSession();
            TeacherDao teacherDao = session.getTeacherDao();
            QueryBuilder<Teacher> queryBuilder = teacherDao.queryBuilder();
//            queryBuilder.where().asdasdsad
//            DeleteQuery<T> deleteQuery = TeacherDao.s 
        }
    }
    
    
    
}
