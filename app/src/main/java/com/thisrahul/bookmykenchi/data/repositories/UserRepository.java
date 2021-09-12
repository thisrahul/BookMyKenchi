package com.thisrahul.bookmykenchi.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.thisrahul.bookmykenchi.data.network.APIClient;

import org.json.JSONObject;

import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {


   public LiveData<JsonObject> signIn(String email, String password,String deviceId){

        MutableLiveData<JsonObject> loginResponse = new MutableLiveData<>();

       JSONObject jsonObject = new JSONObject();
       try {
           jsonObject.put("email", email);
           jsonObject.put("password", password);
           jsonObject.put("deviceId", deviceId);
       } catch (Exception e) {
           e.printStackTrace();
       }
       RequestBody bodyRequest = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
        APIClient.getInterface().signIn(bodyRequest)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.e("message", " : " + response.message());
                        Log.e("body", " : " + response.body());
                        Log.e("errorbody", " : " + response.errorBody());

                        if (response.isSuccessful()){
                            loginResponse.setValue(response.body());
                        }else {
                            Gson gson = new Gson();
                            Type type = new TypeToken<JsonObject>() {}.getType();
                            JsonObject errorResponse = gson.fromJson(response.errorBody().charStream(),type);
                            Log.e("errorResponse", " : " + errorResponse);
                            loginResponse.setValue(errorResponse);
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        loginResponse.setValue(null);
                    }
                });

        return loginResponse;
    }

}
