package com.thisrahul.bookmykenchi.data.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {

    SharedPreferences sharedpreferences;

    public SessionManagement(Context context) {
         sharedpreferences = context.getSharedPreferences("sessionData", Context.MODE_PRIVATE);
    }

    public void saveData(String key , String value){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getData(String key){
        return sharedpreferences.getString(key, "0");
    }

    public void clear(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();
    }
}
