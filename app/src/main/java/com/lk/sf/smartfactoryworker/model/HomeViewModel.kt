package com.lk.sf.smartfactoryworker.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lk.sf.smartfactoryworker.bean.Device

/**
 * @author: winton
 * @time: 2018/7/16 11:41
 * @package: com.lk.sf.smartfactoryworker.model
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 首页数据模型
 */
class HomeViewModel : ViewModel(){

    var devices:MutableLiveData<List<Device>> =  MutableLiveData()

}