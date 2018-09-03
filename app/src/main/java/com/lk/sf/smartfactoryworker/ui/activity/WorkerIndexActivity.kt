package com.lk.sf.smartfactoryworker.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.TextView
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.databinding.ActIndexWorkerBinding
import com.lk.sf.smartfactoryworker.presenter.BasePresenter
import com.lk.sf.smartfactoryworker.ui.BaseActivity
import com.lk.sf.smartfactoryworker.ui.fragment.CallFragment
import com.lk.sf.smartfactoryworker.ui.fragment.DeviceFragment
import com.lk.sf.smartfactoryworker.ui.fragment.HomeFragment
import com.lk.sf.smartfactoryworker.ui.fragment.PersonFragment
import com.winton.bottomnavigationview.NavigationView

/**
 * @author: winton
 * @time: 2018/7/6 19:29
 * @package: com.lk.sf.smartfactoryworker.ui.activity
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 员工首页
 */
class WorkerIndexActivity:BaseActivity<ActIndexWorkerBinding,BasePresenter>() {

    private  lateinit var tabs:ArrayList<NavigationView.Model>

    private lateinit var fragments:ArrayList<Model>

    private var currentIndex = -1

    private lateinit var mTVTitle:TextView

    private val fm:FragmentManager by lazy{
        supportFragmentManager
    }
    companion object {
        fun start(context:Context){
            var intent = Intent(context,WorkerIndexActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.act_index_worker
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
        tabs.add(NavigationView.Model.Builder(R.mipmap.ic_local_check,R.mipmap.ic_local).title("打卡").build())
        tabs.add(NavigationView.Model.Builder(R.mipmap.ic_device_check,R.mipmap.ic_device).title("生产").build())
        tabs.add(NavigationView.Model.Builder(R.mipmap.ic_call_check,R.mipmap.ic_call).title("呼叫").build())
        tabs.add(NavigationView.Model.Builder(R.mipmap.ic_me_check,R.mipmap.ic_me).title("我的").build())
        binding!!.nv.setItems(tabs)
        binding!!.nv.build()
        binding!!.nv.check(0)
        changeFragment(0)
        mTVTitle.text = "打卡"
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
        fragments.add(Model("sign",HomeFragment.newInstance(null)))
        fragments.add(Model("device",DeviceFragment.newInstance(null)))
        fragments.add(Model("call",CallFragment.newInstance(null)))
        fragments.add(Model("person",PersonFragment.newInstance(null)))
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