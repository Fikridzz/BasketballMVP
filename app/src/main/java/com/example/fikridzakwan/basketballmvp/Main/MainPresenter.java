package com.example.fikridzakwan.basketballmvp.Main;

import com.example.fikridzakwan.basketballmvp.Model.ClubResponse;
import com.example.fikridzakwan.basketballmvp.Network.ApiClient;
import com.example.fikridzakwan.basketballmvp.Network.ApiInterface;
import com.example.fikridzakwan.basketballmvp.Utils.Constans;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainConstract.Presenter{

    private final MainConstract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public MainPresenter(MainConstract.View view) {
        this.view = view;
    }

    @Override
    public void getDataClub() {
        view.showProgress();

        Call<ClubResponse> call = apiInterface.getClub(Constans.idBasket);
        call.enqueue(new Callback<ClubResponse>() {
            @Override
            public void onResponse(Call<ClubResponse> call, Response<ClubResponse> response) {
                view.hideProgress();

                if (response.body() != null) {
                    ClubResponse clubResponse = response.body();
                    if (clubResponse.getClubDataList() != null) {
                        view.showDataListClub(clubResponse.getClubDataList());
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
