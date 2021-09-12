package com.thisrahul.bookmykenchi.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.thisrahul.bookmykenchi.R;
import com.thisrahul.bookmykenchi.data.sharedpreference.SessionManagement;
import com.thisrahul.bookmykenchi.databinding.ActivityHomeBinding;
import com.thisrahul.bookmykenchi.ui.auth.LoginActivity;

public class HomeActivity extends AppCompatActivity implements HomeListener{

    private ActivityHomeBinding binding;
    private SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        HomeViewModel viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        binding.setHomeViewModel(viewModel);

        viewModel.homeListener = this;
        sessionManagement = new SessionManagement(this);
        viewModel.token = sessionManagement.getData("token");
    }

    @Override
    public void onClick() {
        sessionManagement.clear();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}