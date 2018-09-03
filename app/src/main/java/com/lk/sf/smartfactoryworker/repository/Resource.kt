package com.lk.sf.smartfactoryworker.repository

/**
 * @author: winton
 * @time: 2018/8/2 17:10
 * @package: com.lk.sf.smartfactoryworker.repository
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 封装的统一的响应
 */
class Resource<T:Any?> {

    val status:Int
    val data:T?
    val msg:String?

    constructor(status: Int, data: T?, msg: String?) {
        this.status = status
        this.data = data
        this.msg = msg
    }


    companion object {
        const val ERROR = 0
        const val LOADING = 1
        const val SUCCESS = 2

        fun <T> success(data:T):Resource<T>{
            return Resource(SUCCESS,data,null)
        }

        fun <T> error(data:T?,msg: String?):Resource<T>{
            return Resource(ERROR,data,msg)
        }

        fun <T> loading(data:T?):Resource<T>{
            return  Resource(LOADING,data,null)
        }
    }

}