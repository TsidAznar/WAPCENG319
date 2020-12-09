package com.covidtracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class HomeActivityTest {
    private static boolean onStartCalled;
    private static boolean onStartNextActivityCalled;
    private static boolean onStopCalled;
    private static boolean onDestroyCalled;


    @Test
    public void onStart() {
        if(!onStartCalled) {
            System.out.println("OnCreate is being called" +'\n'+ (10*10));
            onStartCalled = true;
        }
    }

    @Test
    public void startNextActivity() {
        if(!onStartNextActivityCalled) {
            System.out.println("OnCreate is being called" +'\n'+ (10*10));
            onStartNextActivityCalled = true;
        }
    }

    @Test
    public void onStop() {
        if(!onStopCalled) {
            System.out.println("OnCreate is being called" +'\n'+ (10*10));
            onStopCalled = true;
        }
    }

    @Test
    public void onDestroy() {
        if(!onDestroyCalled) {
            System.out.println("OnCreate is being called" +'\n'+ (10*10));
            onDestroyCalled = true;
        }
    }
}