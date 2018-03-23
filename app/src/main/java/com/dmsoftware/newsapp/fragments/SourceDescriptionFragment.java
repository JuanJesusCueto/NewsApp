package com.dmsoftware.newsapp.fragments;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmsoftware.newsapp.R;
import com.dmsoftware.newsapp.activities.MainActivity;
import com.dmsoftware.newsapp.activities.NewsActivity;
import com.dmsoftware.newsapp.models.Source;

/**
 * A simple {@link Fragment} subclass.
 */
public class SourceDescriptionFragment extends Fragment {


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
        return view;
    }

}
