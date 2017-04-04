package com.toddburgessmedia.torontocatrescue.model;

import android.util.Log;

import com.toddburgessmedia.torontocatrescue.data.LimitedPet;
import com.toddburgessmedia.torontocatrescue.data.LimitedPetDetail;
import com.toddburgessmedia.torontocatrescue.data.Pet;
import com.toddburgessmedia.torontocatrescue.data.PetDetail;
import com.toddburgessmedia.torontocatrescue.data.PetDetailInfo;
import com.toddburgessmedia.torontocatrescue.data.PetList;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

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
    private PetDetail petDetail;
    private LimitedPet limitedPet;

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
                        //TODO Remove
                        Log.d("TCR", "onCompleted: got the list ");
                        petList.sortPetList();

                        if (!EventBus.getDefault().hasSubscriberForEvent(PetListMessage.class)) {
                            Log.d("TCR", "onCompleted: we don't have a subscriber");
                        }
                        EventBus.getDefault().post(new PetListMessage(petList.getPetList()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TCR", "onError: " + e.getMessage());
                        EventBus.getDefault().post(e);
                    }

                    @Override
                    public void onNext(Response<PetList> petListResponse) {

                        if (petListResponse.isSuccessful()) {
                            petList = petListResponse.body();
                        }

                    }
                });

    }

    public void fetchPetDetail(String petID) {

        PetDetailAPI petDetailAPI = retrofit.create(PetDetailAPI.class);
        Observable<Response<PetDetail>> petDetailObservable;
        petDetailObservable = petDetailAPI.getPetDetail(petID, apikey, shelterID);

        petDetailObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<PetDetail>>() {
                    @Override
                    public void onCompleted() {
                        EventBus.getDefault().post(new PetDetailMessage(petDetail.getPetDetailInfo()));
                        Log.d("TCR", "onCompleted: " + petDetail.getPetDetailInfo().getPetName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TCR", "onError: B A R F ! ! ! ! ! ! " + e.getMessage());
                        EventBus.getDefault().post(e);
                    }

                    @Override
                    public void onNext(Response<PetDetail> petDetailResponse) {

                        if (petDetailResponse.isSuccessful()) {
                            petDetail = petDetailResponse.body();
                        }
                    }
                });
    }

    public void fetchLimtedPetDetail(String petID,final boolean flag) {

        PetDetailAPI petDetailAPI = retrofit.create(PetDetailAPI.class);
        final Observable<Response<LimitedPet>> limitedObservable;
        limitedObservable = petDetailAPI.getLimitedPetDetail(petID,apikey, shelterID);

        limitedObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<LimitedPet>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("TCR", "onCompleted: " + limitedPet.getLimitedPetDetail().getPetName());
                        EventBus.getDefault().post(new LimitedPetDetailMessage(limitedPet.getLimitedPetDetail(),flag));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<LimitedPet> petResponse) {

                        Log.d("TCR", "onNext: " + petResponse.raw().toString());
                        if (petResponse.isSuccessful()) {
                            limitedPet = petResponse.body();
                        }
                    }
                });

    }


    public PetList getPetList() {
        return petList;
    }

    public void setPetList(PetList petList) {
        this.petList = petList;
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

    public class PetListMessage {

        ArrayList<Pet> pets;

        public PetListMessage (ArrayList<Pet> pets) {
            this.pets = pets;
        }

        public ArrayList<Pet> getPets() {
            return pets;
        }
    }

    public class PetDetailMessage {

        PetDetailInfo petDetail;

        public PetDetailMessage(PetDetailInfo petDetail) {
            this.petDetail = petDetail;
        }

        public PetDetailInfo getPetDetail() {
            return petDetail;
        }
    }

    public class LimitedPetDetailMessage {

        LimitedPetDetail limitedPetDetail;
        boolean flag;

        public LimitedPetDetailMessage(LimitedPetDetail limitedPetDetail,boolean flag) {
            this.limitedPetDetail = limitedPetDetail;
            this.flag = flag;
        }

        public LimitedPetDetail getLimitedPetDetail() {
            return limitedPetDetail;
        }

        public boolean getFlag() {
            return flag;
        }
    }
}
