package com.base.common.base

import android.app.Application
import com.base.net.BaseRepository

/**
 * @author anhuang
 * @date 2022/3/24
 * Description :
 */
class NoViewModel(application: Application) : BaseViewModel<BaseRepository>(application){}