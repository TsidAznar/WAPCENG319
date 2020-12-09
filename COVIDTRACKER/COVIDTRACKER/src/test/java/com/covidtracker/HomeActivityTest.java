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
            System.out.println("OnStart is being called" +'\n'+ (2*3*4));
            onStartCalled = true;
        }
    }

    @Test
    public void startNextActivity() {
        if(!onStartNextActivityCalled) {
            System.out.println("OnStartNextActivity is being called" +'\n'+ (1+2+3));
            onStartNextActivityCalled = true;
        }
    }

    @Test
    public void onStop() {
        if(!onStopCalled) {
            System.out.println("OnStop is being called" +'\n'+ (3/4));
            onStopCalled = true;
        }
    }

    @Test
    public void onDestroy() {
        if(!onDestroyCalled) {
            System.out.println("OnDestroy is being called" +'\n'+ (1+1));
            onDestroyCalled = true;
        }
    }
}