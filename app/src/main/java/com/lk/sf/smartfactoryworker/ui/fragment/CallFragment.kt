package com.lk.sf.smartfactoryworker.ui.fragment

import android.os.Bundle
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.databinding.FragCallBinding
import com.lk.sf.smartfactoryworker.ui.BaseFragment

/**
 * @author: winton
 * @time: 2018/7/11 23:29
 * @package: com.lk.sf.smartfactoryworker.ui.fragment
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 一句话描述
 */
class CallFragment:BaseFragment<FragCallBinding>() {

    companion object {
        fun newInstance(param:Bundle?):CallFragment{
            var fragment = CallFragment()
            param?.let { fragment.arguments=it }
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.frag_call
    }

}