package com.example.fikridzakwan.basketballmvp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClubResponse {

    @SerializedName("teams")
    List<ClubData> teams;

    public List<ClubData> getClubDataList() {
        return teams;
    }
}
