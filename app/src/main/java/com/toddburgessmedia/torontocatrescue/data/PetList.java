package com.toddburgessmedia.torontocatrescue.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Todd Burgess (todd@toddburgessmedia.com on 21/11/16.
 */

public class PetList implements Serializable {

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
}
