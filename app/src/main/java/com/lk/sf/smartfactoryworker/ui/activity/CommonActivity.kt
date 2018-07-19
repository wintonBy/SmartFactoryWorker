package com.lk.sf.smartfactoryworker.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.widget.ImageView
import android.widget.TextView
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.databinding.ActCommonBinding
import com.lk.sf.smartfactoryworker.presenter.BasePresenter
import com.lk.sf.smartfactoryworker.ui.BaseActivity
import com.lk.sf.smartfactoryworker.ui.fragment.AboutFragment
import com.lk.sf.smartfactoryworker.ui.fragment.DeviceFragment
import com.lk.sf.smartfactoryworker.ui.fragment.HomeFragment

/**
 * @author: winton
 * @time: 2018/7/12 13:19
 * @package: com.lk.sf.smartfactoryworker.ui.activity
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 一句话描述
 */
class CommonActivity : BaseActivity<ActCommonBinding, BasePresenter>() {

    lateinit var fragment: Fragment

    private val fm: FragmentManager by lazy {
        supportFragmentManager
    }

    var type: Int = -1
    var title: String = ""

    companion object {
        const val FT_ABOUT = 1
        const val FT_DEVELOPER = 2

        fun start(context: Context, type: Int, title: String, params: Bundle?) {
            var intent = Intent(context, CommonActivity::class.java)
            with(intent) {
                putExtra("type", type)
                putExtra("title", title)
                params?.let {
                    this.putExtras(params)
                }
            }
            context.startActivity(intent)

        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        with(intent.extras){
            initFragment(this.getInt("type"))
            binding!!.include!!.findViewById<TextView>(R.id.tv_title).text = this.getString("title")
        }
        update()
    }

    override fun initListener() {
        super.initListener()
        binding!!.include!!.findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            this@CommonActivity.finish()
        }
    }

    private fun initFragment(type: Int) {
        when (type) {
            FT_ABOUT -> fragment = AboutFragment.newInstance(null)
            FT_DEVELOPER -> fragment = DeviceFragment.newInstance(null)
        }
    }

    private fun update() {
        fragment?.let { fm.beginTransaction().replace(R.id.fl_content, fragment).commit() }
    }


    override fun getLayoutId(): Int {
        return R.layout.act_common
    }
}