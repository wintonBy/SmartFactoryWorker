package com.lk.sf.smartfactoryworker.ui.fragment

import android.os.Bundle
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.databinding.FragCallBinding
import com.lk.sf.smartfactoryworker.ui.BaseFragment
import com.lk.sf.smartfactoryworker.widget.CallListDialog

/**
 * @author: winton
 * @time: 2018/7/11 23:29
 * @package: com.lk.sf.smartfactoryworker.ui.fragment
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 呼叫页面
 */
class CallFragment:BaseFragment<FragCallBinding>() {

    companion object {
        fun newInstance(param:Bundle?):CallFragment{
            var fragment = CallFragment()
            param?.let { fragment.arguments=it }
            return fragment
        }
    }

    private val callerDialog:CallListDialog by lazy {
        CallListDialog.newInstance()
    }

    override fun getLayoutId(): Int {
        return R.layout.frag_call
    }

    override fun initListener() {
        super.initListener()
        binding!!.btCallManager.setOnClickListener {
            callerDialog.role = "manager"
            callerDialog.show(activity!!.supportFragmentManager,"callerList")
        }
        binding!!.btCallTech.setOnClickListener {
            callerDialog.role = "tech"
            callerDialog.show(activity!!.supportFragmentManager,"callerList")
        }
        binding!!.btCallQuality.setOnClickListener {
            callerDialog.role = "quality"
            callerDialog.show(activity!!.supportFragmentManager,"callerList")
        }
        binding!!.btCallSupport.setOnClickListener({
            callerDialog.role = "support"
            callerDialog.show(activity!!.supportFragmentManager,"callerList")
        })
        binding!!.btCallLogistics.setOnClickListener({
            callerDialog.role = "logistics"
            callerDialog.show(activity!!.supportFragmentManager,"callerList")
        })

    }

}