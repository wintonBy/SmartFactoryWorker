package com.lk.sf.smartfactoryworker.utils

import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author: winton
 * @time: 2018/7/13 11:59
 * @package: com.lk.sf.smartfactoryworker.utils
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 一句话描述
 */
object ScheduelHelper {

    fun <T> compose(): FlowableTransformer<T, T> {
        return FlowableTransformer { observable ->
            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

}