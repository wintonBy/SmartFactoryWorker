package com.lk.sf.smartfactoryworker.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.lk.sf.smartfactoryworker.R
import com.lk.sf.smartfactoryworker.databinding.ActSplashBinding
import com.lk.sf.smartfactoryworker.presenter.BasePresenter
import com.lk.sf.smartfactoryworker.ui.BaseActivity
import com.lk.sf.smartfactoryworker.widget.PermissionInfoDialog
import java.net.HttpURLConnection

/**
 * @author: winton
 * @time: 2018/7/6 16:00
 * @package: com.lk.sf.smartfactoryworker.ui.activity
 * @project: SmartFactoryWorker
 * @mail:
 * @describe: 启动页
 */
class SplashActivity:BaseActivity<ActSplashBinding,BasePresenter>(){
    private val permissionDialog: PermissionInfoDialog by lazy {
        PermissionInfoDialog.newInstance().apply {
            this.setInfo("开启存储权限，保证应用正常使用！")
            this.onConfirm {
                ActivityCompat.requestPermissions(this@SplashActivity, Array(1){Manifest.permission.WRITE_EXTERNAL_STORAGE},REQ_STORAGE )
            }
        }
    }
    private val REQ_STORAGE = 1

    override fun getLayoutId(): Int {
        return R.layout.act_splash
    }

    override fun onResume() {
        super.onResume()
        checkStoragePermission()
    }

    private fun toNextView(){
        window.decorView.postDelayed(Runnable {
            LoginActivity.start(this@SplashActivity)
            this@SplashActivity.finish()
        },2000);
    }

    /**
     * 获取存储权限
     */
    private fun  checkStoragePermission(){
        var code = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if(code == PackageManager.PERMISSION_GRANTED){
            toNextView()
            return
        }
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            permissionDialog.show(supportFragmentManager,"permissionDialog")
        }else{
            ActivityCompat.requestPermissions(this, Array(1){Manifest.permission.WRITE_EXTERNAL_STORAGE},REQ_STORAGE )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQ_STORAGE){
            when(grantResults[0]){
                PackageManager.PERMISSION_GRANTED -> toNextView()
            }
        }
    }
}