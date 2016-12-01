package com.toddburgessmedia.petlist.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

public class Pet implements Serializable {

    @SerializedName("age")
    String age;

    @SerializedName("order")
    int order;

    @SerializedName("pet_id")
    String petID;

    @SerializedName("pet_name")
    String petName;

    @SerializedName("primary_breed")
    String primaryBreed;

    @SerializedName("species")
    String species;

    @SerializedName("sex")
    String sex;

    @SerializedName("details_url")
    String detailsURL;

    @SerializedName("large_results_photo_url")
    String largePhotoURL;

    @SerializedName("results_photo_url")
    String resultsPhotoURL;

    @SerializedName("addr_state_code")
    String state;

    @SerializedName("addr_city")
    String city;

    boolean isVisible = true;

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetailsURL() {
        return detailsURL;
    }

    public void setDetailsURL(String detailsURL) {
        this.detailsURL = detailsURL;
    }

    public String getLargePhotoURL() {
        return largePhotoURL;
    }

    public void setLargePhotoURL(String largePhotoURL) {
        this.largePhotoURL = largePhotoURL;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPrimaryBreed() {
        return primaryBreed;
    }

    public void setPrimaryBreed(String primaryBreed) {
        this.primaryBreed = primaryBreed;
    }

    public String getResultsPhotoURL() {
        return resultsPhotoURL;
    }

    public void setResultsPhotoURL(String resultsPhotoURL) {
        this.resultsPhotoURL = resultsPhotoURL;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
