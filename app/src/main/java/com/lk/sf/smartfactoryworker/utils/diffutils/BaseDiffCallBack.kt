package com.lk.sf.smartfactoryworker.utils.diffutils

import android.support.v7.util.DiffUtil

/**
 * @author: winton
 * @time: 2018/8/3 16:17
 * @package: com.lk.sf.smartfactoryworker.utils.diffutils
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: DiffUtils 异常
 */
open abstract class BaseDiffCallBack<T>(var oldList:List<T>, var newList:List<T>):DiffUtil.Callback() {



    open abstract override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean

    open override fun getOldListSize(): Int {
        return oldList.size
    }

    open override fun getNewListSize(): Int {
        return  newList.size
    }

    open abstract override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
}