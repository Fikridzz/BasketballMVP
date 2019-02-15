package com.example.fikridzakwan.basketballmvp.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.fikridzakwan.basketballmvp.Adapter.BasketballAdapter;
import com.example.fikridzakwan.basketballmvp.Main.MainConstract;
import com.example.fikridzakwan.basketballmvp.Main.MainPresenter;
import com.example.fikridzakwan.basketballmvp.Model.ClubData;
import com.example.fikridzakwan.basketballmvp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainConstract.View {

    @BindView(R.id.rvClub)
    RecyclerView rvClub;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private final MainPresenter mainPresenter = new MainPresenter(this);
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter.getDataClub();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainPresenter.getDataClub();
            }
        });
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
        swipeRefresh.setRefreshing(false);

    }

    @Override
    public void showDataListClub(List<ClubData> clubDataList) {
        rvClub.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        rvClub.setAdapter(new BasketballAdapter(clubDataList, this));

    }

    @Override
    public void showFailurMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
