package com.dmsoftware.newsapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.dmsoftware.newsapp.R;
import com.dmsoftware.newsapp.activities.NewsActivity;
import com.dmsoftware.newsapp.fragments.SourceDescriptionFragment;
import com.dmsoftware.newsapp.models.Source;
import com.dmsoftware.newsapp.networking.ClearbitAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanjesuscuetoyabar on 22/03/18.
 */

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {

    private List<Source> sources = new ArrayList<>();

    public SourcesAdapter(List<Source> sources) {
        this.sources = sources;
    }

    public List<Source> getSources() {
        return sources;
    }

    public SourcesAdapter setSources(List<Source> sources) {
        this.sources = sources;
        return this;
    }

    @Override
    public SourcesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_source,parent,false));
    }

    @Override
    public void onBindViewHolder(SourcesAdapter.ViewHolder holder, int position) {

        final Source source = sources.get(position);
        holder.logoANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.logoANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        holder.logoANImageView.setImageUrl(ClearbitAPI.getUrlToLogo(source.getUrl()));
        holder.logoANImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsActivity context = (NewsActivity) view.getContext();
                SourceDescriptionFragment descriptionFragment = new SourceDescriptionFragment();
                descriptionFragment.setArguments(source.toBundle());
                context.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, descriptionFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        holder.nameTextView.setText(source.getName());
        holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ANImageView logoANImageView;
        TextView nameTextView;
        ImageButton favoriteButton;
        public ViewHolder(View itemView) {
            super(itemView);
            logoANImageView = (ANImageView) itemView.findViewById(R.id.logoANImageView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            favoriteButton = (ImageButton) itemView.findViewById(R.id.favoriteButton);
        }
    }
}
