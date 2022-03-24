package com.base.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * @author anhuang
 * @date 2022/3/24
 * Description : App本身相关的 utils
 */
public class AppInfoUtils {

    /**
     * 获取app的版本名称
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context){
        PackageManager pm=context.getPackageManager();
        try {
            PackageInfo packageInfo=pm.getPackageInfo(context.getPackageName(),0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 获取app的版本码
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context){
        PackageManager pm=context.getPackageManager();
        try {
            PackageInfo packageInfo=pm.getPackageInfo(context.getPackageName(),0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
