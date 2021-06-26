package com.example.test;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

public class ActivityKiller extends Application
{
    private List<Activity> mList = new LinkedList<Activity>();
    private static ActivityKiller instance;
    private ActivityKiller(){}
    public static ActivityKiller getInstance()
    {
        if (null == instance)
        {
            instance = new ActivityKiller();
        }
        return instance;
    }
    public void addActivity(Activity activity)
    {
        mList.add(activity);
    }
    public void exit()
    {
        try {
            for (Activity activity:mList)
            {
                if (activity != null)
                    activity.finish(); //所有的活动是在这里被销毁的
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.exit(0);
        }
    }
    public void onLowMemory()
    {
        super.onLowMemory();
        System.gc();
    }
}

