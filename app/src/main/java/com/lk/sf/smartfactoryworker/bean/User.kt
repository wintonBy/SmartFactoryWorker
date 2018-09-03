package com.lk.sf.smartfactoryworker.bean

/**
 * @author: winton
 * @time: 2018/7/12 22:21
 * @package: com.lk.sf.smartfactoryworker.bean
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 用户类
 */
data class User(val id:Int){
    var gender:Int = -1
    var name:String = ""
    var no:String = ""
    var phone = ""
    var type=""
    var department:Department? = null
}