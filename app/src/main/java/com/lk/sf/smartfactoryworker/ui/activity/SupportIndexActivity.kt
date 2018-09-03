package com.lk.sf.smartfactoryworker.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.TextView
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.databinding.ActIndexSupportBinding
import com.lk.sf.smartfactoryworker.presenter.BasePresenter
import com.lk.sf.smartfactoryworker.ui.BaseActivity
import com.lk.sf.smartfactoryworker.ui.fragment.PersonFragment
import com.lk.sf.smartfactoryworker.ui.fragment.SupportCallFragment
import com.lk.sf.smartfactoryworker.ui.fragment.SupportRecordFragment
import com.winton.bottomnavigationview.NavigationView

/**
 * @author: winton
 * @time: 2018/7/26 20:14
 * @package: com.lk.sf.smartfactoryworker.ui.activity
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 维修版首页
 */
class SupportIndexActivity:BaseActivity<ActIndexSupportBinding,BasePresenter>() {
    private  lateinit var tabs:ArrayList<NavigationView.Model>

    private lateinit var fragments:ArrayList<SupportIndexActivity.Model>

    private var currentIndex = -1

    private lateinit var mTVTitle: TextView

    private val fm: FragmentManager by lazy{
        supportFragmentManager
    }
    companion object {
        fun start(context: Context){
            var intent = Intent(context,SupportIndexActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.act_index_support
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        binding!!.action!!.findViewById<View>(R.id.iv_back).visibility = View.INVISIBLE
        mTVTitle = binding!!.action!!.findViewById(R.id.tv_title)
        initFragments()
        initTab()
    }

    private fun initTab(){
        tabs = ArrayList()
        tabs.add(NavigationView.Model.Builder(R.mipmap.ic_call_check,R.mipmap.ic_call).title("呼叫").build())
        tabs.add(NavigationView.Model.Builder(R.mipmap.ic_local_check,R.mipmap.ic_local).title("记录").build())
        tabs.add(NavigationView.Model.Builder(R.mipmap.ic_me_check,R.mipmap.ic_me).title("我的").build())
        binding!!.nv.setItems(tabs)
        binding!!.nv.build()
        binding!!.nv.check(0)
        changeFragment(0)
        mTVTitle.text = "呼叫"
    }

    override fun initListener() {
        super.initListener()
        binding!!.nv.setOnTabSelectedListener(object :NavigationView.OnTabSelectedListener{
            override fun unselected(p0: Int, p1: NavigationView.Model?) {

            }

            override fun selected(p0: Int, p1: NavigationView.Model?) {
                changeFragment(p0)
                mTVTitle.text = p1!!.title
            }
        })
    }

    private fun initFragments(){
        fragments = ArrayList()
        fragments.add(SupportIndexActivity.Model("call",SupportCallFragment.newInstance(null)))
        fragments.add(SupportIndexActivity.Model("record",SupportRecordFragment.newInstance(null)))
        fragments.add(SupportIndexActivity.Model("person", PersonFragment.newInstance(null)))
    }

    /**
     * 切换fragment
     */
    private fun changeFragment(index:Int){
        if(index == currentIndex){
            return
        }
        var model = fragments[index]
        var ft = fm.beginTransaction()
        if(!model.fragment.isAdded){
            ft.add(R.id.fl_content,model.fragment,model.tag)
        }
        if(currentIndex != -1){
            ft.hide(fragments[currentIndex].fragment)
        }
        ft.show(model.fragment)
        ft.commit()
        currentIndex = index
    }

    private class Model(tag:String,fragment: Fragment){
        val tag = tag
        val fragment = fragment
    }

}
