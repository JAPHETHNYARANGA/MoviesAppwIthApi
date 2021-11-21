package com.moringaschool.mymoviesapp;

import android.content.Context;
import android.widget.Toast;

import com.moringaschool.mymoviesapp.Listeners.OnDetailsApiListener;
import com.moringaschool.mymoviesapp.Listeners.OnSearchApiListener;
import com.moringaschool.mymoviesapp.Models.DetailApiResponse;
import com.moringaschool.mymoviesapp.Models.SearchApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://imdb-internet-movie-database-unofficial.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void searchMovies(OnSearchApiListener listener, String movie_name){
        getMovies getMovies = retrofit.create(RequestManager.getMovies.class);
        Call<SearchApiResponse> call = getMovies.callMovies(movie_name);

        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Failed! please check your internet connection and try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public void searchMovieDetails(OnDetailsApiListener listener, String movie_id){
        getMovieDetails getMovieDetails = retrofit.create(RequestManager.getMovieDetails.class);
        Call<DetailApiResponse> call = getMovieDetails.callMovieDetails(movie_id);

        call.enqueue(new Callback<DetailApiResponse>() {
            @Override
            public void onResponse(Call<DetailApiResponse> call, Response<DetailApiResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context, "Failed! please check your internet connection and try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<DetailApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public interface getMovies{
        @Headers({
                "Accept:application/json",
                "x-rapidapi-host:imdb-internet-movie-database-unofficial.p.rapidapi.com",
                "x-rapidapi-key:7ea72ef5famsh82acece820c21a1p19105djsn7e5f22bcd24e"
        })
        @GET("search/{movie_name}")
        Call<SearchApiResponse>callMovies(
                @Path("movie_name")String movie_name
        );
    }

    public interface getMovieDetails{
        @Headers({
                "Accept:application/json",
                "x-rapidapi-host:imdb-internet-movie-database-unofficial.p.rapidapi.com",
                "x-rapidapi-key:7ea72ef5famsh82acece820c21a1p19105djsn7e5f22bcd24e"
        })
        @GET("film/{movie_id}")
            Call<DetailApiResponse>callMovieDetails(
                @Path("movie_id")String movie_id
        );
    }
}
