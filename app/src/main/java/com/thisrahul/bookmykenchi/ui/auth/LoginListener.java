package com.thisrahul.bookmykenchi.ui.auth;

import androidx.lifecycle.LiveData;


import com.google.gson.JsonObject;

import okhttp3.ResponseBody;

public interface LoginListener {
    void onProcessStart();
    void onSuccess(LiveData<JsonObject> loginResponse);
    void onFailure(String message);
}
