package com.example.app;


import com.example.library.MyUtil;
import com.google.common.collect.ImmutableList;


public class MainApp {

    public static void main(String[] args) {
        MyUtil util = new MyUtil();
        ImmutableList<String> names = util.getNames(); // uses Guava from library
        System.out.println(names);
    }
}
