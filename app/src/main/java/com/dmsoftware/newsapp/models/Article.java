package com.dmsoftware.newsapp.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by juanjesuscuetoyabar on 23/03/18.
 */

public class Article {
    private Map<String,String> source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

    public Article() {
    }

    public Article(Map<String, String> source, String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public Map<String, String> getSource() {
        return source;
    }

    public Article setSource(Map<String, String> source) {
        this.source = source;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Article setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Article setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Article setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public Article setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
        return this;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public Article setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("id",source.get("id"));
        bundle.putString("name",source.get("name"));
        bundle.putString("author",author);
        bundle.putString("title",title);
        bundle.putString("description",description);
        bundle.putString("url",url);
        bundle.putString("urlToImage",urlToImage);
        bundle.putString("publishedAt",publishedAt);
        return bundle;
    }

    public static Article from(Bundle bundle) {
        Article article = new Article();
        Map<String,String> source = new HashMap<>();
        source.put("id",bundle.getString("id"));
        source.put("name",bundle.getString("name"));
        article.setSource(source)
                .setAuthor(bundle.getString("author"))
                .setTitle(bundle.getString("title"))
                .setDescription(bundle.getString("description"))
                .setUrl(bundle.getString("url"))
                .setUrlToImage(bundle.getString("urlToImage"))
                .setPublishedAt(bundle.getString("publishedAt"));
        return article;
    }

    public static Article from(JSONObject JSONArticle) {
        Article article = new Article();
        Map<String,String> source = new HashMap<>();
        try {
            source.put("id",JSONArticle.getJSONObject("source").getString("id"));
            source.put("name",JSONArticle.getJSONObject("source").getString("name"));
            article.setSource(source)
                    .setAuthor(JSONArticle.getString("author"))
                    .setTitle(JSONArticle.getString("title"))
                    .setDescription(JSONArticle.getString("description"))
                    .setUrl(JSONArticle.getString("url"))
                    .setUrlToImage(JSONArticle.getString("urlToImage"))
                    .setPublishedAt(JSONArticle.getString("publishedAt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return article;
    }

    public static List<Article> from(JSONArray jsonSArticles) {
        List<Article> articles = new ArrayList<>();
        for (int i = 0; i<jsonSArticles.length();i++) {
            try {
                articles.add(from(jsonSArticles.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return articles;
    }
}
