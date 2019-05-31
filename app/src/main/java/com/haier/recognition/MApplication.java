package com.haier.recognition;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.LinkedList;

/**
 * Created by aaron on 16/9/7.
 */

public class MApplication extends Application {

    private static LinkedList<Activity> activityLinkedList;

    @Override
    public void onCreate() {
        super.onCreate();

        ZXingLibrary.initDisplayOpinion(this);


        activityLinkedList = new LinkedList<>();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                activityLinkedList.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

                activityLinkedList.remove(activity);
            }
        });
    }


    public static void showList() {
        for (Activity activity : activityLinkedList) {

        }
    }

    public static void exitAppList() {
        for (Activity activity : activityLinkedList) {
            activity.finish();
        }
    }


}
