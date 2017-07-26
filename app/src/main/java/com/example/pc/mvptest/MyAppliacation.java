package com.example.pc.mvptest;

import android.app.Application;
import android.util.Log;

import com.example.pc.mvptest.model.db.CommonDBUtils;
import com.example.pc.mvptest.model.greendao.DBManager;
import com.example.pc.mvptest.model.greendao.DaoMaster;

/**
 * @author
 * @version 1.0
 * @date 2017/7/24
 */
public class MyAppliacation extends Application{
    
    
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("dd", "sadasdasdasdasd===============");
//        CommonDBUtils commonDBUtils = new CommonDBUtils(this);
    }
    
    public DBManager getDBManager(){
        return DBManager.getDBManager(this);
    }
    
}
