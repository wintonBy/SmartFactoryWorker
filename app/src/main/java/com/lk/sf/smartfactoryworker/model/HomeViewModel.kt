package com.lk.sf.smartfactoryworker.model

import android.arch.lifecycle.MutableLiveData
import com.lk.sf.smartfactoryworker.bean.Device
import com.lk.sf.smartfactoryworker.http.BaseResponse
import com.lk.sf.smartfactoryworker.http.BaseSubscriber
import com.lk.sf.smartfactoryworker.http.RetrofitClient
import com.lk.sf.smartfactoryworker.repository.Resource
import retrofit2.Retrofit

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

    /**
     * 绑定设备
     */
    fun bindDevice(id:String){
        RetrofitClient.bindDevice(id,true,object :BaseSubscriber<BaseResponse>(){

            override fun onSuccess(response: BaseResponse) {
                loadDevices()
            }

            override fun onFailed(code: Int?, msg: String?) {
                loadDevices()
            }
        })
    }

    /**
     * 解绑设备
     */
    fun unbindDevice(id:String){
        RetrofitClient.bindDevice(id,false,object :BaseSubscriber<BaseResponse>(){
            override fun onSuccess(response: BaseResponse) {
                loadDevices()
            }

            override fun onFailed(code: Int?, msg: String?) {
                loadDevices()
            }
        })
    }


    private fun loadDevices(){
        list.add(Device("12","测试"))
        list.add(Device("12","测试"))
        devices.value = Resource.success(list)
    }



}