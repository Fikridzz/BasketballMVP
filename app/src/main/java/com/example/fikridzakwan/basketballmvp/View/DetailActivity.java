package com.example.fikridzakwan.basketballmvp.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fikridzakwan.basketballmvp.Detail.DetailConstact;
import com.example.fikridzakwan.basketballmvp.Detail.DetailPresenter;
import com.example.fikridzakwan.basketballmvp.Model.ClubData;
import com.example.fikridzakwan.basketballmvp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailConstact.View {

    @BindView(R.id.imgStadium)
    ImageView imgStadium;
    @BindView(R.id.txtTempatStadium)
    TextView txtTempatStadium;
    @BindView(R.id.myToolbar)
    Toolbar myToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.txtNamaStadium)
    TextView txtNamaStadium;
    @BindView(R.id.imgLogoClub)
    ImageView imgLogoClub;
    @BindView(R.id.txtDetailClub)
    TextView txtDetailClub;
    @BindView(R.id.txtNamaClub)
    TextView txtNamaClub;

    private ProgressDialog progressDialogl;
    private final DetailPresenter detailPresenter = new DetailPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        detailPresenter.getDetailClub(bundle);
    }

    @Override
    public void showProgress() {
        progressDialogl = new ProgressDialog(this);
        progressDialogl.setMessage("Loading...");
        progressDialogl.setCancelable(false);
        progressDialogl.show();

    }

    @Override
    public void hideProgress() {
        progressDialogl.dismiss();

    }

    @Override
    public void showDetailClub(ClubData clubData) {
        txtNamaStadium.setText(clubData.getStrStadium());
        txtNamaClub.setText(clubData.getStrTeam());
        txtDetailClub.setText(clubData.getStrDescriptionEN());
        txtTempatStadium.setText(clubData.getStrStadiumLocation());
        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
        Glide.with(this).load(clubData.getStrStadiumThumb()).apply(options).into(imgStadium);
        Glide.with(this).load(clubData.getStrTeamBadge()).apply(options).into(imgLogoClub);

    }

    @Override
    public void showFailurMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
