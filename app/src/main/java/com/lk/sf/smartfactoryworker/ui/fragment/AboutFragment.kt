package com.lk.sf.smartfactoryworker.ui.fragment

import android.os.Bundle
import com.lk.sf.smartfactoryworker.BuildConfig
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.databinding.FragAboutBinding
import com.lk.sf.smartfactoryworker.ui.BaseFragment
import com.lk.sf.smartfactoryworker.widget.InfoDialog
import android.content.Intent
import android.net.Uri


/**
 * @author: winton
 * @time: 2018/7/12 23:09
 * @package: com.lk.sf.smartfactoryworker.ui.fragment
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 关于页面
 */
class AboutFragment:BaseFragment<FragAboutBinding>(){

    private var infoDialog:InfoDialog? = null

    companion object {
        fun newInstance(params: Bundle?):AboutFragment{
            var fragment = AboutFragment()
            params?.let { fragment.arguments = it }
            return fragment
        }
    }

    override fun initData() {
        binding!!.tvAppVersion.text = String.format(resources.getString(R.string.version_p),BuildConfig.VERSION_NAME)
    }

    override fun initListener() {
        super.initListener()
        with(binding!!){
            tvCall.setOnClickListener {
                infoDialog?.let {
                    it.show()
                    return@setOnClickListener
                }
                infoDialog = object:InfoDialog(activity!!,getString(R.string.call_service),R.string.confirm,R.string.cancel){
                    override fun confirm() {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getString(R.string.call_num)))
                        startActivity(intent)
                        cancel()
                    }
                }

            }
            checkUpdate.setOnClickListener {
                checkUpdate()
            }
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.frag_about
    }

    /**
     * 版本检测
     */
    private fun checkUpdate(){

    }
}