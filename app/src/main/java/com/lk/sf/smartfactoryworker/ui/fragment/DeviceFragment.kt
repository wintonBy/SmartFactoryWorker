package com.lk.sf.smartfactoryworker.ui.fragment

import android.os.Bundle
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.databinding.FragDeviceBinding
import com.lk.sf.smartfactoryworker.ui.BaseFragment

/**
 * @author: winton
 * @time: 2018/7/11 23:30
 * @package: com.lk.sf.smartfactoryworker.ui.fragment
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 设备信息页
 */
class DeviceFragment:BaseFragment<FragDeviceBinding>() {

    companion object {
        fun newInstance(params:Bundle?):DeviceFragment{
            var fragment = DeviceFragment()
            params?.let { fragment.arguments= it }
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.frag_device
    }
}