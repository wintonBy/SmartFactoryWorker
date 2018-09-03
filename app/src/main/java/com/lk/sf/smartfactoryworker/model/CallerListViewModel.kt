package com.lk.sf.smartfactoryworker.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lk.sf.smartfactoryworker.bean.Caller
import com.lk.sf.smartfactoryworker.repository.Resource

/**
 * @author: winton
 * @time: 2018/7/30 23:31
 * @package: com.lk.sf.smartfactoryworker.model
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 呼叫人列表
 */
class CallerListViewModel:ViewModel(){

    private var callers:MutableLiveData<Resource<List<Caller>>> = MutableLiveData()

    fun getCallers():MutableLiveData<Resource<List<Caller>>>{
        return callers
    }

    /**
     * 调取网络接口 获取数据
     */
    fun load(role:String){
        var tmp = ArrayList<Caller>()
        when(role){
            "manager" -> tmp.add(Caller())
            "tech" -> {
                tmp.add(Caller())
                tmp.add(Caller())
            }
        }
        callers.value = Resource.success(tmp)
    }


}