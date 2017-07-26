package com.example.pc.mvptest.presenter;

import android.util.Log;

import com.example.pc.mvptest.model.AppRetrofit;
import com.example.pc.mvptest.model.User;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author
 * @version 1.0
 * @date 2017/7/20
 */
public class MainPresenter{
    
    private MainHttpRequest mMainHttpRequest = AppRetrofit.getRetrofit().create(MainHttpRequest.class);
    
    private interface MainHttpRequest{
        //  http://api.geonames.org/citiesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&lang=de&username=dem
        @GET("citiesJSON")
        public Call<ResponseBody> getUsers(
                @Query("north") double north
                ,@Query("south") double south
                ,@Query("east") double east
                ,@Query("west") double west
                ,@Query("lang") String lang
                ,@Query("username") String username
                );
        @GET("citiesJSON")
        public Observable<User> getUser(
                @Query("north") double north
                ,@Query("south") double south
                ,@Query("east") double east
                ,@Query("west") double west
                ,@Query("lang") String lang
                ,@Query("username") String username
                );
    }
    
    public Call<ResponseBody> getUsers(){
        return mMainHttpRequest.getUsers(44.1,-9.9,-22.4,55.2,"de","dem");
    }    
    public void getUser(Observer<User> observer){
        setSubscribe(mMainHttpRequest.getUser(44.1,-9.9,-22.4,55.2,"de","dem"),observer);
    }

    /**
     * 插入观察者
     * @param observable
     * @param observer
     * @param <T>
     */
    public <T> void setSubscribe(Observable<T> observable, Observer<T> observer){
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
