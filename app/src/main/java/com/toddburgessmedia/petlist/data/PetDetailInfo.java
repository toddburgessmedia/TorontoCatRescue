package com.toddburgessmedia.petlist.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 22/11/16.
 */

public class PetDetailInfo implements Parcelable {

    @SerializedName("shelter_name")
    String shelterName;

    @SerializedName("hair_length")
    String hairLength;

    @SerializedName("phone_area_code")
    String areaCode;

    @SerializedName("email")
    String email;

    @SerializedName("shelter_desc")
    String shelterDescription;

    @SerializedName("good_with_dogs")
    String goodWithDogs;

    @SerializedName("last_modified")
    String lastModified;

    @SerializedName("declawed")
    String declawed;

    @SerializedName("description")
    String description;

    @SerializedName("addr_city")
    String city;

    @SerializedName("spayed_neutered")
    String spayed;

    @SerializedName("color")
    String furColour;

    @SerializedName("good_with_cats")
    String goodWithCats;

    @SerializedName("addr_postal_code")
    String postalCode;

    @SerializedName("pet_name")
    String petName;

    @SerializedName("species")
    String species;

    @SerializedName("shots_current")
    String shotsCurrent;

    @SerializedName("adoption_process")
    String adoptionProcess;

    @SerializedName("pet_id")
    String petID;

    @SerializedName("website_url")
    String webURL;

    @SerializedName("primary_breed")
    String primaryBreed;

    @SerializedName("areas_served")
    String areasServed;

    @SerializedName("good_with_kids")
    String goodWithKids;

    @SerializedName("phone_number")
    String phoneNumber;

    @SerializedName("addr_country_code")
    String countryCode;

    @SerializedName("age")
    String age;

    @SerializedName("addr_state_code")
    String stateCode;

    @SerializedName("sex")
    String sex;

    @SerializedName("shelter_id")
    String shelterID;

    @SerializedName("bonded_to")
    String bondedTo;

    @SerializedName("images")
    ArrayList<PetDetailImage> petImages;

    public String getAdoptionProcess() {
        return adoptionProcess;
    }

    public void setAdoptionProcess(String adoptionProcess) {
        this.adoptionProcess = adoptionProcess;
    }

    public String getBondedTo() {
        return bondedTo;
    }

    public void setBondedTo(String bondedTo) {
        this.bondedTo = bondedTo;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreasServed() {
        return areasServed;
    }

    public void setAreasServed(String areasServed) {
        this.areasServed = areasServed;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDescription() {

        byte[] b = description.getBytes();
        StringBuffer sb = new StringBuffer();
        boolean isNeg = false;
        int i = 0;
        while (i < b.length) {
            if (b[i] < 0) {
                if (!isNeg) {
                    sb.append("'");
                    isNeg = true;
                }
                i++;
            } else {
                sb.append((char)b[i]);
                i++;
                isNeg = false;
            }
        }

        return sb.toString();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFurColour() {
        return furColour;
    }

    public void setFurColour(String furColour) {
        this.furColour = furColour;
    }

    public ArrayList<PetDetailImage> getPetImages() {
        return petImages;
    }

    public void setPetImages(ArrayList<PetDetailImage> petImages) {
        this.petImages = petImages;
    }

    public String getHairLength() {
        return hairLength;
    }

    public void setHairLength(String hairLength) {
        this.hairLength = hairLength;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPrimaryBreed() {
        return primaryBreed;
    }

    public void setPrimaryBreed(String primaryBreed) {
        this.primaryBreed = primaryBreed;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getShelterDescription() {
        return shelterDescription;
    }

    public void setShelterDescription(String shelterDescription) {
        this.shelterDescription = shelterDescription;
    }

    public String getShelterID() {
        return shelterID;
    }

    public void setShelterID(String shelterID) {
        this.shelterID = shelterID;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getSpayed() {
        return spayed;
    }

    public void setSpayed(String spayed) {
        this.spayed = spayed;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getWebURL() {
        return webURL;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }

    public String getDeclawed() {
        return declawed;
    }

    public void setDeclawed(String declawed) {
        this.declawed = declawed;
    }

    public String getGoodWithCats() {
        return goodWithCats;
    }

    public void setGoodWithCats(String goodWithCats) {
        this.goodWithCats = goodWithCats;
    }

    public String getGoodWithDogs() {
        return goodWithDogs;
    }

    public void setGoodWithDogs(String goodWithDogs) {
        this.goodWithDogs = goodWithDogs;
    }

    public String getGoodWithKids() {
        return goodWithKids;
    }

    public void setGoodWithKids(String goodWithKids) {
        this.goodWithKids = goodWithKids;
    }

    public String getShotsCurrent() {
        return shotsCurrent;
    }

    public void setShotsCurrent(String shotsCurrent) {
        this.shotsCurrent = shotsCurrent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.shelterName);
        dest.writeString(this.hairLength);
        dest.writeString(this.areaCode);
        dest.writeString(this.email);
        dest.writeString(this.shelterDescription);
        dest.writeString(this.goodWithDogs);
        dest.writeString(this.lastModified);
        dest.writeString(this.declawed);
        dest.writeString(this.description);
        dest.writeString(this.city);
        dest.writeString(this.spayed);
        dest.writeString(this.furColour);
        dest.writeString(this.goodWithCats);
        dest.writeString(this.postalCode);
        dest.writeString(this.petName);
        dest.writeString(this.species);
        dest.writeString(this.shotsCurrent);
        dest.writeString(this.adoptionProcess);
        dest.writeString(this.petID);
        dest.writeString(this.webURL);
        dest.writeString(this.primaryBreed);
        dest.writeString(this.areasServed);
        dest.writeString(this.goodWithKids);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.countryCode);
        dest.writeString(this.age);
        dest.writeString(this.stateCode);
        dest.writeString(this.sex);
        dest.writeString(this.shelterID);
        dest.writeString(this.bondedTo);
        dest.writeList(this.petImages);
    }

    public PetDetailInfo() {
    }

    protected PetDetailInfo(Parcel in) {
        this.shelterName = in.readString();
        this.hairLength = in.readString();
        this.areaCode = in.readString();
        this.email = in.readString();
        this.shelterDescription = in.readString();
        this.goodWithDogs = in.readString();
        this.lastModified = in.readString();
        this.declawed = in.readString();
        this.description = in.readString();
        this.city = in.readString();
        this.spayed = in.readString();
        this.furColour = in.readString();
        this.goodWithCats = in.readString();
        this.postalCode = in.readString();
        this.petName = in.readString();
        this.species = in.readString();
        this.shotsCurrent = in.readString();
        this.adoptionProcess = in.readString();
        this.petID = in.readString();
        this.webURL = in.readString();
        this.primaryBreed = in.readString();
        this.areasServed = in.readString();
        this.goodWithKids = in.readString();
        this.phoneNumber = in.readString();
        this.countryCode = in.readString();
        this.age = in.readString();
        this.stateCode = in.readString();
        this.sex = in.readString();
        this.shelterID = in.readString();
        this.bondedTo = in.readString();
        this.petImages = new ArrayList<PetDetailImage>();
        in.readList(this.petImages, PetDetailImage.class.getClassLoader());
    }

    public static final Parcelable.Creator<PetDetailInfo> CREATOR = new Parcelable.Creator<PetDetailInfo>() {
        @Override
        public PetDetailInfo createFromParcel(Parcel source) {
            return new PetDetailInfo(source);
        }

        @Override
        public PetDetailInfo[] newArray(int size) {
            return new PetDetailInfo[size];
        }
    };
}
