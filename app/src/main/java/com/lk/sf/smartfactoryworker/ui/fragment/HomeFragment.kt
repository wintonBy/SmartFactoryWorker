package com.lk.sf.smartfactoryworker.ui.fragment

import android.Manifest
import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import com.blankj.utilcode.util.SnackbarUtils
import com.lk.sf.smartfactoryworker.BR
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.adapter.BaseRVAdapter
import com.lk.sf.smartfactoryworker.bean.Device
import com.lk.sf.smartfactoryworker.databinding.FragHomeBinding
import com.lk.sf.smartfactoryworker.model.HomeViewModel
import com.lk.sf.smartfactoryworker.repository.Resource
import com.lk.sf.smartfactoryworker.ui.BaseFragment
import com.lk.sf.smartfactoryworker.ui.activity.ScanActivity
import com.lk.sf.smartfactoryworker.utils.diffutils.BindDeviceDiffCallback
import com.lk.sf.smartfactoryworker.widget.PermissionInfoDialog
import com.zxing.activity.CodeUtils

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

    private lateinit var viewModel:HomeViewModel

    private val permissionDialog:PermissionInfoDialog by lazy {
        PermissionInfoDialog.newInstance().apply {
            this.setInfo("开启相机权限，才能正常使用扫码功能哦！")
            this.onConfirm {
                requestPermissions(Array(1){Manifest.permission.CAMERA},REQ_CAMERA)
            }
        }
    }

    companion object {
        const val REQ_CAMERA:Int = 1
        const val REQ_SCAN = 2

        fun newInstance(param: Bundle?):HomeFragment{
            var instances = HomeFragment();
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
        viewModel.getDevices().observe(this, Observer {
           when(it!!.status){
               Resource.SUCCESS -> adapter.submitList(it.data)
               Resource.ERROR-> showError(it.msg!!)
               Resource.LOADING ->{}
           }
        })
        adapter = BaseRVAdapter(BindDeviceDiffCallback(),BR.device,R.layout.layout_item_bind_device)
        binding!!.rv.layoutManager = LinearLayoutManager(context)
        binding!!.rv.adapter = adapter
    }

    override fun initListener() {
        super.initListener()
        binding!!.cvSign.setOnClickListener {
            checkPermission()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_CANCELED){
            return
        }
        when(requestCode){
            REQ_SCAN -> parseScan(data)
        }
    }

    /**
     * 解析内容
     */
    private fun parseScan(data:Intent?){
        data?.let {
            var type = it.getIntExtra(CodeUtils.RESULT_TYPE,-1);
            var result = it.getStringExtra(CodeUtils.RESULT_STRING);
            when(type){
                CodeUtils.RESULT_SUCCESS -> parseSuccess(result)
                CodeUtils.RESULT_FAILED -> showError("解析失败")
            }
        }

    }

    private fun showError(msg:String) {
        SnackbarUtils.with(activity!!.window.decorView).setMessage(msg).showError()
    }

    /**
     *
     */
    private fun parseSuccess(data:String){
        viewModel.bindDevice(data)
    }

    /**
     * 权限检查
     */
    private fun checkPermission(){
        var cameraPermissionCode = ContextCompat.checkSelfPermission(context!!,Manifest.permission.CAMERA)
        if(cameraPermissionCode == PackageManager.PERMISSION_GRANTED){
            toScan()
            return
        }
        if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
            permissionDialog.show(activity!!.supportFragmentManager,"permissionDialog")
        }else{
            requestPermissions(Array(1){Manifest.permission.CAMERA},REQ_CAMERA)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQ_CAMERA ){
            when(grantResults[0]){
                PackageManager.PERMISSION_GRANTED -> toScan()
            }
        }
    }

    /**
     * 跳转至扫码页
     */
    private fun  toScan(){
        var intent = Intent(context,ScanActivity::class.java)
        intent.putExtra("title","扫码绑定")
        startActivityForResult(intent,REQ_SCAN)
    }
}