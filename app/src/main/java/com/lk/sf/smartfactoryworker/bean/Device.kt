package com.lk.sf.smartfactoryworker.bean

import android.arch.persistence.room.Entity

/**
 * @author: winton
 * @time: 2018/7/13 21:12
 * @package: com.lk.sf.smartfactoryworker.bean
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 设备描述类
 */

@Entity(tableName = "devices")
class Device:BaseBean() {
    var id:String? = null
    var name:String? = null
}