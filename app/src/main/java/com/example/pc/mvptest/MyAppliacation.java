package com.example.pc.mvptest;

import android.app.Application;
import android.util.Log;

import com.example.pc.mvptest.model.db.CommonDBUtils;
import com.example.pc.mvptest.model.greendao.DBManager;
import com.example.pc.mvptest.model.greendao.DaoMaster;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * @author
 * @version 1.0
 * @date 2017/7/24
 */
public class MyAppliacation extends Application{
    
    
    @Override
    public void onCreate() {
        super.onCreate();
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        UMShareAPI.get(this);
        Log.d("dd", "sadasdasdasdasd===============");
//        CommonDBUtils commonDBUtils = new CommonDBUtils(this);ssss
    }
    
    public DBManager getDBManager(){
        return DBManager.getDBManager(this);
    }
    
}
