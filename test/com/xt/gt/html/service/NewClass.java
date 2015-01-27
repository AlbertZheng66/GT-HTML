/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xt.gt.html.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author albert
 */
public class NewClass implements Serializable {

    private int int1 = 20;

    private long long1 = 200l;

    private float float1 = 22.22f;

    private double double1 = 333.22;

    private short short1  = 12;

    private String string1 = "aaaaaaaaaaaaaaaaaaaa";

    private Map map1 = new HashMap();

    private List list = new ArrayList();

    private Set set1 = new HashSet();

    private ChildClass child = new ChildClass();

    private NewClass nullValue;

    public NewClass() {
        map1.put("key1", "key1");
        map1.put("key2", 23456);
    }

    public double getDouble1() {
        return double1;
    }

    public void setDouble1(double double1) {
        this.double1 = double1;
    }

    public float getFloat1() {
        return float1;
    }

    public void setFloat1(float float1) {
        this.float1 = float1;
    }

    public int getInt1() {
        return int1;
    }

    public void setInt1(int int1) {
        this.int1 = int1;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public long getLong1() {
        return long1;
    }

    public void setLong1(long long1) {
        this.long1 = long1;
    }

    public Map getMap1() {
        return map1;
    }

    public void setMap1(Map map1) {
        this.map1 = map1;
    }

    public Set getSet1() {
        return set1;
    }

    public void setSet1(Set set1) {
        this.set1 = set1;
    }

    public short getShort1() {
        return short1;
    }

    public void setShort1(short short1) {
        this.short1 = short1;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public ChildClass getChild() {
        return child;
    }

    public void setChild(ChildClass child) {
        this.child = child;
    }

    public NewClass getNullValue() {
        return nullValue;
    }

    public void setNullValue(NewClass nullValue) {
        this.nullValue = nullValue;
    }

}
