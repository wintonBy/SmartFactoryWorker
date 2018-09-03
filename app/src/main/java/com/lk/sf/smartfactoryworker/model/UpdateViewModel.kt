package com.lk.sf.smartfactoryworker.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lk.sf.smartfactoryworker.bean.UpdateInfo
import com.lk.sf.smartfactoryworker.http.BaseSubscriber
import com.lk.sf.smartfactoryworker.http.RetrofitClient
import com.lk.sf.smartfactoryworker.http.response.UpdateInfoResponse
import com.lk.sf.smartfactoryworker.repository.Resource
import org.reactivestreams.Subscription

/**
 * @author: winton
 * @time: 2018/8/2 17:02
 * @package: com.lk.sf.smartfactoryworker.model
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 检查更新
 */
class UpdateViewModel:BaseViewModel() {

    private var data:MutableLiveData<Resource<UpdateInfo>> = MutableLiveData()

    fun getUpdate():MutableLiveData<Resource<UpdateInfo>>{
        return data
    }

    override fun start() {
        checkUpdate()
    }

    private fun checkUpdate(){
        RetrofitClient.checkUpdate(object :BaseSubscriber<UpdateInfoResponse>(){
            override fun onSuccess(response: UpdateInfoResponse) {
                super.onSuccess(response)
                if(response.isSuccess()){
                    data.value = Resource.success(response.data!!)
                }else{
                    data.value = Resource.error(null,response.message)
                }
            }

            override fun onError(t: Throwable?) {
                super.onError(t)
                data.value = Resource.error(null,t?.message)
            }

            override fun onStart(s: Subscription) {
                super.onStart(s)
                data.value = Resource.loading(null)
            }
        })
    }
}