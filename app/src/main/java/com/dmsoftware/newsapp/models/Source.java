package com.dmsoftware.newsapp.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanjesuscuetoyabar on 22/03/18.
 */

public class Source {
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;

    public Source() {
    }

    public Source(String id, String name, String description, String url, String category, String language, String country) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.language = language;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public Source setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Source setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Source setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Source setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Source setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public Source setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Source setCountry(String country) {
        this.country = country;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("name",name);
        bundle.putString("description",description);
        bundle.putString("url",url);
        bundle.putString("category",category);
        bundle.putString("language",language);
        bundle.putString("country",country);
        return bundle;
    }

    public static Source from(Bundle bundle) {
        Source source = new Source();
        source.setId(bundle.getString("id"))
                .setName(bundle.getString("name"))
                .setDescription(bundle.getString("description"))
                .setUrl(bundle.getString("url"))
                .setCategory(bundle.getString("category"))
                .setLanguage(bundle.getString("language"))
                .setCountry(bundle.getString("country"));
        return source;
    }

    public static Source from(JSONObject jsonSource) {
        Source source = new Source();
        try {
            source.setId(jsonSource.getString("id"))
                    .setName(jsonSource.getString("name"))
                    .setDescription(jsonSource.getString("description"))
                    .setUrl(jsonSource.getString("url"))
                    .setCategory(jsonSource.getString("category"))
                    .setLanguage(jsonSource.getString("language"))
                    .setCountry(jsonSource.getString("country"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return source;
    }

    public static List<Source> from(JSONArray jsonSources) {
        List<Source> sources = new ArrayList<>();
        for (int i = 0; i<jsonSources.length();i++) {
            try {
                sources.add(from(jsonSources.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return sources;
    }
}
