package com.example.afomic.toprepo.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.afomic.toprepo.R;
import com.example.afomic.toprepo.model.Repository;
import com.example.afomic.toprepo.utils.GlideApp;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryHolder>{
    public interface RepositoryClickListener{
        void onClick(Repository repository);
    }
    private Context mContext;
    private List<Repository> mRepositories;
    private RepositoryClickListener mRepositoryClickListener;
    public RepositoryAdapter(Context context, List<Repository> repositories){
        mRepositories=repositories;
        mContext=context;
        mRepositoryClickListener=(RepositoryClickListener) context;
    }

    @NonNull
    @Override
    public RepositoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_repository,parent,false);
        return new RepositoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryHolder holder, int position) {
        Repository repository=mRepositories.get(position);
        holder.bindView(repository);

    }

    @Override
    public int getItemCount() {
        if(mRepositories!=null){
            return mRepositories.size();
        }
        return 0;
    }

    public class RepositoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView repoNameTextView, repoOwnerTextView,repoCreatedDateTextView,repoStarsTextView;
        CircleImageView repoOwnerAvatarImageView;
        public RepositoryHolder(View itemView) {
            super(itemView);
            repoCreatedDateTextView=itemView.findViewById(R.id.tv_date_created);
            repoOwnerTextView=itemView.findViewById(R.id.tv_repository_owner);
            repoStarsTextView=itemView.findViewById(R.id.tv_star_number);
            repoNameTextView=itemView.findViewById(R.id.tv_repository_name);
            repoOwnerAvatarImageView=itemView.findViewById(R.id.imv_owner_avatar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Repository clickedRepository=mRepositories.get(getAdapterPosition());
            mRepositoryClickListener.onClick(clickedRepository);
        }
        public void bindView(Repository repository){
            repoNameTextView.setText(repository.getName());
            repoStarsTextView.setText(String.valueOf(repository.getStarNumber()));
            repoCreatedDateTextView.setText(repository.getCreatedAt());
            repoOwnerTextView.setText(repository.getOwnerName());
            GlideApp.with(mContext)
                    .load(repository.getOwnerAvatarUrl())
                    .placeholder(R.drawable.avater)
                    .into(repoOwnerAvatarImageView);
        }
    }
}
