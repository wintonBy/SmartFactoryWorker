package com.lk.sf.smartfactoryworker.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @author: winton
 * @time: 2018/7/15 16:27
 * @package: com.lk.sf.smartfactoryworker.adapter
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: ViewHolder
 */
class RVViewHolder :RecyclerView.ViewHolder{
    var binding:ViewDataBinding? = null

    constructor(view: View):super(view)
}