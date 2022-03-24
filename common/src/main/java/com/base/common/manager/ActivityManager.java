package com.base.common.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author an huang
 */
public class ActivityManager {
    public static List<Activity> activitys = new ArrayList<>();

    public static Activity getTopActivity() {
        if (activitys.size() > 0) {
            return activitys.get(activitys.size() - 1);
        }
        return null;
    }

    public static void addActivity(Activity activity) {
        activitys.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activitys.remove(activity);
    }

    public static void removeAll() {
        for (Activity activity : activitys) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    public static boolean contains(Class<?> clazz) {
        for (Activity activity : activitys) {
            if (activity.getClass().isAssignableFrom(clazz)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 除了传来的Activity其他的全部删除
     * 可以传多个Activity
     *
     * @param clazz
     */
    public static void removeAll(Class<?>... clazz) {
        boolean isExist = false;
        for (Activity act : activitys) {
            for (Class c : clazz) {
                if (act.getClass().isAssignableFrom(c)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                if (!act.isFinishing()) {
                    act.finish();
                }
            } else {
                isExist = false;
            }
        }
    }

    /**
     * 移除栈内多个相同的页面
     * @param clazz
     */
    public static void removeAllSameActivity(Class<?>... clazz) {
        boolean isExist = false;
        for (Activity act : activitys) {
            for (Class c : clazz) {
                if (act.getClass().isAssignableFrom(c)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                if (!act.isFinishing()) {
                    act.finish();
                }
            } else {
                isExist = false;
            }
        }

    }
}
