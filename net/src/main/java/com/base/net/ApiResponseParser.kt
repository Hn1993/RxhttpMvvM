package com.base.net

import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.parse.TypeParser
import rxhttp.wrapper.utils.convertTo
import java.io.IOException
import java.lang.reflect.Type

/**
 * @author anhuang
 * @date 2022/3/23
 * Description :
 */
@Parser(name = "Response")
open class ApiResponseParser<T> : TypeParser<T> {

    //以下两个构造方法是必须的
    protected constructor() : super()
    constructor(type: Type) : super(type)

    @Throws(IOException::class)
    override fun onParse(response: okhttp3.Response): T {
        val data: ApiResponse<T> = response.convertTo(ApiResponse::class, *types)
        val t = data.data     //获取data字段
        if (data.errorCode != 0 || t == null) { //code不等于200，说明数据不正确，抛出异常
            throw ParseException(data.errorCode.toString(), data.errorMsg, response)
        }
        return t
    }
}
