package com.thisrahul.bookmykenchi.ui.auth;


import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.thisrahul.bookmykenchi.data.repositories.UserRepository;



public class LoginViewModel extends ViewModel {

    public String email;
    public   String password;

    public LoginListener loginListener;

    public void onSignInButtonClick(View view){
        loginListener.onProcessStart();
        if (email.isEmpty() || password.isEmpty() ){
            loginListener.onFailure("Invalid Email or Password");
            return;
        }else {
            LiveData<JsonObject> loginResponse = new UserRepository().signIn(email, password, String.valueOf(Math.random()));
            loginListener.onSuccess(loginResponse);
        }
    }
}
