package com.base.net

/**
 * @author anhuang
 * @date 2022/3/23
 * Description : 自定义接口返回值类型
 */
data class ApiResponse<T> (
    var data: T,
    var errorCode: Int = -1,
    var errorMsg: String = ""
)