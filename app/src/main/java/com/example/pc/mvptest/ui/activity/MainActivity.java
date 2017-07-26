package com.example.pc.mvptest.ui.activity;

import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.*;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.pc.mvptest.MyAppliacation;
import com.example.pc.mvptest.R;
import com.example.pc.mvptest.model.User;
import com.example.pc.mvptest.model.entity.Teacher;
import com.example.pc.mvptest.presenter.MainPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity{
    
    Observable mObservable;
    TextView mTextView;
    String[][] mStrings = {{"aa","bb"},{"cc"},{"dd","ee","ff"}};

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.main_ta);
        MyAppliacation myAppliacation = (MyAppliacation)getApplication();
        Teacher teacher = new Teacher();
        teacher.setName("周A");
        teacher.setAge(24);
        teacher.setInfo("骚B");
//        teacher.set_id(Long.MAX_VALUE);
        myAppliacation.getDBManager().insertEntity(teacher);
        //rxjava测试
        rxjavaTest();
        //retrofit测试
        try {
            retrofitTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rxjavaLift();

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SQlLiteActivity.class);
                startActivity(intent);
            }
        });
    }

    
    public void rxjavaLift(){
        Observable.just("String").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                if (s == "Strin") {
                    return "yes";
                } else {
                    return "no";
                }
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("dd","yes or no: "+s);
            }
        });
        Observable.from(mStrings).flatMap(new Func1<String[], Observable<String>>() {
            @Override
            public Observable<String> call(String[] strings) {
                return Observable.from(strings);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("dd","string:  "+s);
            }
        });
    }

    public void retrofitTest() throws IOException {
        MainPresenter mainPresenter= new MainPresenter();
        mainPresenter.getUser(new Observer<User>() {
            @Override
            public void onCompleted() {
                
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(User user) {
                Log.d("aa","user: "+user.toString());
            }
        });
//        MainPresenter mainPresenter= new MainPresenter();
//        Call<ResponseBody> call = mainPresenter.getUsers();
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.d("log","isSuccessful: "+response.isSuccessful());
//                try {
//                    Log.d("log","body: "+response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                t.printStackTrace();
//                Log.d("aa","onFailure"+t.toString());
//            }
//        });
    }
    
    public void rxjavaTest(){
        Log.d("log", "main,currentThread: " + android.os.Process.myTid());
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                Log.d("log", "currentThread: " + Process.myTid());
                subscriber.onNext("");
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread()) // 指定主线程
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //                        mTextView.setText("doSetText"); // 需要在主线程执行
                        Log.d("log", "doOnSubscribe,currentThread: " + Process.myTid());
                    }
                })
                .subscribeOn(Schedulers.newThread())
                        //                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        Log.d("log", "doOnNext,currentThread: " + android.os.Process.myTid());
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Object>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.d("log", "onStart,currentThread: " + android.os.Process.myTid());
                    }

                    @Override
                    public void onCompleted() {
                        Log.d("log", "onCompleted,currentThread: " + android.os.Process.myTid());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });

        Log.d("log", "OnActivityCreate");
    }
}
