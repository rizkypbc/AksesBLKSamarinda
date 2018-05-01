package com.akses.blk.samarinda.Model;

import java.util.ArrayList;

/**
 * Created by ASUS on 22/08/2017.
 */

public class Value {

    String value;
    String message;
    ArrayList<Pencari> result;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Pencari> getResult() {
        return result;
    }

    public void setResult(ArrayList<Pencari> result) {
        this.result = result;
    }
}
