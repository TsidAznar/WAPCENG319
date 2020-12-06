package com.covidtracker;

import android.app.Activity;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {
    private static boolean onCreateCalled;
    private static boolean onCreateDialogCalled;
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
    }

    @Test
    public void onBackPressed() {

    }
}