package com.dmsoftware.newsapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.dmsoftware.newsapp.R;
import com.dmsoftware.newsapp.activities.NewsActivity;
import com.dmsoftware.newsapp.adapters.ArticlesAdapter;
import com.dmsoftware.newsapp.models.Article;
import com.dmsoftware.newsapp.networking.NewsAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView articleRecyclerView;
    ArticlesAdapter articlesAdapter;
    RecyclerView.LayoutManager articleLayoutManager;
    List<Article> articles;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((NewsActivity)getActivity()).setFragmentToolbar("Home",false,getFragmentManager());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        articles = new ArrayList<>();
        articleRecyclerView = (RecyclerView) view.findViewById(R.id.articlesRecyclerView);
        articlesAdapter = new ArticlesAdapter(articles);
        articleLayoutManager = new GridLayoutManager(view.getContext(),1);
        articleRecyclerView.setAdapter(articlesAdapter);
        articleRecyclerView.setLayoutManager(articleLayoutManager);
        updateArticles();
        return view;
    }

    private void updateArticles() {
        AndroidNetworking.get(NewsAPI.ARTICLES_URL)
                .addQueryParameter("sources","bbc-news")
                .addQueryParameter("apiKey",getString(R.string.api_key))
                .setPriority(Priority.LOW)
                .setTag(getString(R.string.app_name))
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(getString(R.string.app_name), "Error" + response.getString("message"));
                            }
                            articles = Article.from(response.getJSONArray("articles"));
                            articlesAdapter.setArticles(articles);
                            articlesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(getString(R.string.app_name), "Error" + anError.getLocalizedMessage());
                    }
                });
    }

}
