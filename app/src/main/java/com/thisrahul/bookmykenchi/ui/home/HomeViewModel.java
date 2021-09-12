package com.thisrahul.bookmykenchi.ui.home;

import android.content.Intent;
import android.se.omapi.Session;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.thisrahul.bookmykenchi.data.sharedpreference.SessionManagement;

public class HomeViewModel extends ViewModel {

    public String token;
    public HomeListener homeListener;

    public void onSignOutButtonClick(View view){
        homeListener.onClick();
    }
}
