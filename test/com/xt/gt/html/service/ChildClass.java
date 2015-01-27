
package com.xt.gt.html.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author albert
 */
public class ChildClass implements Serializable {

    private int int1 = 20;


    private double double1 = 333.22;

    private String string1 = "aaaaaaaaaaaaaaaaaaaa";

    private Map map1 = new HashMap();

    public ChildClass() {
    }

    public double getDouble1() {
        return double1;
    }

    public void setDouble1(double double1) {
        this.double1 = double1;
    }

    public int getInt1() {
        return int1;
    }

    public void setInt1(int int1) {
        this.int1 = int1;
    }

    public Map getMap1() {
        return map1;
    }

    public void setMap1(Map map1) {
        this.map1 = map1;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }
}
