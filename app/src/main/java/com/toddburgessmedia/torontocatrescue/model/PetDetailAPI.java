package com.toddburgessmedia.torontocatrescue.model;

import com.toddburgessmedia.torontocatrescue.data.LimitedPet;
import com.toddburgessmedia.torontocatrescue.data.PetDetail;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 22/11/16.
 */

public interface PetDetailAPI {

    @GET("/search/pet_details?output=json&v=2")
    Observable<Response<PetDetail>> getPetDetail(@Query("pet_id") String petID,
                                                 @Query("key") String apikey,
                                                 @Query("shelter_id") String shelterID);

    @GET("/search/limited_pet_details?output=json&v=2")
    Observable<Response<LimitedPet>> getLimitedPetDetail(@Query("pet_id") String petID,
                                                         @Query("key") String apikey,
                                                         @Query("shelter_id") String shelterID);

}
