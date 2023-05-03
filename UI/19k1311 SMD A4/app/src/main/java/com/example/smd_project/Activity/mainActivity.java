package com.example.smd_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;

import com.example.smd_project.Adapter.CategoryAdapter;
import com.example.smd_project.Adapter.PopularAdapter;
import com.example.smd_project.ApiCategoriesClass;
import com.example.smd_project.ApiPopularClass;
import com.example.smd_project.ApiService;
import com.example.smd_project.Domain.CategoryDomain;
import com.example.smd_project.Domain.FoodDomain;
import com.example.smd_project.R;
import com.example.smd_project.RetrofitClient;

import java.util.ArrayList;
import java.util.List;


public class mainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewCategory();
        recyclerViewPopular();
    }

    Retrofit retrofit = RetrofitClient.getRetrofitInstance();




    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        ArrayList<CategoryDomain> category = new ArrayList<>();
        Call<ApiCategoriesClass> call = apiService.getCategories();
        call.enqueue(new Callback<ApiCategoriesClass>() {
                         @Override
                         public void onResponse(Call<ApiCategoriesClass> call, Response<ApiCategoriesClass> response) {
                             if (response.isSuccessful()) {
                                 ApiCategoriesClass data = response.body();
                                 if (data != null) {
                                     String title = data.getTitle();
                                     String pic = data.getPic();
                                     category.add(new CategoryDomain(title,pic));
                                 }
                                 else{
                                     category.add(new CategoryDomain("Pizza", "cat_1"));
                                     category.add((new CategoryDomain("Burger", "cat_2")));
                                     category.add(new CategoryDomain("HotDog", "cat_3"));
                                     category.add((new CategoryDomain("Drink", "cat_4")));
                                     category.add((new CategoryDomain("Donut", "cat_5")));
                                 }
                             }
                         }
                         @Override
                         public void onFailure(Call<ApiCategoriesClass> call, Throwable t) {
                             category.add(new CategoryDomain("Pizza", "cat_1"));
                             category.add((new CategoryDomain("Burger", "cat_2")));
                             category.add(new CategoryDomain("HotDog", "cat_3"));
                             category.add((new CategoryDomain("Drink", "cat_4")));
                             category.add((new CategoryDomain("Donut", "cat_5")));
                         }
        });

        adapter = new CategoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        ArrayList<FoodDomain> foodList = new ArrayList<>();

        Call<ApiPopularClass> call2 = apiService.getPopular();
        call2.enqueue(new Callback<ApiPopularClass>() {
        @Override
        public void onResponse(Call<ApiPopularClass> call, Response<ApiPopularClass> response) {
        if (response.isSuccessful()) {
        ApiPopularClass data = response.body();
            if (data != null) {
                String title = data.getTitle();
                String pic = data.getPic();
                String description = data.getDescription();
                double fee=data.getFee();
                foodList.add(new FoodDomain(
                        title,
                        pic,
                        description,
                        fee
                ));
            }
        } else {
            foodList.add(new FoodDomain(
                    "Fajita pizza",
                    "pop_1",
                    "chicken chunks, mozeralla cheese, cheddar cheese, special sauce, special spices",
                    850.90
            ));
            foodList.add(new FoodDomain(
                    "Beef cheese burger",
                    "pop_2",
                    "beef , cheese, sauce, coleslaw",
                    330.90
            ));
            foodList.add(new FoodDomain(
                    "Zinger cheese burger",
                    "pop_2",
                    "crispy chicken, cheese, sauce, coleslaw",
                    330.90
            ));                              }
                          }

                          @Override
                          public void onFailure(Call<ApiPopularClass> call, Throwable t) {
                              foodList.add(new FoodDomain(
                                      "Fajita pizza",
                                      "pop_1",
                                      "chicken chunks, mozeralla cheese, cheddar cheese, special sauce, special spices",
                                      850.90
                              ));
                              foodList.add(new FoodDomain(
                                      "Beef cheese burger",
                                      "pop_2",
                                      "beef , cheese, sauce, coleslaw",
                                      330.90
                              ));
                              foodList.add(new FoodDomain(
                                      "Zinger cheese burger",
                                      "pop_2",
                                      "crispy chicken, cheese, sauce, coleslaw",
                                      330.90
                              ));
                          }
                      });

        adapter2=new PopularAdapter(foodList);
        recyclerViewPopularList.setAdapter(adapter2);
    }



}