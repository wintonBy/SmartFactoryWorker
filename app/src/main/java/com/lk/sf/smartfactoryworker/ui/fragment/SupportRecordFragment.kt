package com.lk.sf.smartfactoryworker.ui.fragment

import android.os.Bundle
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.databinding.FragSupportRecordBinding
import com.lk.sf.smartfactoryworker.ui.BaseFragment

/**
 * @author: winton
 * @time: 2018/7/26 22:03
 * @package: com.lk.sf.smartfactoryworker.ui
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 维修记录页面
 */
class SupportRecordFragment: BaseFragment<FragSupportRecordBinding>() {

    companion object {

        fun newInstance(param: Bundle?):SupportRecordFragment{
            var instances = SupportRecordFragment();
            param?.let {
                instances.arguments = param
            }
            return instances
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.frag_support_record
    }
}