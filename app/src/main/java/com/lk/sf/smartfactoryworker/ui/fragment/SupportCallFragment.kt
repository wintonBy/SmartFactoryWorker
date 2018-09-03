package com.lk.sf.smartfactoryworker.ui.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.databinding.FragSupportCallBinding
import com.lk.sf.smartfactoryworker.ui.BaseFragment
import com.lk.sf.smartfactoryworker.ui.activity.ScanActivity
import com.lk.sf.smartfactoryworker.widget.PermissionInfoDialog

/**
 * @author: winton
 * @time: 2018/7/26 21:25
 * @package: com.lk.sf.smartfactoryworker.ui.fragment
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 维修版呼叫页
 */
class SupportCallFragment:BaseFragment<FragSupportCallBinding>() {

    companion object {
        const val REQ_SCAN = 1
        const val REQ_CAMERA = 2
        fun newInstance(param: Bundle?):SupportCallFragment{
            var instances:SupportCallFragment = SupportCallFragment();
            param?.let {
                instances.arguments = param
            }
            return instances
        }
    }
    private val permissionDialog: PermissionInfoDialog by lazy {
        PermissionInfoDialog.newInstance().apply {
            this.setInfo("开启相机权限，才能正常使用扫码功能哦！")
            this.onConfirm {
                requestPermissions(Array(1){Manifest.permission.CAMERA}, REQ_CAMERA)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.frag_support_call
    }

    override fun initData() {
        super.initData()
    }

    override fun initListener() {
        super.initListener()
        binding!!.llScan.setOnClickListener {
            checkPermission()
        }
    }

    /**
     * 权限检查
     */
    private fun checkPermission(){
        var cameraPermissionCode = ContextCompat.checkSelfPermission(context!!, Manifest.permission.CAMERA)
        if(cameraPermissionCode == PackageManager.PERMISSION_GRANTED){
            toScan()
            return
        }
        if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
            permissionDialog.show(activity!!.supportFragmentManager,"permission")
        }else{
            requestPermissions(Array(1){ Manifest.permission.CAMERA}, SupportCallFragment.REQ_CAMERA)
        }
    }

    private fun toScan() {
        ScanActivity.startForResult(activity!!,"扫码响应", SupportCallFragment.REQ_SCAN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQ_CAMERA){
            when(grantResults[0]){
                PackageManager.PERMISSION_GRANTED -> toScan()
            }
        }
    }

}