package com.moringaschool.mymoviesapp.Listeners;

import com.moringaschool.mymoviesapp.Models.SearchApiResponse;

public interface OnSearchApiListener {
    void onResponse(SearchApiResponse response);
    void onError(String message);
}
