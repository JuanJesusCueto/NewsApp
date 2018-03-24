package com.dmsoftware.newsapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.dmsoftware.newsapp.R;
import com.dmsoftware.newsapp.models.Article;

import java.util.List;

/**
 * Created by juanjesuscuetoyabar on 23/03/18.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private List<Article> articles;

    public ArticlesAdapter(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public ArticlesAdapter setArticles(List<Article> articles) {
        this.articles = articles;
        return this;
    }

    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_article,parent,false));
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.ViewHolder holder, int position) {
        final Article article= articles.get(position);
        holder.articleANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.articleANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        holder.articleANImageView.setImageUrl(article.getUrlToImage());
        holder.titleTextView.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ANImageView articleANImageView;
        TextView titleTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            articleANImageView = (ANImageView) itemView.findViewById(R.id.articleANImageView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
        }
    }
}
