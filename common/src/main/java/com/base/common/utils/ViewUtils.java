package com.base.common.utils;

import android.view.MotionEvent;
import android.view.View;

/**
 * @author anhuang
 * @date 2022/2/9
 * Description : View 相关的utils
 */
public class ViewUtils {
    /**
     *触摸事件 是否在指定view内
     **/
    public static boolean isTouchInViewRect(View view, MotionEvent ev) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        if(ev.getX() < x || ev.getX() > (x + view.getWidth()) || ev.getY() < y || ev.getY() > (y + view.getHeight())){
            return false;
        }
        return true;
    }
}
