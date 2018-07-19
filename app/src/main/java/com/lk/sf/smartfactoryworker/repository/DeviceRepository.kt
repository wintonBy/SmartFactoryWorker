package com.lk.sf.smartfactoryworker.repository

import com.lk.sf.smartfactoryworker.bean.Device

/**
 * @author: winton
 * @time: 2018/7/16 21:49
 * @package: com.lk.sf.smartfactoryworker.repository
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 设备数据仓库
 */
class DeviceRepository {

    fun getDevice(deviceId:String):Device?{

    }

    companion object {
        @Volatile private var instance:DeviceRepository? = null

        fun getInstance() = instance?: synchronized(this){
            instance?:DeviceRepository().also { instance = it }
        }
    }
}