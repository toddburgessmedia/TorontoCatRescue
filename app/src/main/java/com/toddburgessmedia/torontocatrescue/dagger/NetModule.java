package com.toddburgessmedia.torontocatrescue.dagger;

import android.app.Application;

import com.toddburgessmedia.torontocatrescue.R;
import com.toddburgessmedia.torontocatrescue.model.PetListModel;
import com.toddburgessmedia.torontocatrescue.model.PetListDataModel;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

@Module
public class NetModule {

    public NetModule () {

    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        return client;
    }

    @Provides
    @Singleton
    public Retrofit getRetrofitAdoptAPet (OkHttpClient client) {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl(" http://api.adoptapet.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    @Provides
    public PetListModel getPetListModel (Retrofit retrofit, Application application) {

        String apikey = application.getString(R.string.api_key);
        String shelterid = application.getString(R.string.shelter_id);

        return new PetListModel(retrofit, apikey, shelterid);
    }

    @Provides
    public PetListDataModel getPetListDataModel (Retrofit retrofit, Application application) {

        String apikey = application.getString(R.string.api_key);
        String shelterid = application.getString(R.string.shelter_id);

        return (PetListDataModel) new PetListModel(retrofit, apikey, shelterid);
    }


}
