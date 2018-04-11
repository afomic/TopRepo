package com.example.afomic.toprepo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.afomic.toprepo.R;
import com.example.afomic.toprepo.adapter.RepositoryAdapter;
import com.example.afomic.toprepo.data.Constants;
import com.example.afomic.toprepo.di.ContextModule;
import com.example.afomic.toprepo.di.DaggerMainActivityComponent;
import com.example.afomic.toprepo.di.MainActivityComponent;
import com.example.afomic.toprepo.model.Repository;
import com.example.afomic.toprepo.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView,
        RepositoryAdapter.RepositoryClickListener{
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.rv_repository)
    RecyclerView repositoryRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    MainPresenter mainPresenter;
    MainActivityComponent mainActivityComponent;
    LinearLayoutManager mLinearLayoutManager;

    List<Repository> mRepositories;
    private RepositoryAdapter mRepositoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityComponent= DaggerMainActivityComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        mainPresenter= mainActivityComponent.getMainPresenter();
        ButterKnife.bind(this);
        mainPresenter.takeView(this);


    }

    @Override
    public void setUpView() {
        setSupportActionBar(toolbar);
        mRepositories=new ArrayList<>();
        mRepositoryAdapter=new RepositoryAdapter(MainActivity.this,mRepositories);
        mLinearLayoutManager=new LinearLayoutManager(MainActivity.this);
        repositoryRecyclerView.setLayoutManager(mLinearLayoutManager);
        repositoryRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
        repositoryRecyclerView.setAdapter(mRepositoryAdapter);
        mainPresenter.loadRepository();

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRepositories(List<Repository> repository) {
        mRepositories.addAll(repository);
        mRepositoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeRepository(Repository repository) {

    }

    @Override
    public void showRepositoryDetailView(Repository repository) {
        Intent intent=new Intent(MainActivity.this, RepositoryDetailActivity.class);
        intent.putExtra(Constants.EXTRA_REPOSITORY,repository);
        startActivity(intent);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(Repository repository) {
        mainPresenter.repositoryClicked(repository);
    }
}
