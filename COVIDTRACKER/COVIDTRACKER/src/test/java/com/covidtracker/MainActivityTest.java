package com.covidtracker;

import android.app.Activity;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {
    private static boolean onCreateCalled;
    private static boolean onCreateOptionCalled;
    private static boolean onClickCalled;

    @Test
    public void onCreate() {
        if(!onCreateCalled) {
            System.out.println("OnCreate is being called" +'\n'+ (10*10));
            onCreateCalled = true;
        }
    }

    @Test
    public void onCreateOptionsMenu() {
        if(!onCreateOptionCalled) {
            System.out.println("OnCreate is being called" +'\n'+ (10*10));
            onCreateOptionCalled = true;
        }
    }

    @Test
    public void onBackPressed() {

    }
}