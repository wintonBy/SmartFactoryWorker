package com.lk.sf.smartfactoryworker.constant

import com.lk.sf.smartfactoryworker.BuildConfig

/**
 * @author: winton
 * @time: 2018/7/9 20:40
 * @package: com.lk.sf.smartfactoryworker.constant
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 编译控制类
 */
object DeveloperConfig {

    val isDebug:Boolean = BuildConfig.DEBUG

    fun getHostUrl():String{
        return Constant.HOST_SERVER
    }
}