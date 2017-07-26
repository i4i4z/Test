package com.example.pc.mvptest.model;

import android.util.Log;

import com.example.pc.mvptest.BuildConfig;
import com.example.pc.mvptest.model.common.CommonConstant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author
 * @version 1.0
 * @date 2017/7/22
 */
public class AppRetrofit {

    private static Retrofit retrofit=null;
    
    public synchronized static Retrofit getRetrofit(){
        if(retrofit==null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            initOkhttpBuilder(builder);
            OkHttpClient okHttpClient = builder.build();
            Log.d("log",CommonConstant.baseUrl);
            retrofit = new Retrofit.Builder()
                    .baseUrl(CommonConstant.baseUrl+"/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
    
    public static void initOkhttpBuilder(OkHttpClient.Builder mBuilder){
        setTimeOut(mBuilder);
        setCommonParamInterceptor(mBuilder);
        setHeadInterceptor(mBuilder);
        setLogInterceptor(mBuilder);
        setChache(mBuilder);
    }
    
    
    //设置请求超时,重连
    private static void setTimeOut(OkHttpClient.Builder okHttpClient){
        okHttpClient.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
    }

    //设置参数
    private static void setCommonParamInterceptor(OkHttpClient.Builder builder){
                //公共参数
//                Interceptor commonParamInterceptor = new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request request = chain.request();
//                        HttpUrl httpUrl = request.url().newBuilder()
//                                .addQueryParameter("platform", "adnroid")
//                                .addQueryParameter("version", "1.2.0").build();
//                        Request paramRequest = request.newBuilder().url(httpUrl).build();
//                        return chain.proceed(paramRequest);
//                    }
//                };
//                builder.addInterceptor(commonParamInterceptor);
    }

    //设置头
    private static void setHeadInterceptor(OkHttpClient.Builder builder){
        Interceptor headIntterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request headRequest = request.newBuilder()
                        //                        .header("","")
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .method(request.method(), request.body())
                        .build();
                return chain.proceed(request);
            }
        };
        builder.addInterceptor(headIntterceptor);
    }
    
    //设置log拦截
    private static void setLogInterceptor(OkHttpClient.Builder builder){
//        if (BuildConfig.DEBUG) {
//            //log信息拦截器  
//            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            //设置Debug Log模式
//            builder.addInterceptor(httpLoggingInterceptor);
//        }
    }
    
    private static void setChache(OkHttpClient.Builder builder){
//        final File chachefile = new File(Application.getContext().getExternalCacheDir(), "HttpCache");
//        final Cache cache = new Cache(chachefile, 1024 * 1024 * 50);//缓存文件  
//        Interceptor cacheInterceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                if (!AppUtils.isNetworkReachable(Application.getContext())) {
//                    request = request.newBuilder()
//                            .cacheControl(CacheControl.FORCE_CACHE)
//                            .build();
//                }
//                Response response = chain.proceed(request);
//                if (AppUtils.isNetworkReachable(AppAplication.getContext())) {
//                    int maxAge = 0;
//
//                    // 有网络时 设置缓存超时时间0个小时  
//                    response.newBuilder()
//                            .header("Cache-Control", "public, max-age=" + maxAge)
//                            .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效  
//                            .build();
//                } else {
//                    //无网络时，设置超时为4周  
//                    int maxStale = 60 * 60 * 24 * 28;
//                    response.newBuilder()
//                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                            .removeHeader("Pragma")
//                            .build();
//                }
//
//                return response;
//            }
//        };
//        builder.cache(cache).addInterceptor(cacheInterceptor);
    }
}
