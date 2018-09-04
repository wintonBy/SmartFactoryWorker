package com.lk.sf.smartfactoryworker.ui

import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lk.sf.smartfactoryworker.R

/**
 * @author: winton
 * @time: 2018/7/6 15:46
 * @package: com.lk.sf.smartfactoryworker.ui.fragment
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: Fragment基类
 */
abstract class BaseFragment<T: ViewDataBinding>:Fragment(){

    var binding:T? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<T>(layoutInflater, getLayoutId(),container,false)
        var view:View = binding!!.root
        initListener()
        initData()
        return view
    }

    abstract fun getLayoutId():Int

    open fun initData(){

    }
    open fun initListener(){

    }
}