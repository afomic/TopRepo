package com.example.afomic.toprepo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.afomic.toprepo.R;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RepositoryDetailActivity extends AppCompatActivity implements RepositoryDetailView{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_date_created)
    TextView dateCreateTextView;
    @BindView(R.id.tv_repo_last_upate_date)
    TextView repoLastUpdateTextView;
    @BindView(R.id.tv_repo_fork)
    TextView repoForkNumberTextView;
    @BindView(R.id.tv_repo_star)
    TextView repoStarNumberTextView;
    @BindView(R.id.tv_repo_watcher)
    TextView repoWatcherNumberTextView;
    @BindView(R.id.tv_repository_owner)
    TextView repoOwnerTextView;
    @BindView(R.id.tv_repo_description)
    TextView repoDescriptionTextView;
    @BindView(R.id.imv_owner_avatar)
    CircleImageView repoOwnerAvatarImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_detail);
    }
    public void setupView(){

//        Glida.with(this)
//                .load("")
//                .listener(GlidePalette.with("")
//                        .use(GlidePalette.Profile.VIBRANT)
//                        .intoBackground(textView)
//                        .intoTextColor(textView)
//                        .use(GlidePalette.Profile.VIBRANT)
//                        .intoBackground(titleView, GlidePalette.Swatch.RGB)
//                        .intoTextColor(titleView, GlidePalette.Swatch.BODY_TEXT_COLOR)
//                        .crossfade(true)
//                )
//                .into(imageView);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void setUpView() {

    }
}
