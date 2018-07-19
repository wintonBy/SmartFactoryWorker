package com.lk.sf.smartfactoryworker

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import cn.jpush.android.api.JPushInterface
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.lk.sf.smartfactoryworker.ui.activity.LoginActivity
import com.zxing.activity.ZXingLibrary
import kotlin.properties.Delegates

/**
 * @author: winton
 * @time: 2018/7/6 15:49
 * @package: com.lk.sf.smartfactoryworker
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: Application
 */

class MyApplication:Application(){

    companion object {
        var instance:MyApplication by Delegates.notNull()
    }

    /**
     * 自己管理Activity栈
     */
    private lateinit var activitys:ArrayList<Activity>

    override fun onCreate() {
        super.onCreate()
        instance = this
        activitys = ArrayList()
        MultiDex.install(this)
        initUtils()
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
        ZXingLibrary.initDisplayOpinion(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    private fun initUtils() {
        /*初始化工具类*/
        Utils.init(this)
    }

    fun addActivity(activity: Activity){
        activitys.add(activity)
    }
    fun removeActivity(activity: Activity){
        activitys.remove(activity)
    }


    fun doCodeTimeOut(){
        ToastUtils.showLong(R.string.code_timeout_tip)
        for(activity in activitys){
            if(!activity.isFinishing){
                activity.finish()
            }
        }
        LoginActivity.start(this)
    }
}