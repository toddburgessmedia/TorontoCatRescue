package com.toddburgessmedia.torontocatrescue.model;

import com.toddburgessmedia.torontocatrescue.data.PetList;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

public interface PetListAPI {

    @GET("/search/pets_at_shelter?output=json")
    Single<Response<PetList>> getAllPets(@Query("key") String apiKey,
                                         @Query("shelter_id") String shelterID,
                                         @Query("start_number") int start,
                                         @Query("end_number") int end);

}
