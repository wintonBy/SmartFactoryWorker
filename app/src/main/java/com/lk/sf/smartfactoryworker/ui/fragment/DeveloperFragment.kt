package com.lk.sf.smartfactoryworker.ui.fragment

import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.databinding.FragDeveloperInfoBinding
import com.lk.sf.smartfactoryworker.ui.BaseFragment

/**
 * @author: winton
 * @time: 2018/7/13 17:43
 * @package: com.lk.sf.smartfactoryworker.ui.fragment
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 开发者页面
 */
class DeveloperFragment:BaseFragment<FragDeveloperInfoBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.frag_developer_info
    }
}