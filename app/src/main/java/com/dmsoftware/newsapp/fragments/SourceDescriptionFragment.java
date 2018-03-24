package com.dmsoftware.newsapp.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.dmsoftware.newsapp.R;
import com.dmsoftware.newsapp.activities.NewsActivity;
import com.dmsoftware.newsapp.models.Source;
import com.dmsoftware.newsapp.networking.ClearbitAPI;

/**
 * A simple {@link Fragment} subclass.
 */
public class SourceDescriptionFragment extends Fragment {

    ANImageView logoANImageView;
    TextView descriptionTextView;
    TextView urlTextView;
    TextView categoryTextView;
    TextView languageTextView;
    TextView countryTextView;

    public SourceDescriptionFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Source source = Source.from(getArguments());
        ((NewsActivity)getActivity()).setFragmentToolbar(source.getName(),true,getFragmentManager());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_source_description, container, false);

        //Initialize variables
        logoANImageView = (ANImageView) view.findViewById(R.id.logoDescriptionANImageView);
        descriptionTextView = (TextView) view.findViewById(R.id.descriptionTextView);
        urlTextView = (TextView) view.findViewById(R.id.urlTextView);
        categoryTextView = (TextView) view.findViewById(R.id.categoryTextView);
        languageTextView = (TextView) view.findViewById(R.id.languageTextView);
        countryTextView = (TextView) view.findViewById(R.id.countryTextView);

        //Set values
        logoANImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        logoANImageView.setErrorImageResId(R.mipmap.ic_launcher);
        logoANImageView.setImageUrl(ClearbitAPI.getUrlToLogo(source.getUrl()));
        descriptionTextView.setText(source.getDescription());
        urlTextView.setText(source.getUrl());
        categoryTextView.setText(source.getCategory());
        languageTextView.setText(source.getLanguage());
        countryTextView.setText(source.getCountry());
        return view;
    }

}
