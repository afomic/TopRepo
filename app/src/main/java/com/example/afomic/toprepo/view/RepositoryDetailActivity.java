package com.example.afomic.toprepo.view;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.afomic.toprepo.R;
import com.example.afomic.toprepo.di.DaggerRepositoryDetailsPresenterComponent;
import com.example.afomic.toprepo.di.RepositoryDetailsPresenterComponent;
import com.example.afomic.toprepo.model.Repository;
import com.example.afomic.toprepo.presenter.RepositoryDetailPresenter;
import com.example.afomic.toprepo.utils.GlideApp;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RepositoryDetailActivity extends AppCompatActivity implements RepositoryDetailView{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_repo_created_date)
    TextView dateCreateTextView;
    @BindView(R.id.tv_repo_last_upate_date)
    TextView repoLastUpdateTextView;
    @BindView(R.id.tv_repo_fork)
    TextView repoForkNumberTextView;
    @BindView(R.id.tv_repo_star)
    TextView repoStarNumberTextView;
    @BindView(R.id.tv_repository_owner)
    TextView repoOwnerTextView;
    @BindView(R.id.tv_repo_description)
    TextView repoDescriptionTextView;
    @BindView(R.id.imv_owner_avatar)
    CircleImageView repoOwnerAvatarImageView;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    RepositoryDetailsPresenterComponent repositoryDetailsPresenterComponent;
    RepositoryDetailPresenter repositoryDetailPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_detail);
        ButterKnife.bind(this);
        repositoryDetailsPresenterComponent= DaggerRepositoryDetailsPresenterComponent.builder()
                .build();
        repositoryDetailPresenter=repositoryDetailsPresenterComponent.getRepositoryDetailPresenter();
        repositoryDetailPresenter.takeView(this);
        repositoryDetailPresenter.handleIntent(getIntent());
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void setUpView() {
        setSupportActionBar(toolbar);

    }

    @Override
    public void showRepositoryDetails(Repository repository) {
        GlideApp.with(this)
                .load(repository.getOwnerAvatarUrl())
                .listener(GlidePalette.with(repository.getOwnerAvatarUrl())
                        .use(GlidePalette.Profile.VIBRANT)
                        .intoBackground(collapsingToolbarLayout)
                        .crossfade(true)
                )
                .into(repoOwnerAvatarImageView);
        repoDescriptionTextView.setText(repository.getDescription());
        repoForkNumberTextView.setText(String.valueOf(repository.getForkNumber()));
        repoOwnerTextView.setText(repository.getOwnerName());
        getSupportActionBar().setTitle(repository.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white);
        repoStarNumberTextView.setText(String.valueOf(repository.getStarNumber()));
        repoLastUpdateTextView.setText(repository.getUpdatedAt());
        dateCreateTextView.setText(repository.getCreatedAt());
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
