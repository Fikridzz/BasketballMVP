package com.example.fikridzakwan.basketballmvp.Detail;

import android.os.Bundle;
import android.widget.Toast;

import com.example.fikridzakwan.basketballmvp.Model.ClubData;
import com.example.fikridzakwan.basketballmvp.Model.ClubResponse;
import com.example.fikridzakwan.basketballmvp.Network.ApiClient;
import com.example.fikridzakwan.basketballmvp.Network.ApiInterface;
import com.example.fikridzakwan.basketballmvp.Utils.Constans;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter implements DetailConstact.Presenter{

    private final DetailConstact.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private int id;

    public DetailPresenter(DetailConstact.View view) {
        this.view = view;
    }

    @Override
    public void getDetailClub(Bundle bundle) {
        if (bundle != null) {
            id = Integer.valueOf(bundle.getString(Constans.id));

            view.showProgress();

            Call<ClubResponse> call = apiInterface.getDetail(id);
            call.enqueue(new Callback<ClubResponse>() {
                @Override
                public void onResponse(Call<ClubResponse> call, Response<ClubResponse> response) {
                    view.hideProgress();
                    if (response.body() != null) {
                        ClubResponse clubResponse = response.body();
                        if (clubResponse.getClubDataList() != null) {
                            ClubData clubData = clubResponse.getClubDataList().get(0);
                            view.showDetailClub(clubData);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ClubResponse> call, Throwable t) {
                    view.hideProgress();
                    view.showFailurMessage(t.getMessage());
                }
            });
        }

    }
}
