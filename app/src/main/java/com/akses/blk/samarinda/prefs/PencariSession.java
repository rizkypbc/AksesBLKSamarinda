package com.akses.blk.samarinda.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ASUS on 22/08/2017.
 */

public class PencariSession {

    private static final String PREF_NAME_PENCARI = "login_pencari";
    private static final String PENCARI_IS_LOGGED_IN = "isLoggedInPencari";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public PencariSession(Context context) {

        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME_PENCARI, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setPencariIsLoggedIn(boolean pencariIsLoggedIn) {

        editor.putBoolean(PENCARI_IS_LOGGED_IN, pencariIsLoggedIn);
        editor.apply();
    }

    public boolean isPencariLoggedIn() {

        return sharedPreferences.getBoolean(PENCARI_IS_LOGGED_IN, false);
    }
}
