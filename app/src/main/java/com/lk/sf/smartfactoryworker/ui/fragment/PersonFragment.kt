package com.lk.sf.smartfactoryworker.ui.fragment

import android.os.Bundle
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.databinding.FragPersonBinding
import com.lk.sf.smartfactoryworker.ui.BaseFragment
import com.lk.sf.smartfactoryworker.ui.activity.CommonActivity

/**
 * @author: winton
 * @time: 2018/7/11 23:19
 * @package: com.lk.sf.smartfactoryworker.ui.fragment
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 个人页面
 */
class PersonFragment:BaseFragment<FragPersonBinding>(){

    companion object {
        fun newInstance(params: Bundle?):PersonFragment{
            var fragment = PersonFragment()
            params?.let {
                fragment.arguments= it
            }
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.frag_person
    }

    override fun initListener() {
        super.initListener()
       with(binding!!){
           slLayout.setOnRefreshListener {

           }
           tvAbout.setOnClickListener {
                CommonActivity.start(context!!,CommonActivity.FT_ABOUT,getString(R.string.about),null);
           }
           tvFactory.setOnClickListener {
           }
           tvSystem.setOnClickListener {

           }
           tvService.setOnClickListener {
                CommonActivity.start(context!!,CommonActivity.FT_DEVELOPER,getString(R.string.service_info),null)
           }
       }

    }

}