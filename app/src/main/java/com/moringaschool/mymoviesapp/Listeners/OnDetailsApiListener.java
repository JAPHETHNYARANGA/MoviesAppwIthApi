package com.moringaschool.mymoviesapp.Listeners;

import com.moringaschool.mymoviesapp.Models.DetailApiResponse;

public interface OnDetailsApiListener {
    void onResponse(DetailApiResponse response);
    void onError(String message);
}
