package com.thisrahul.bookmykenchi.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.thisrahul.bookmykenchi.R;
import com.thisrahul.bookmykenchi.data.sharedpreference.SessionManagement;
import com.thisrahul.bookmykenchi.databinding.ActivityLoginBinding;
import com.thisrahul.bookmykenchi.ui.home.HomeActivity;


import static com.thisrahul.bookmykenchi.util.ViewUtils.showProgress;
import static com.thisrahul.bookmykenchi.util.ViewUtils.showToast;

public class LoginActivity extends AppCompatActivity implements LoginListener{
    private  ActivityLoginBinding binding;
    private ProgressDialog progressDialog;

    public SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        LoginViewModel viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setLoginViewModel(viewModel);

        viewModel.loginListener = this;

        sessionManagement = new SessionManagement(this);

       if (sessionManagement.getData("login").equals("1")){
           Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
           startActivity(intent);
           finish();
       }
    }

    @Override
    public void onProcessStart() {
        progressDialog =  showProgress(LoginActivity.this);
        progressDialog.show();
    }

    @Override
    public void onSuccess(LiveData<JsonObject> loginResponse) {
      loginResponse.observe(LoginActivity.this, new Observer<JsonObject>() {
          @Override
          public void onChanged(JsonObject loginResponse) {
              progressDialog.hide();
              String s = loginResponse.get("responseCode").toString();
              s = s.replaceAll("^\"|\"$", "");
              String s1 = "400";
                  if (s.equals("400")){
                     showToast(LoginActivity.this,"Invalid Email or Password");
                  }else{
                     Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                     sessionManagement.saveData("login","1");
                     sessionManagement.saveData("token",loginResponse.get("token").toString());
                     startActivity(intent);
                     finish();
                  }
              }
      });
    }

    @Override
    public void onFailure(String message) {
        progressDialog.hide();
        showToast(LoginActivity.this,"Login Failed due to "+message);
    }
}