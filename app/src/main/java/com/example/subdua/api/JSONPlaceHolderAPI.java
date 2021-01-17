package com.example.subdua.api;

import com.example.subdua.model.RetrofitResponse;
import com.example.subdua.model.User;
import com.example.subdua.model.UserDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceHolderAPI {

    @GET("users/{username}")
    Call<UserDetail> userDetail(@Path("username") String username);

    @GET("/users/{username}/followers")
    Call<List<User>> followersFragment(@Path("username") String username);

    @GET("/users/{username}/following")
    Call<List<User>> followingFragment(@Path("username") String username);


    @GET("/search/users")
    Call<RetrofitResponse> searchUserData(
            @Query("q") String username
    );


}

