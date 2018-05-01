package com.akses.blk.samarinda.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ASUS on 22/08/2017.
 */

public class PencariInfo {

    private static final String PREF_NAME_PENCARI = "pencariinfo";
    private static final String KEY_USERNAME_PENCARI = "username";
    private static final String KEY_NAMA_PENCARI = "nama_pencari";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public PencariInfo(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME_PENCARI, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setUsernamePencari(String usernamePencari) {

        editor.putString(KEY_USERNAME_PENCARI, usernamePencari);
        editor.apply();
    }

    public void setNamaPencari(String namaPencari) {

        editor.putString(KEY_NAMA_PENCARI, namaPencari);
        editor.apply();
    }

    public void clearPencariInfo() {

        editor.clear();
        editor.commit();
    }

    public String getKeyUsernamePencari() {

        return sharedPreferences.getString(KEY_USERNAME_PENCARI, "");
    }

    public String getKeyNamaPencari() {

        return sharedPreferences.getString(KEY_NAMA_PENCARI, "");
    }
}
