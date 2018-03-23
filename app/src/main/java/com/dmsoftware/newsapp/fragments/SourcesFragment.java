package com.dmsoftware.newsapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import com.dmsoftware.newsapp.adapters.SourcesAdapter;
import com.dmsoftware.newsapp.models.Source;
import com.dmsoftware.newsapp.networking.NewsAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SourcesFragment extends Fragment {

    List<Source> sources = new ArrayList<>();
    RecyclerView sourcesRecyclerView;
    SourcesAdapter sourcesAdapter;
    RecyclerView.LayoutManager sourcesLayoutManager;

    public SourcesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((NewsActivity)getActivity()).setFragmentToolbar("Sources",false,getFragmentManager());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sources, container, false);

        sources = new ArrayList<>();
        sourcesRecyclerView = (RecyclerView) view.findViewById(R.id.sourcesRecyclerView);
        sourcesAdapter = new SourcesAdapter(sources);
        sourcesLayoutManager = new GridLayoutManager(view.getContext(),2);
        sourcesRecyclerView.setAdapter(sourcesAdapter);
        sourcesRecyclerView.setLayoutManager(sourcesLayoutManager);
        updateSources();
        return view;
    }

    private void updateSources() {
        AndroidNetworking.get(NewsAPI.SOURCES_URL)
                .addQueryParameter("apiKey", getString(R.string.api_key))
                .setPriority(Priority.LOW)
                .setTag(R.string.app_name)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d(getString(R.string.app_name),"Error" + response.getString("message"));
                                return;
                            }
                            sources = Source.from(response.getJSONArray("sources"));
                            sourcesAdapter.setSources(sources);
                            sourcesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(getString(R.string.app_name),anError.getLocalizedMessage());
                    }
                });
    }

}
