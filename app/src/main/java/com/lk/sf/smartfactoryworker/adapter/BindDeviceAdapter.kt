package com.lk.sf.smartfactoryworker.adapter

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lk.sf.smartfactoryworker.BR
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.bean.Device
import com.lk.sf.smartfactoryworker.databinding.LayoutItemBindDeviceBinding

/**
 * @author: winton
 * @time: 2018/9/4 下午2:37
 * @desc: 描述
 */
class BindDeviceAdapter:BaseRVAdapter<Device,BindDeviceAdapter.ViewHolder> {

    private var bindDataId:Int = -1
    private var listener:ItemClick

    constructor(diffCallback: DiffUtil.ItemCallback<Device>,listener:ItemClick) : super(diffCallback){
        this.bindDataId = BR.device
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = DataBindingUtil.inflate<LayoutItemBindDeviceBinding>(inflater, R.layout.layout_item_bind_device,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bindDataId,getItem(position),listener)
    }


    class ViewHolder:RVViewHolder{
        constructor(binding:LayoutItemBindDeviceBinding):super(binding.root){
            this.binding = binding

        }

       fun bind(variableId: Int, value: Any,listener: ItemClick) {
            super.bind(variableId, value)
            (binding as LayoutItemBindDeviceBinding ).let {
                it.itemClick = listener
            }
        }
    }

    interface ItemClick{
        fun unbind(device:Device)
    }
}