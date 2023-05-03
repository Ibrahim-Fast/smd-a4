package com.example.smd_project;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("categories")
    Call<ApiCategoriesClass> getCategories();

    @GET("popular")
    Call<ApiPopularClass> getPopular();
}