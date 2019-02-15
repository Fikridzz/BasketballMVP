package com.example.fikridzakwan.basketballmvp.Main;

import com.example.fikridzakwan.basketballmvp.Model.ClubData;

import java.util.List;

public interface MainConstract {
    interface View {
        void showProgress();
        void hideProgress();
        void showDataListClub(List<ClubData> clubDataList);
        void showFailurMessage(String msg);
    }

    interface Presenter {
        void getDataClub();
    }
}
