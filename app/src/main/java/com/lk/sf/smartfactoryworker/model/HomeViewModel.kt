package com.lk.sf.smartfactoryworker.model

import android.arch.lifecycle.MutableLiveData
import com.lk.sf.smartfactoryworker.bean.Device
import com.lk.sf.smartfactoryworker.repository.Resource

/**
 * @author: winton
 * @time: 2018/7/16 11:41
 * @package: com.lk.sf.smartfactoryworker.model
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 首页数据模型
 */
class HomeViewModel : BaseViewModel(){

    private var devices:MutableLiveData<Resource<List<Device>>> =  MutableLiveData()
    var list = ArrayList<Device>()

    fun getDevices():MutableLiveData<Resource<List<Device>>>{
        return devices
    }

    override fun start() {
        super.start()
        loadDevices()
    }

    fun bindDevice(id:String){
        list.add(Device("22","添加"))
        list.add(Device("22","添加"))
        devices.value = Resource.success(ArrayList<Device>(list))
    }

    private fun loadDevices(){
        list.add(Device("12","测试"))
        list.add(Device("12","测试"))
        devices.value = Resource.success(list)
    }



}