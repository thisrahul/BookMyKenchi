package com.thisrahul.bookmykenchi.data.network;

import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Services {

    @POST("api/v1/user/singin")
    Call<JsonObject> signIn(@Body RequestBody requestBody);

}
