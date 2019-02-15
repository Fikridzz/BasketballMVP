package com.example.fikridzakwan.basketballmvp.Detail;

import android.os.Bundle;
import android.service.autofill.UserData;

import com.example.fikridzakwan.basketballmvp.Model.ClubData;
import com.example.fikridzakwan.basketballmvp.Model.ClubResponse;

public interface DetailConstact {
    interface View {
        void showProgress();
        void hideProgress();
        void showDetailClub(ClubData clubData);
        void showFailurMessage(String msg);
    }

    interface Presenter {
        void getDetailClub(Bundle bundle);
    }
}
