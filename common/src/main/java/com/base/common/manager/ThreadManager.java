package com.base.common.manager;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import java.security.InvalidParameterException;


/**
 * 避免项目中过多的 NewThread 过度消耗性能
 * @author an huang
 */
public class ThreadManager {
    private String TAG = "ThreadManager";


    /** 主线程 */
    public static final int THREAD_UI = 0;
    /** background线程，用于一般的耗时操作 */
    public static final int THREAD_BACKGROUND = 1;
    /** data线程，用于数据库操作 */
    public static final int THREAD_DATA = 2;
    private static final int THREAD_SIZE = 3;
    /** 线程信息数组 */
    private static final Handler[] HANDLER_LIST = new Handler[THREAD_SIZE];
    private static final String[] THREAD_NAME_LIST = {
            "thread_ui",
            "thread_background",
            "thread_data"
    };
    private ThreadManager() {
        HANDLER_LIST[THREAD_UI] = new Handler();
    }


    private int currentIndex = 0;
    private Runnable currentRunnable;

    private static class ThreadManagerHolder {
        private static ThreadManager sManager = new ThreadManager();
    }
    public static ThreadManager getManager() {
        return ThreadManagerHolder.sManager;
    }
    /**
     * 派发任务
     *
     * @param index 线程类型
     */
    public void post(int index, Runnable r, LifecycleOwner owner) {
        postDelayed(index, r, 0,owner);
    }
    /**
     * 延迟派发任务
     *
     * @param index 线程类型
     */
    public void postDelayed(int index, Runnable r, long delayMillis, LifecycleOwner owner) {
        Handler handler = getHandler(index);
        handler.postDelayed(r, delayMillis);
        currentIndex = index;
        currentRunnable = r;
        Log.e(TAG,"postDelayed");
        owner.getLifecycle().addObserver(new ThreadManagerObserver());
    }
    /**
     * 删除任务
     *
     * @param index 线程类型
     */
    public void removeCallbacks(int index, Runnable r) {
        Handler handler = getHandler(index);
        handler.removeCallbacks(r);
        handler.removeCallbacksAndMessages(null);
    }
    /**
     * 获取线程Handler
     *
     * @param index 线程类型
     */
    public Handler getHandler(int index) {
        if (index < 0 || index >= THREAD_SIZE) {
            throw new InvalidParameterException();
        }
        if (HANDLER_LIST[index] == null) {
            synchronized (HANDLER_LIST) {
                if (HANDLER_LIST[index] == null) {
                    HandlerThread thread = new HandlerThread(THREAD_NAME_LIST[index]);
                    if (index != THREAD_UI) {
                        //优先级要低于主线程
                        thread.setPriority(Thread.MIN_PRIORITY);
                    }
                    thread.start();
                    Handler handler = new Handler(thread.getLooper());
                    HANDLER_LIST[index] = handler;
                }
            }
        }
        return HANDLER_LIST[index];
    }
    /**
     * 判断是否运行在当前线程
     *
     * @param index 线程类型
     * @return true yes
     */
    public boolean runningOnCurrent(int index) {
        return getHandler(index).getLooper() == Looper.myLooper();
    }

    public class ThreadManagerObserver implements LifecycleObserver{
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void removeCallback(){
            Log.e(TAG,"removeCallback removeCallback="+currentIndex + "-- currentRunnable --"+currentRunnable);
            removeCallbacks(currentIndex,currentRunnable);
        }
    }
}
