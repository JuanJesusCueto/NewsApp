package com.dmsoftware.newsapp.networking;

/**
 * Created by juanjesuscuetoyabar on 22/03/18.
 */

public class ClarbitAPI {
    public static String LOGO_BASE_URL = "https://logo.clearbit.com/";

    public static String getUrlToLogo(String domain) {
        return LOGO_BASE_URL + domain;
    }
}
