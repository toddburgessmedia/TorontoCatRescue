package com.toddburgessmedia.torontocatrescue.model;

import com.toddburgessmedia.torontocatrescue.data.Pet;
import com.toddburgessmedia.torontocatrescue.data.PetDetail;
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
                        petList.sortPetList();
                        EventBus.getDefault().post(new PetListMessage(petList.getPetList()));
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

    public void fetchPetDetail(String petID) {

        PetDetailAPI petDetailAPI = retrofit.create(PetDetailAPI.class);
        Observable<Response<PetDetail>> petDetailObservable;
        petDetailObservable = petDetailAPI.getPetDetail(petID, apikey, shelterID);

        petDetailObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<PetDetail>>() {
                    @Override
                    public void onCompleted() {
                        EventBus.getDefault().post(new PetDetailMessage(petDetail));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<PetDetail> petDetailResponse) {

                        if (petDetailResponse.isSuccessful()) {
                            petDetail = petDetailResponse.body();
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

        PetDetail petDetail;

        public PetDetailMessage(PetDetail petDetail) {
            this.petDetail = petDetail;
        }

        public PetDetail getPetDetail() {
            return petDetail;
        }
    }
}
