package com.framgia.capstone.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.framgia.capstone.data.model.PhongKham;
import com.google.gson.Gson;

/**
 * Created by Duong on 2/22/2017.
 */
public class SharedPreferencesUtils {
    private static final String PREF_USER = "User";
    private static final String PREF_PHONGKHAM = "PHONGKHAM";

    public static void saveUser(Context context, String user) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_USER, new Gson().toJson(user))
                .apply();
    }

    public static String loadUser(Context context) {
        return new Gson().fromJson(
                PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_USER, null),
                String.class);
    }

    public static void deleteUser(Context context) {
  /*      PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .remove(PREF_USER)
                .clear()
                .apply();*/
        SharedPreferences preferences = context.getSharedPreferences(PREF_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    public static void savePhongKham(Context context, PhongKham phongKham) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_PHONGKHAM, new Gson().toJson(phongKham))
                .apply();
    }

    public static PhongKham loadPhongKham(Context context) {
        return new Gson().fromJson(PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_PHONGKHAM, null), PhongKham.class);
    }

    public static void deletePhongKham(Context context) {
     /*   PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .remove(PREF_PHONGKHAM)
                .clear()
                .apply();*/

        SharedPreferences preferences = context.getSharedPreferences(PREF_PHONGKHAM, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
}
