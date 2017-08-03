package com.toddburgessmedia.torontocatrescue.model;

import android.os.Parcelable;

import com.toddburgessmedia.torontocatrescue.data.LimitedPet;
import com.toddburgessmedia.torontocatrescue.data.PetDetail;
import com.toddburgessmedia.torontocatrescue.data.PetDetailInfo;
import com.toddburgessmedia.torontocatrescue.presenter.PetDetailPresenter;

import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Single;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 30/07/17.
 */

public class PetDetailModelImpl implements PetDetailModel {

    Retrofit retrofit;
    String apikey;
    String shelterID;
    PetDetailPresenter presenter;

    PetDetail petDetail;
    LimitedPet bondedFriend;

    public PetDetailModelImpl(Retrofit retrofit, String apikey, String shelterID) {

        this.retrofit = retrofit;
        this.apikey = apikey;
        this.shelterID = shelterID;
    }

    @Override
    public void setPetDetailPresenter(PetDetailPresenter petDetailPresenter) {
        this.presenter = petDetailPresenter;
    }

    public void fetchPetDetail(String petID) {

        Single<Response<PetDetail>> petDetailSingle = getResponseSingle(petID);

        petDetailSingle.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<Response<PetDetail>>() {
                    @Override
                    public void onSuccess(Response<PetDetail> value) {
                        petDetail = value.body();
                        presenter.updatePetInformation(petDetail.getPetDetailInfo());
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.onError();
                    }
                });

    }

    public void fetchPetDetailAll(String petID) {

        Single<Response<PetDetail>> petDetailSingle = getResponseSingle(petID);
        Single<Response<LimitedPet>> limitedSingle = getLimitedResponseSingle(petID);

        Single<PetDetail> result = Single.zip(petDetailSingle, limitedSingle, new Func2<Response<PetDetail>, Response<LimitedPet>, PetDetail>() {
                    @Override
                    public PetDetail call(Response<PetDetail> petDetailResponse, Response<LimitedPet> limitedPetResponse) {
                        petDetail = petDetailResponse.body();
                        LimitedPet limited = limitedPetResponse.body();
                        petDetail.setPetURL(limited.getLimitedPetDetail().getPetDetailsUrl());
                        return petDetail;
                    }
                });

        result.observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleSubscriber<PetDetail>() {
                            @Override
                            public void onSuccess(PetDetail value) {
                                presenter.updatePetInformation(value.getPetDetailInfo());
                            }

                            @Override
                            public void onError(Throwable error) {
                                presenter.onError();
                            }
                        });

    }

    private Single<Response<PetDetail>> getResponseSingle(String petID) {
        PetDetailAPI petDetailAPI = retrofit.create(PetDetailAPI.class);
        Single<Response<PetDetail>> petDetailSingle;
        petDetailSingle = petDetailAPI.getPetDetail(petID, apikey, shelterID);
        return petDetailSingle;
    }

    public PetDetail getPetDetail () {

        return petDetail;
    }

    @Override
    public boolean isBondedPair() {

        PetDetailInfo info = petDetail.getPetDetailInfo();
        if (info.getBondedTo() != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void getBondedPet(PetDetailInfo info) {
        fetchBondedFriend(info.getBondedTo());


    }

    @Override
    public String getCatName() {

        return petDetail.getPetDetailInfo().getPetName();
    }

    public void fetchBondedFriend(String petID) {

        Single<Response<LimitedPet>> limitedSingle = getLimitedResponseSingle(petID);

        limitedSingle.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<Response<LimitedPet>>() {
                    @Override
                    public void onSuccess(Response<LimitedPet> value) {
                        bondedFriend = value.body();
                        presenter.updateBondedPetInformation(bondedFriend.getLimitedPetDetail());
                    }

                    @Override
                    public void onError(Throwable error) {
                        presenter.onError();
                    }
                });

    }

    private Single<Response<LimitedPet>> getLimitedResponseSingle(String petID) {
        PetDetailAPI petDetailAPI = retrofit.create(PetDetailAPI.class);
        Single<Response<LimitedPet>> limitedSingle;
        limitedSingle = petDetailAPI.getLimitedPetDetail(petID, apikey, shelterID);
        return limitedSingle;
    }

    public LimitedPet getBondedFriend() {

        if (bondedFriend == null) {
            return new LimitedPet();
        } else {
            return bondedFriend;
        }
    }

    @Override
    public Parcelable savePetDetail() {

        return (Parcelable) petDetail;
    }

    @Override
    public void restorePetDetail(Parcelable petDetail) {

        if (petDetail instanceof PetDetail) {
            this.petDetail = (PetDetail) petDetail;
        }
    }

    @Override
    public Parcelable saveBondedFriend() {

        if (bondedFriend != null) {
            return (Parcelable) bondedFriend;
        } else {
            return (Parcelable) new LimitedPet();
        }

    }

    @Override
    public void restoreBondedFriend(Parcelable bondedFriend) {

        if ((bondedFriend != null) && (bondedFriend instanceof LimitedPet)) {
            this.bondedFriend = (LimitedPet) bondedFriend;
        }

    }
}
