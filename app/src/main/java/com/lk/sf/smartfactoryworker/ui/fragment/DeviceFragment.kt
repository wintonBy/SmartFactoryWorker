package com.lk.sf.smartfactoryworker.ui.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.lk.sf.smartfactoryworker.BR
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.adapter.BaseRVAdapter
import com.lk.sf.smartfactoryworker.bean.Device
import com.lk.sf.smartfactoryworker.databinding.FragDeviceBinding
import com.lk.sf.smartfactoryworker.model.DeviceViewModel
import com.lk.sf.smartfactoryworker.repository.Resource
import com.lk.sf.smartfactoryworker.ui.BaseFragment
import com.lk.sf.smartfactoryworker.utils.diffutils.BindDeviceDiffCallback

/**
 * @author: winton
 * @time: 2018/7/11 23:30
 * @package: com.lk.sf.smartfactoryworker.ui.fragment
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 设备信息页
 */
class DeviceFragment:BaseFragment<FragDeviceBinding>() {

    private lateinit var adapter:BaseRVAdapter<Device>

    private lateinit var model:DeviceViewModel

    private var devices:ArrayList<Device> = ArrayList()
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

    override fun initData() {
        super.initData()
        binding!!.rv.layoutManager = LinearLayoutManager(context)
        adapter = BaseRVAdapter(BindDeviceDiffCallback(), BR.device,R.layout.layout_item_device)
        model = DeviceViewModel()
        model.getDevices().observe(this, Observer {
            when(it!!.status){
                Resource.SUCCESS -> {
                    adapter.submitList(it.data)
                }
                Resource.ERROR -> {

                }
                Resource.LOADING ->{

                }
            }
        })
        model.start()
        binding!!.rv.adapter = adapter
    }
}