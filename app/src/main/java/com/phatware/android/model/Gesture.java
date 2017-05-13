package com.phatware.android.model;

/**
 * Created by jmd19 on 5/11/2017.
 */

public class Gesture {
    private String id;
    private String sign;
    private String action;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Gesture(String id, String sign, String action) {

        this.id = id;
        this.sign = sign;
        this.action = action;
    }
}
