package com.framgia.capstone.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import com.framgia.capstone.data.model.User;
import com.google.gson.Gson;


import java.util.List;

/**
 * Created by Duong on 2/22/2017.
 */
public class SharedPreferencesUtils {
    private static final String PREF_USER = "User";

    public static void saveUser(Context context, String user) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString
            (PREF_USER, new Gson().toJson(user)).apply();
    }

    public static String loadUser(Context context) {
        return new Gson().fromJson(PreferenceManager.getDefaultSharedPreferences(context).getString
            (PREF_USER, null), String.class);
    }

    public static void deleteUser(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
    }
}
