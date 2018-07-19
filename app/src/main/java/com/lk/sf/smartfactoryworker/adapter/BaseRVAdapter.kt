package com.lk.sf.smartfactoryworker.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
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
open class BaseRVAdapter<T:Any>:RecyclerView.Adapter<RVViewHolder> {
    var list:ArrayList<T>? = null
    var layoutId:Int = -1
    var bindDataId:Int = -1

    constructor(list: ArrayList<T>?,layoutId:Int,bindDataId:Int){
        this.list = list
        this.layoutId = layoutId
        this.bindDataId = bindDataId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        var binding:ViewDataBinding = DataBindingUtil.inflate(inflater,layoutId,parent,false)
        var holder:RVViewHolder = RVViewHolder(binding.root)
        holder.binding = binding
        return holder
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        holder.binding!!.setVariable(bindDataId,list!![position])
        holder.binding!!.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return list?.size?:0
    }



}