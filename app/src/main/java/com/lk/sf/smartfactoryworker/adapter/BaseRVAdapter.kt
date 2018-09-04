package com.lk.sf.smartfactoryworker.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * @author: winton
 * @time: 2018/7/13 21:03
 * @package: com.lk.sf.smartfactoryworker.adapter
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: RecycleView adapter 基类
 */
open  abstract class BaseRVAdapter<T,V:RVViewHolder>:ListAdapter<T,V> {

    constructor(diffCallback:DiffUtil.ItemCallback<T>):super(diffCallback)

}