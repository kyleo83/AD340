package com.example.hw6;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class CameraLoader extends AsyncTaskLoader<String> {

    private String mQueryString;

    public CameraLoader(Context context, String queryString){
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
//        super.onStartLoading();
        forceLoad();
    }


    @Nullable
    @Override
    public String loadInBackground() {
        String baseURL = "https://web6.seattle.gov/Travelers/api/Map/Data";

        return NetworkUtils.getData(baseURL,
                "zoomId", "13", "type", "2");
    }
}
