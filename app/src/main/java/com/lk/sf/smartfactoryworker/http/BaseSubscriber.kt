package com.lk.sf.smartfactoryworker.http

import com.blankj.utilcode.util.LogUtils
import com.lk.sf.smartfactoryworker.MyApplication
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import io.reactivex.FlowableSubscriber
import org.reactivestreams.Subscription

/**
 * @author: winton
 * @time: 2018/7/13 10:37
 * @package: com.lk.sf.smartfactoryworker.http
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 订阅类的基类
 */
open class BaseSubscriber<T:BaseResponse>:FlowableSubscriber<T> {

    companion object {
        var isCodeTimeOut = false
    }

    override fun onComplete() {
    }

    override fun onSubscribe(s: Subscription) {
        onStart(s)
    }

    override fun onNext(t: T) {
        when(t.result){
            BaseResponse.SUCCESS -> onSuccess(t)
            BaseResponse.FAILED -> onFailed(t.result,t.message)
            BaseResponse.CODE_TIMEOUT -> doCodeTimeOut()
        }
    }

    private fun doCodeTimeOut(){
        if(!isCodeTimeOut){
            MyApplication.instance.doCodeTimeOut()
            isCodeTimeOut = true
        }

    }

    override fun onError(t: Throwable?) {
        LogUtils.e(t)
        onFailed(null,t?.message)
    }
    open fun onStart(s: Subscription){
        s.request(Long.MAX_VALUE)
    }

    open fun onSuccess(response:T){

    }

    open fun onFailed(code:Int?,msg:String?){

    }





}