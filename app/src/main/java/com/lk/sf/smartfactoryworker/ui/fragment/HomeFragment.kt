package com.lk.sf.smartfactoryworker.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.lk.sf.smartfactoryworker.BR
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.adapter.BaseRVAdapter
import com.lk.sf.smartfactoryworker.bean.Device
import com.lk.sf.smartfactoryworker.databinding.FragHomeBinding
import com.lk.sf.smartfactoryworker.model.HomeViewModel
import com.lk.sf.smartfactoryworker.ui.BaseFragment

/**
 * @author: winton
 * @time: 2018/7/9 20:12
 * @package: com.lk.sf.smartfactoryworker.ui.fragment
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 首页
 */
class HomeFragment:BaseFragment<FragHomeBinding>() {

    private lateinit var adapter:BaseRVAdapter<Device>
    private lateinit var list: ArrayList<Device>

    private lateinit var viewModel:HomeViewModel

    companion object {
        fun newInstance(param: Bundle?):HomeFragment{
            var instances:HomeFragment = HomeFragment();
            param?.let {
                instances.arguments = param
            }
            return instances
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.frag_home
    }

    override fun initData() {
        super.initData()
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.devices.observe(this, Observer {

        })
        list = ArrayList()
        adapter = BaseRVAdapter(list,R.layout.layout_item_bind_device,BR.device)
        binding!!.rv.layoutManager = LinearLayoutManager(context)
        binding!!.rv.adapter = adapter
    }

    override fun initListener() {
        super.initListener()
    }
}