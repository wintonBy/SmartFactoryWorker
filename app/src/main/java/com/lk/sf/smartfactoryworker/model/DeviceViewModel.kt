package com.lk.sf.smartfactoryworker.model

import android.arch.lifecycle.MutableLiveData
import com.lk.sf.smartfactoryworker.bean.Device
import com.lk.sf.smartfactoryworker.repository.Resource

/**
 * @author: winton
 * @time: 2018/8/4 15:57
 * @package: com.lk.sf.smartfactoryworker.model
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: deviceList viewModel
 */
class DeviceViewModel:BaseViewModel() {

    private var devices:MutableLiveData<Resource<List<Device>>> = MutableLiveData()

    fun getDevices():MutableLiveData<Resource<List<Device>>>{
        return devices
    }

    override fun start() {
        super.start()
        loadData()
    }

    private fun loadData(){
        var list = ArrayList<Device>()
        list.add(Device("232","22"))
        list.add(Device("232","22"))
        list.add(Device("232","22"))
        list.add(Device("232","22"))
        devices.value = Resource.success(list)
    }

}