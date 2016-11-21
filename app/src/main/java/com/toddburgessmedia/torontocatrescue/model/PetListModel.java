package com.toddburgessmedia.torontocatrescue.model;

import com.toddburgessmedia.torontocatrescue.data.PetList;

import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

public class PetListModel {

    Retrofit retrofit;

    int start = 1;
    int end = 700;

    String apikey;
    String shelterID;

    PetList petList;

    public PetListModel (Retrofit retrofit, String apikey, String shelterID) {

        this.retrofit = retrofit;
        this.apikey = apikey;
        this.shelterID = shelterID;
    }

    public void fetchPetList() {

        PetListAPI petListAPI = retrofit.create(PetListAPI.class);
        Observable<Response<PetList>> petListObservable;
        petListObservable = petListAPI.getAllPets(apikey, shelterID, start, end);

        petListObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<PetList>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<PetList> petListResponse) {

                        if (petListResponse.isSuccessful()) {
                            petList = petListResponse.body();
                        }

                    }
                });

    }


    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}
