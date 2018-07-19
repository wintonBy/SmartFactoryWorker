package com.lk.sf.smartfactoryworker.http

import java.io.Serializable

/**
 * @author: winton
 * @time: 2018/7/13 10:10
 * @package: com.lk.sf.smartfactoryworker.http
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: Http响应类基类
 */
open class BaseResponse:Serializable {

  companion object {
      val FAILED = 0
      val SUCCESS = 1
      val CODE_TIMEOUT = 2
  }
    var message:String? = null
    var result:Int = -1

    fun isSuccess():Boolean = result == SUCCESS

    fun isCodeTimeOut():Boolean = result == CODE_TIMEOUT
}