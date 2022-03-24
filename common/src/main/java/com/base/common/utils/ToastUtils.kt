package com.base.common.utils

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.base.common.manager.CommonManager

/**
 * @author anhuang
 * @date 2022/3/24
 * Description :
 */
object ToastUtils {

    private val mHandler = Handler(Looper.getMainLooper())
    private var mToast: Toast? = null

    open fun show(msgResId: Int) {
        show(msgResId, false)
    }

    fun show(msgResId: Int, timeLong: Boolean) {
        show(CommonManager.getInstance().appContext.getString(msgResId), timeLong)
    }

    fun show(msg: CharSequence?) {
        show(msg, false)
    }

    fun show(msg: CharSequence?, timeLong: Boolean) {
        runOnUiThread {
            if (mToast != null) {
                mToast!!.cancel()
            }
            val duration = if (timeLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
            mToast = Toast.makeText(CommonManager.getInstance().appContext, msg, duration)
            mToast!!.show()
        }
    }

    private fun runOnUiThread(runnable: Runnable) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            runnable.run()
        } else {
            mHandler.post(runnable)
        }
    }
}