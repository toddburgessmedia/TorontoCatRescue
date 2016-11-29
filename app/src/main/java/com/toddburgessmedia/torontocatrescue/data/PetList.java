package com.toddburgessmedia.torontocatrescue.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

public class PetList implements Serializable, Parcelable {

    @SerializedName("returned_pets")
    int petCount;

    @SerializedName("status")
    String status;

    @SerializedName("total_pets")
    int totalPets;

    @SerializedName("pets")
    ArrayList<Pet> petList;

    public void sortPetList() {
        Collections.sort(petList, new Comparator<Pet>() {
            @Override
            public int compare(Pet pet, Pet t1) {
                return pet.getPetName().compareTo(t1.getPetName());
            }
        });
    }

    public int getPetCount() {
        return petCount;
    }

    public void setPetCount(int petCount) {
        this.petCount = petCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPets() {
        return totalPets;
    }

    public void setTotalPets(int totalPets) {
        this.totalPets = totalPets;
    }

    public ArrayList<Pet> getPetList() {
        return petList;
    }

    public void setPetList(ArrayList<Pet> petList) {
        this.petList = petList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.petCount);
        dest.writeString(this.status);
        dest.writeInt(this.totalPets);
        dest.writeList(this.petList);
    }

    public PetList() {
    }

    protected PetList(Parcel in) {
        this.petCount = in.readInt();
        this.status = in.readString();
        this.totalPets = in.readInt();
        this.petList = new ArrayList<Pet>();
        in.readList(this.petList, Pet.class.getClassLoader());
    }

    public static final Parcelable.Creator<PetList> CREATOR = new Parcelable.Creator<PetList>() {
        @Override
        public PetList createFromParcel(Parcel source) {
            return new PetList(source);
        }

        @Override
        public PetList[] newArray(int size) {
            return new PetList[size];
        }
    };
}
