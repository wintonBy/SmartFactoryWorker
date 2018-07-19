package com.lk.sf.smartfactoryworker.ui

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lk.sf.smartfactoryworker.MyApplication
import com.lk.sf.smartfactoryworker.presenter.BasePresenter

/**
 * @author: winton
 * @time: 2018/7/6 15:21
 * @package: com.lk.sf.smartfactoryworker.ui.activity
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: Activity基类
 */
abstract class BaseActivity<T: ViewDataBinding,P:BasePresenter?>: AppCompatActivity(){

    val TAG = this.javaClass.name

    var mPresenter:P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.instance.addActivity(this)
        super.onCreate(savedInstanceState)
        initPreLayout()
        initView()
        initListener()
        mPresenter=initPresenter()
        initData(savedInstanceState)
    }
    var binding:T? = null

    private fun initView(){
        binding = DataBindingUtil.setContentView(this,getLayoutId())
    }

    /**
     * 设置布局
     */
    abstract fun getLayoutId():Int

    /**
     * 调用此方法可以在初始化布局之前执行
     */
    open fun initPreLayout(){

    }

    /**
     * 初始化监听器
     */
    open fun initListener(){

    }

    /**
     * 初始化布局
     */
    open fun initData(savedInstanceState: Bundle?){

    }

    open fun initPresenter():P?{
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        MyApplication.instance.removeActivity(this)
    }
}