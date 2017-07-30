package com.toddburgessmedia.torontocatrescue.model;

import com.toddburgessmedia.torontocatrescue.data.LimitedPet;
import com.toddburgessmedia.torontocatrescue.data.PetDetail;
import com.toddburgessmedia.torontocatrescue.data.PetDetailInfo;
import com.toddburgessmedia.torontocatrescue.presenter.PetDetailPresenter;

import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Single;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 30/07/17.
 */

public class PetDetailModelImpl implements PetDetailModel {

    Retrofit retrofit;
    String apikey;
    String shelterID;
    PetDetailPresenter presenter;

    PetDetail petDetail;
    LimitedPet limitedPet;

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

        PetDetailAPI petDetailAPI = retrofit.create(PetDetailAPI.class);
        Single<Response<PetDetail>> petDetailSingle;
        petDetailSingle = petDetailAPI.getPetDetail(petID, apikey, shelterID);

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
                        //EventBus.getDefault().post(e);
                    }
                });

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
        fetchLimitedPetDetail(info.getBondedTo());


    }

    @Override
    public String getCatName() {

        return petDetail.getPetDetailInfo().getPetName();
    }

    public void fetchLimitedPetDetail(String petID) {

        Single<Response<LimitedPet>> limitedSingle = getResponseSingle(petID);

        limitedSingle.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<Response<LimitedPet>>() {
                    @Override
                    public void onSuccess(Response<LimitedPet> value) {
                        limitedPet = value.body();
                        presenter.updateBondedPetInformation(limitedPet.getLimitedPetDetail());
                    }

                    @Override
                    public void onError(Throwable error) {
                        presenter.onError();
                    }
                });

    }

    private Single<Response<LimitedPet>> getResponseSingle(String petID) {
        PetDetailAPI petDetailAPI = retrofit.create(PetDetailAPI.class);
        Single<Response<LimitedPet>> limitedSingle;
        limitedSingle = petDetailAPI.getLimitedPetDetail(petID, apikey, shelterID);
        return limitedSingle;
    }

    public LimitedPet getLimitedPet () {

        return limitedPet;
    }


}
