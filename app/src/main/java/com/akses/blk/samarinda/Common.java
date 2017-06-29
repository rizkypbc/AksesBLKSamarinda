package com.akses.blk.samarinda;

/**
 * Created by ASUS on 15/05/2017.
 */

public class Common {

    static final String SERVICE_API_URL_PELATIHAN = "http://192.168.43.212/blki/pelatihan.php";
    static final String SERVICE_API_URL_KERJA = "http://192.168.43.212/blki/pencari.php";
    static final String SERVICE_API_URL_PERUSAHAAN = "http://192.168.43.212/blki/perusahaan.php";

    public static final String BASE_IP          = "http://192.168.43.212/";
    public static final String LOGIN_URL        = BASE_IP + "loginblki/login.php";

    static final int RESULT_SUCCESS = 0;
    static final int RESULT_ERROR = 1;
    static final int RESULT_USER_EXITS = 2;
}
